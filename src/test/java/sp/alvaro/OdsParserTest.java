/**
 *  Copyright (C) 2012, Leonardo Leite
 *  This file is part of Notificador.
 *
 *  Notificador is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Notificador is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *   along with Notificador. If not, see <http://www.gnu.org/licenses/>.
 */
package sp.alvaro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import sp.alvaro.model.Conceito;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.odf.OdsProfParser;

/**
 * Este teste depende dos arquivos em test/resources
 * @author leonardo
 *
 */
public class OdsParserTest {

    public final static String SHEETS_DIR = "test_sheets";
    public final static String EXTENSION = "ods";
    public final static String LETTERS_SHEET = "bad_format/letras.ods";
    public final static String BLANK_LINES_SHEET = "bad_format/linhas_em_branco.ods";
    
    private static Logger logger = Logger.getLogger(OdsParserTest.class);
    
    private Set<File> files;
    private ProfFile expectedFile1, expectedFile2, currentFile1, currentFile2;
    
    @Before
    public void setUp() throws Exception {
        
        FilePicker picker = new FilePicker(SHEETS_DIR, EXTENSION);
        files = picker.pickFiles();
        
        // dados para comparação
        ValoresProf vals = new ValoresProf();
        expectedFile1 = vals.getProfSheet1();
        expectedFile2 = vals.getProfSheet2();  
        
        // dados calculados
        NotasParser parser = new OdsProfParser();
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

        File sheetFile = getFile(LETTERS_SHEET);
        NotasParser parser = new OdsProfParser();
        parser.parseFile(sheetFile);
        ProfFile profFile = parser.parseFile(sheetFile);
        ProfSheet profSheet = profFile.getSheets().get(0);
        
        int expectTarjSize = OdsProfParser.MAX_ALUNOS;
        for (Tarjeta tarj: profSheet.getTarjetas()) {
            assertEquals(expectTarjSize, tarj.getNotas().size());
        }
        
        Tarjeta tarj6a = profSheet.getTarjetas().get(0);
        Tarjeta tarj6b = profSheet.getTarjetas().get(1);
        Tarjeta tarj6c = profSheet.getTarjetas().get(2);

        Conceito c = tarj6a.getNotas().get(1);
        assertEquals("NC", c.getAlteracao());
        
        c = tarj6b.getNotas().get(2);
        assertEquals("AB", c.getAlteracao());

        c = tarj6c.getNotas().get(1);
        assertEquals("RM", c.getAlteracao());

        c = tarj6c.getNotas().get(2);
        assertEquals("NC", c.getAlteracao());
        
        c = tarj6a.getNotas().get(0);
        assertEquals(null, c.getAlteracao());
    }
    
    @Test
    public void readSheetWithBlankLines() throws NotasParserException {
    
        File sheetFile = getFile(BLANK_LINES_SHEET);
        NotasParser parser = new OdsProfParser();
        parser.parseFile(sheetFile);
        ProfFile profFile = parser.parseFile(sheetFile);
        ProfSheet profSheet = profFile.getSheets().get(0);
        
        int expectTarjSize = OdsProfParser.MAX_ALUNOS;
        for (Tarjeta tarj: profSheet.getTarjetas()) {
            assertEquals(expectTarjSize, tarj.getNotas().size());
        }
    }
    
    private File getFile(String fileName) {
		URL url = this.getClass().getClassLoader().getResource(fileName);
		if (url == null) {
			String msg = "Arquivo " + fileName + " não existe";
			logger.error(msg);
			throw new IllegalStateException(msg);
		}
		File file = new File(url.getFile());
		return file;
    }
}
