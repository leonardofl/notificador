package sp.alvaro.odf;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import sp.alvaro.NotasParser;
import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.TarjetaProf;

//TODO: testar com mais tarjetas, pra alcançar o AA
/**
 * Recupera as notas nos arquivos ods
 * @author leonardo
 *
 */
public class OdsParser implements NotasParser {
    
    private static final int MAX_ALUNOS = 54; // o máximo que cabe na tarjeta

    public Set<ProfFile> parse(Collection<File> files) throws IOException {
        
        Set<ProfFile> profFiles = new HashSet<ProfFile>();
        for (File file: files) {
            profFiles.add(this.parseFile(file));
        }
        return profFiles;
    }
    
    public ProfFile parseFile(File file) throws IOException {
        
        SpreadsheetDocument planilha;
        try {
            planilha = SpreadsheetDocument.loadDocument(file);
        } catch (Exception e) {
            throw new IOException("Falha da API de ODF");
        }
        
        Table table = planilha.getTableList().get(0);

        // extract basic information
        String prof = table.getCellByPosition("B64").getDisplayText();
        String materia = table.getCellByPosition("C3").getDisplayText();

        ProfFile profFile = new ProfFile(prof, materia, file.getName());
        for (int bim=1; bim<=4; bim++) {
            ProfSheet profSheet = parseSheet(planilha.getTableList().get(bim-1), bim);
            profFile.getSheets().add(profSheet);
        }
        
        return profFile;
    }
    
    private ProfSheet parseSheet(Table table, int bim) {

        ProfSheet profSheet = new ProfSheet(Periodo.valueOf(bim));

        Coluna y = new Coluna("C");
        int i = 0;
        // while !turma.isEmpty
        String turma = table.getCellByPosition(y.getValor().concat("4")).getDisplayText();
        while (!turma.isEmpty() && !turma.contains("FIM")) {

            TarjetaProf tarj = parseTarjeta(table, i);
            profSheet.getTarjetas().add(tarj);

            y.inc(4);
            i++;
            turma = table.getCellByPosition(y.getValor().concat("4")).getDisplayText();
        }

        return profSheet;
    }
    
    /**
     * 
     * @param table
     * @para index índice da tarjeta na planilha, indo de 0 a N-1
     * @return
     */
    private TarjetaProf parseTarjeta(Table table, int index) {

        Coluna y = new Coluna("C");
        y.inc(4*index);
        String turma = table.getCellByPosition(y.getValor().concat("4")).getDisplayText();        
        String aulasDadas = table.getCellByPosition(y.getValor().concat("63")).getDisplayText();   
        String aulasPrevistas = table.getCellByPosition(y.getValor().concat("62")).getDisplayText(); 
        TarjetaProf tarj = new TarjetaProf(turma, Integer.parseInt(aulasDadas), Integer.parseInt(aulasPrevistas));
        
        // notas
        int row = 7;
        // while // !nota.isEmpty
        for (int i=0; i<MAX_ALUNOS; i++) { 

            String nota = table.getCellByPosition(y.getValor().concat(Integer.toString(row))).getDisplayText();
            
            Coluna ya = new Coluna(y.getValor());
            ya.dec();
            String aluno = table.getCellByPosition(ya.toString().concat(Integer.toString(row))).getDisplayText();
            Coluna yf = new Coluna(y.getValor());
            yf.inc();
            String faltas = table.getCellByPosition(yf.getValor().concat(Integer.toString(row))).getDisplayText();

            if (nota != null && !nota.isEmpty()) {

                int notaInt = 0, faltasInt = 0;
                try {
                    notaInt = Integer.parseInt(nota);
                    faltasInt = Integer.parseInt(faltas);
                }
                catch(NumberFormatException e) {
                    ;
                }
                Conceito conc = new Conceito(new Aluno(aluno, turma), notaInt, faltasInt);
                tarj.getNotas().add(conc);
            }
            
            row++;
        }
        
        return tarj;
    }
    
}
