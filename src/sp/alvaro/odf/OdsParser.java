package sp.alvaro.odf;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import sp.alvaro.NotasParser;
import sp.alvaro.NotasParserException;
import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.Tarjeta;

/**
 * Recupera as notas nos arquivos ods
 * @author leonardo
 *
 */
public class OdsParser implements NotasParser {
    
	private Logger logger = Logger.getLogger(OdsParser.class);
	
    public static final int MAX_ALUNOS = 54; // o máximo que cabe na tarjeta
    private static final String COL_FIRST_TARJETA = "C"; // coluna das notas
    private static final String CELL_PROF = "B64";
    private static final String CELL_FIRST_AULAS_DADAS = "C63";
    private static final String LIN_TURMAS = "4";
    private static final String LIN_MATERIAS = "3";
    private static final String LIN_AULAS_DADAS = "63";
    private static final String LIN_AULAS_PREVISTAS = "62";   
    private static final String LIN_FIRST_NOTA = "7";
    private static final int TARJETAS_DISTANCE = 4;

    public Set<ProfFile> parse(Collection<File> files) throws NotasParserException {
        
        Set<ProfFile> profFiles = new HashSet<ProfFile>();
        for (File file: files) {

        	profFiles.add(this.parseFile(file));
        }
        return profFiles;
    }
    
    public ProfFile parseFile(File file) throws NotasParserException {
        
    	logger.debug("Lendo arquivo " + file.getName());
    	
        SpreadsheetDocument planilha;
        try {
            planilha = SpreadsheetDocument.loadDocument(file);
        } catch (Exception e) {
        	String msg = "Não pude ler o arquivo " + file.getName();
        	logger.error(msg, e);
            throw new NotasParserException(msg);
        }
        
        Table table = null;
        try {
        	table = planilha.getTableList().get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
			String msg = "Arquivo " + file.getName() + " não possui planilha 0";
        	logger.error(msg, e);
        	throw new NotasParserException(msg);
        }
        
        // extract basic information
        String prof = table.getCellByPosition(CELL_PROF).getDisplayText();
        if (prof == null || prof.isEmpty()) {
			String msg = "Célula B64 do arquivo " + file.getName()
					+ " deveria conter o nome do professor";
        	logger.error(msg);
        	throw new NotasParserException(msg);
        }
        
        ProfFile profFile = new ProfFile(prof, file.getName());
        for (int bim=1; bim<=4; bim++) {
			ProfSheet profSheet = parseSheet(
					planilha.getTableList().get(bim - 1), prof,
					Periodo.valueOf(bim));
            if (profSheet == null) {
            	logger.warn("Período " + bim + " do " + prof + " não foi incluído");
            } else {
            	profFile.getSheets().add(profSheet);
            } 
        }
        
        return profFile;
    }
    
    /**
	 * 
	 * @param table
	 * @param prof
	 * @param bim
	 * @return a planilha parseada ou <code>null</code> caso a primeira célula
	 *         de aulas dadas esteja vazia, ou algum outro problema aconteça
     * @throws NotasParserException 
	 */
    private ProfSheet parseSheet(Table table, String prof, Periodo bim) throws NotasParserException {
    	
    	logger.debug("Lendo período " + bim);

        String check = table.getCellByPosition(CELL_FIRST_AULAS_DADAS).getDisplayText();
        if (check == null || check.isEmpty()) {
        	return null;
        }

        ProfSheet profSheet = new ProfSheet(bim, prof);

        Coluna y = new Coluna(COL_FIRST_TARJETA);
        int i = 0;
        String turma = table.getCellByPosition(y.getValor().concat(LIN_TURMAS)).getDisplayText();
        // while !turma.isEmpty
        while (!turma.isEmpty() && !turma.contains("FIM")) {
    	// TODO: não deveria precisar do "FIM"

            Tarjeta tarj = parseTarjeta(table, i, prof, bim);
            if (tarj == null) {
				logger.warn("Tarjeta da " + turma + " " + bim
						+ " não incluída na planilha de "
						+ profSheet.getProfessor());
            } else {
            	profSheet.getTarjetas().add(tarj);
            }

            y.inc(TARJETAS_DISTANCE);
            i++;
            turma = table.getCellByPosition(y.getValor().concat(LIN_TURMAS)).getDisplayText();
        }

        return profSheet;
    }
    
    /**
     * 
     * @param table
     * @para index índice da tarjeta na planilha, indo de 0 a N-1
     * @return
     * @throws NotasParserException 
     */
    private Tarjeta parseTarjeta(Table table, int index, String prof, Periodo bim) throws NotasParserException {

        Coluna y = new Coluna(COL_FIRST_TARJETA);
        y.inc(TARJETAS_DISTANCE*index);
		String turma = table.getCellByPosition(y.getValor().concat(LIN_TURMAS))
				.getDisplayText();
		String aulasDadasStr = table.getCellByPosition(
				y.getValor().concat(LIN_AULAS_DADAS)).getDisplayText();
		String aulasPrevistasStr = table.getCellByPosition(
				y.getValor().concat(LIN_AULAS_PREVISTAS)).getDisplayText();
		String materia = table.getCellByPosition(
				y.getValor().concat(LIN_MATERIAS)).getDisplayText();
		
		if (turma.isEmpty()) {
			String msg = "Turma não especificada na tarjeta " + (index + 1)
					+ " do " + bim + " do prof " + prof;
			logger.error(msg);
			throw new NotasParserException(msg);
		}
		if (materia.isEmpty()) {
			String msg = "Matéria não especificada na tarjeta " + (index + 1)
					+ " do " + bim + " do prof " + prof;
			logger.error(msg);
			throw new NotasParserException(msg);
		}
        		
        int aulasDadas=0, aulasPrevistas=0;
        try {
        	aulasDadas = Integer.parseInt(aulasDadasStr);
        } catch (NumberFormatException e) {
        	String message = "Aulas dadas não foram registradas na célula" + y.getValor() + LIN_AULAS_DADAS;
        	logger.warn(message);
        }
        try {
        	aulasPrevistas = Integer.parseInt(aulasPrevistasStr);
	    } catch (NumberFormatException e) {
        	String message = "Aulas previstas não foram registradas na célula" + y.getValor() + LIN_AULAS_PREVISTAS;
        	logger.warn(message);
	    }
        
        Tarjeta tarj = new Tarjeta(turma, materia, prof, bim, aulasDadas, aulasPrevistas);
        
        // notas
        int row = Integer.parseInt(LIN_FIRST_NOTA);
        for (int i=0; i<MAX_ALUNOS; i++) { 

            String nota = table.getCellByPosition(y.getValor().concat(Integer.toString(row))).getDisplayText();
            
            Coluna ya = new Coluna(y.getValor());
            ya.dec();
            String aluno = table.getCellByPosition(ya.toString().concat(Integer.toString(row))).getDisplayText();
            Coluna yf = new Coluna(y.getValor());
            yf.inc();
            String faltas = table.getCellByPosition(yf.getValor().concat(Integer.toString(row))).getDisplayText();

            int notaInt = 0, faltasInt = 0;
            if (nota != null && !nota.isEmpty()) {
                try {
                    notaInt = Integer.parseInt(nota);
                }
                catch(NumberFormatException e) {
                    ;
                }
                try {
                    faltasInt = Integer.parseInt(faltas);
                }
                catch(NumberFormatException e) {
                    ;
                }                
            }
            Conceito conc = new Conceito(new Aluno(aluno, turma), notaInt, faltasInt);
            tarj.getNotas().add(conc);
            
            row++;
        }
        
        return tarj;
    }
    
}
