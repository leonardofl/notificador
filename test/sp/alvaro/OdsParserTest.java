package sp.alvaro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.odf.OdsParser;

/**
 * Este teste depende dos arquivos em test/resources
 * @author leonardo
 *
 */
public class OdsParserTest {

    public final static String WORK_DIR = "test/resources";
    public final static String EXTENSION = "ods";
    public final static String LETTERS_SHEET = "test/resources/bad_format/letras.ods";
    public final static String BLANK_LINES_SHEET = "test/resources/bad_format/linhas_em_branco.ods";
    
    private Set<File> files;
    private ProfFile expectedFile1, expectedFile2, currentFile1, currentFile2;
    
    @Before
    public void setUp() throws Exception {
        
        FilePicker picker = new FilePicker(WORK_DIR, EXTENSION);
        files = picker.pickFiles();
        
        // dados para comparação
        ValoresProf vals = new ValoresProf();
        expectedFile1 = vals.getProfSheet1();
        expectedFile2 = vals.getProfSheet2();  
        
        // dados calculados
        NotasParser parser = new OdsParser();
        Set<ProfFile> profFiles = null;
        try {
            profFiles = parser.parse(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (ProfFile f: profFiles) {
            if (f.getFileName().equals("prof1.ods"))
                currentFile1 = f;
            if (f.getFileName().equals("prof2.ods"))
                currentFile2 = f;
        }
    }

    @Test // verifica nome do professor e matéria
    public void verifyDataHead() {
        
        assertEquals(expectedFile1.getProfessor(), currentFile1.getProfessor());
        
        assertEquals(expectedFile2.getProfessor(), currentFile2.getProfessor());
    }
    
    @Test
    public void verifySheetDataFromProf1() {

        for (int i=0; i<4; i++) {

            assertEquals(4, currentFile1.getSheets().size());
            assertEquals(expectedFile1.getSheets().get(i).getBimestre(), 
                    currentFile1.getSheets().get(i).getBimestre());

            for (int j=0; j<4; j++) {
                assertEquals(4, currentFile1.getSheets().get(i).getTarjetas().size());
                assertEquals(expectedFile1.getSheets().get(i).getTarjetas().get(j).getAulasDadas(), 
                        currentFile1.getSheets().get(i).getTarjetas().get(j).getAulasDadas());
                assertEquals(expectedFile1.getSheets().get(i).getTarjetas().get(j).getTurma(), 
                        currentFile1.getSheets().get(i).getTarjetas().get(j).getTurma());
            }
        }
    }
    
    @Test
    public void verifySheetDataFromProf2() {

        for (int i=0; i<4; i++) {

            assertEquals(4, currentFile2.getSheets().size());
            assertEquals(expectedFile2.getSheets().get(i).getBimestre(), 
                    currentFile2.getSheets().get(i).getBimestre());

            for (int j=0; j<4; j++) {
                assertEquals(4, currentFile2.getSheets().get(i).getTarjetas().size());
                assertEquals(expectedFile2.getSheets().get(i).getTarjetas().get(j).getAulasDadas(), 
                        currentFile2.getSheets().get(i).getTarjetas().get(j).getAulasDadas());
                assertEquals(expectedFile2.getSheets().get(i).getTarjetas().get(j).getTurma(), 
                        currentFile2.getSheets().get(i).getTarjetas().get(j).getTurma());
            }
        }
    }
    
    @Test
    public void verifyNotasProf1() {        

    	for (int i=0; i<3; i++) {
            assertEquals(expectedFile1.getSheets().get(i), currentFile1.getSheets().get(i));
            for (Tarjeta tarj: expectedFile1.getSheets().get(i).getTarjetas()) {
            	String turma = tarj.getTurma();
            	String materia = tarj.getMateria();
            	Tarjeta other = currentFile1.getSheets().get(i).findTarjeta(turma, materia);
            	assertTrue(tarj.notasEquals(other));
            }
    	}
    }

    @Test
    public void verifyNotasProf2() {        

    	for (int i=0; i<3; i++) {
            assertEquals(expectedFile2.getSheets().get(i), currentFile2.getSheets().get(i));
            for (Tarjeta tarj: expectedFile2.getSheets().get(i).getTarjetas()) {
            	String turma = tarj.getTurma();
            	String materia = tarj.getMateria();
            	Tarjeta other = currentFile2.getSheets().get(i).findTarjeta(turma, materia);
            	assertTrue(tarj.notasEquals(other));
            }
    	}
    }
    
    @Test
    public void readSheetWithLetters() throws NotasParserException {

        File sheetFile = new File(LETTERS_SHEET);
        NotasParser parser = new OdsParser();
        parser.parseFile(sheetFile);
        ProfFile profFile = parser.parseFile(sheetFile);
        ProfSheet profSheet = profFile.getSheets().get(0);
        
        int expectTarjSize = OdsParser.MAX_ALUNOS;
        for (Tarjeta tarj: profSheet.getTarjetas()) {
            assertEquals(expectTarjSize, tarj.getNotas().size());
        }
    }
    
    @Test
    public void readSheetWithBlankLines() throws NotasParserException {
    
        File sheetFile = new File(BLANK_LINES_SHEET);
        NotasParser parser = new OdsParser();
        parser.parseFile(sheetFile);
        ProfFile profFile = parser.parseFile(sheetFile);
        ProfSheet profSheet = profFile.getSheets().get(0);
        
        int expectTarjSize = OdsParser.MAX_ALUNOS;
        for (Tarjeta tarj: profSheet.getTarjetas()) {
            assertEquals(expectTarjSize, tarj.getNotas().size());
        }
    }
}
