package sp.alvaro.odf;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import sp.alvaro.TurmaFileRecorder;
import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.model.TarjetaFaltasAnuais;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.model.TurmaSheet;

public class OdsRecorder implements TurmaFileRecorder {
	
	private Logger logger = Logger.getLogger(OdsRecorder.class);

	// regra de negócio
	public static final int PRESENCA_MINIMA = 75; // 75%
	
    private static final String MODELO_CONSOLIDADO = "resources/consolidado_modelo.ods";
    private static final int MAX_MATERIAS = 12; 
    private static final int MAX_NOTAS = 54;
    private static final int TARJETA_PASSO = 4; // quantas células de uma tarjeta pra outras
    private static final int LINHA_MATERIA = 3;
    private static final int LINHA_TURMA = 4;
    private static final int LINHA_AULAS_PREVISTAS =62;
    private static final int LINHA_AULAS_DADAS = 63;
    private static final int LINHA_PROFESSOR = 64;
    private static final String COLUNA_FALTAS_ANUAIS = "AX";
    
    private File outputDir;
    
    public OdsRecorder(File outputDir) {
        
        this.outputDir = outputDir;
    }
    
    @Override
    public void record(Collection<TurmaFile> turmaFiles) throws IOException {
        
        for (TurmaFile f: turmaFiles) {
        	
        	logger.debug("Gravando arquivo " + f.getTurma());
            
            // consistência
            int turmas_size = f.getSheets().get(0).getTarjetas().size();
            if (turmas_size > MAX_MATERIAS) {
                throw new IllegalArgumentException(
                        turmas_size + " é muito! Sistema não suporta mais que " + MAX_MATERIAS + " matérias.");
            }
            
            SpreadsheetDocument ods;
			try {
				ods = SpreadsheetDocument.newSpreadsheetDocument();
				ods = SpreadsheetDocument.loadDocument(MODELO_CONSOLIDADO);
			} catch (Exception e) {
				String msg = "Não consegui carregar a planilha de modelo " + MODELO_CONSOLIDADO;
				logger.error(msg, e);
				throw new IOException(msg, e);
			}

        	this.processSheets(ods, f);
            
            String path = outputDir.getAbsolutePath() + "/" + f.getTurma() + ".ods";
            try {
				ods.save(path);
			} catch (Exception e) {
				String msg = "Não consegui salvar planilha em " + path;
				logger.error(msg, e);
				throw new IOException(msg, e);
			}
        }
    }
    
    private void processSheets(SpreadsheetDocument ods, TurmaFile f) {

    	int i = 0;
        for (TurmaSheet turmaSheet: f.getSheets()) { // percorre bimestres
            
        	logger.debug("Gravando planilha " + turmaSheet.getBimestre());
        	
        	if (turmaSheet.getBimestre() == Periodo.ANO)
        		i = 4;
            Table table = ods.getTableList().get(i);
            
            table.getCellByPosition("C" + LINHA_TURMA).setStringValue(f.getTurma());
            
            Coluna col = new Coluna("C");
            for (Tarjeta tarjeta: turmaSheet.getTarjetas()) {
                
                // consistência
                int tarj_size = tarjeta.getNotas().size();
                if (tarj_size > MAX_NOTAS){
                	IllegalArgumentException e = new IllegalArgumentException(
                            tarj_size + " é muito! Sistema não suporta mais que " + MAX_NOTAS + " linhas por tarjeta.");
                	logger.warn(e.getMessage() + " enquanto parseando ", e);
                    throw e;
                }
                
                // preenche dados
                table.getCellByPosition(col.getValor()+LINHA_MATERIA).setStringValue(tarjeta.getMateria());
                table.getCellByPosition(col.getValor()+LINHA_AULAS_PREVISTAS).setStringValue(Integer.toString(tarjeta.getAulasPrevistas()));
                table.getCellByPosition(col.getValor()+LINHA_AULAS_DADAS).setStringValue(Integer.toString(tarjeta.getAulasDadas()));
                Coluna colProf = new Coluna(col.getValor());
                colProf.dec();
                table.getCellByPosition(colProf.getValor()+LINHA_PROFESSOR).setStringValue(tarjeta.getProfessor());
                
                int lin = 7;
                for (Conceito nota: tarjeta.getNotas()) {
                    
                    String l = Integer.toString(lin);
                    Coluna cn = new Coluna(col.getValor()); 
                    Coluna cf = new Coluna(col.getValor());
                    cn.inc();
                    cf.inc();
                    table.getCellByPosition(cn.getValor()+l).setStringValue(nota.getAluno().getNome());
                    if (nota.getAlteracao() != null && !nota.getAlteracao().isEmpty()) {
                    	table.getCellByPosition(col+l).setStringValue(nota.getAlteracao());
                    } else {
                    	table.getCellByPosition(col+l).setStringValue(Double.toString(nota.getNota()));
                    }
                    table.getCellByPosition(cf.getValor()+l).setStringValue(Integer.toString(nota.getFaltas()));
                    lin++; 
                }
                
                col.inc(TARJETA_PASSO); 
            }
            
            if (turmaSheet.getBimestre() == Periodo.ANO) { // notas finais
            	this.recordFaltasAnuais(f.getFaltasAnuais(), turmaSheet, table, 
            			new Coluna(COLUNA_FALTAS_ANUAIS));
            }
            i++;
        }
    }

	private void recordFaltasAnuais(TarjetaFaltasAnuais faltasAnuais, TurmaSheet finalSheet, 
			Table table, Coluna col) {

		logger.debug("Gravando notas anuais");
		
		int lin = 7;
		Coluna col2 = new Coluna(col.getValor());
		col2.inc();
		Coluna col3 = new Coluna(col2.getValor());
		col3.inc();
		
		table.getCellByPosition(col2.getValor()+LINHA_TURMA).setStringValue(faltasAnuais.getTurma());
		String totalAulas = Integer.toString(faltasAnuais.getAulasDadas());
		String previstas = Integer.toString(faltasAnuais.getAulasPrevistas());
		table.getCellByPosition(col2.getValor()+LINHA_AULAS_DADAS).setStringValue(totalAulas);
		table.getCellByPosition(col2.getValor()+LINHA_AULAS_PREVISTAS).setStringValue(previstas);

		for (Conceito conc: finalSheet.getTarjetas().get(0).getNotas()) {
			
			Aluno aluno = conc.getAluno();
			int falta = faltasAnuais.getFaltas().get(aluno);
			
			String faltaStr = Integer.toString(falta);
			String porcentagem = "" + Math.round(100d*falta/faltasAnuais.getAulasDadas()) + "%";
			String l = Integer.toString(lin);

			table.getCellByPosition(col.getValor()+l).setStringValue(aluno.getNome());
			table.getCellByPosition(col2.getValor()+l).setStringValue(faltaStr);
			table.getCellByPosition(col3.getValor()+l).setStringValue(porcentagem);
            
//            if (falta > 100 - PRESENCA_MINIMA) {
//            	Font font = (Font) table.getCellByPosition(col2.getValor()+l).getFont();
//            	font.setColor(Color.RED);
//            	table.getCellByPosition(col2.getValor()+l).setFont(font);
//            }
            
            lin++;
		}
		
	}

}
