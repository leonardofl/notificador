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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.model.TurmaSheet;
import sp.alvaro.odf.OdsParser;

public class TurmaSheetBuilderTest {

    public final static String SHEETS_DIR = "test_sheets";
    public final static String EXTENSION = "ods";

    private Set<ProfFile> profFiles; 
    private Set<TurmaFile> currentTurmaFiles; 
    private Set<TurmaFile> expectedTurmaFiles;
    
    @Before
    public void setUp() throws IOException, TurmaFileBuilderException {
        
        FilePicker picker = new FilePicker(SHEETS_DIR, EXTENSION);
        Set<File> files = picker.pickFiles();

        NotasParser parser = new OdsParser();
        profFiles = null;
        try {
            profFiles = parser.parse(files);
        } catch (Exception e) {
            e.printStackTrace();
        }        

        TurmaFileBuilder builder = new TurmaFileBuilder();
        currentTurmaFiles = builder.buildTurmaFiles(profFiles);
        
        ValoresTurma val = new ValoresTurma();
        expectedTurmaFiles = val.generateData(); 
    }
    
    @Test
    public void shouldAssembleFourFilesWithSheetsAndStreeps() {
        
        // 4 turmas
        assertEquals(4, currentTurmaFiles.size());

        
        for (TurmaFile file: currentTurmaFiles) {
            // 4 bimestres + média anual
            assertEquals(5, file.getSheets().size());
            for (TurmaSheet sheet: file.getSheets()) {
                // dois profs (duas matérias)
                assertEquals(2, sheet.getTarjetas().size());
            }
        }
    }
    
    @Test
    public void verifyTurmas() {

        // gera expected
        List<String> expectedTurmas = new ArrayList<String>();
        for (TurmaFile tf: expectedTurmaFiles) {
            expectedTurmas.add(tf.getTurma());
        }
            
        // checa current \in expected
        List<String> currentTurmas = new ArrayList<String>();
        for (TurmaFile f: currentTurmaFiles) {
            currentTurmas.add(f.getTurma());
            assertTrue(expectedTurmas.contains(f.getTurma()));
        }

        // checa expected \in current 
        for (String t: expectedTurmas) {
            assertTrue(currentTurmas.contains(t));
        }
    }
    
    @Test
    public void verifyBimestres() {
        
        List<Periodo> expectedBimestres = 
                Arrays.asList(new Periodo[]{Periodo.BIMESTRE_1, Periodo.BIMESTRE_2, Periodo.BIMESTRE_3,
                                            Periodo.BIMESTRE_4, Periodo.ANO});
        
        for (TurmaFile f: currentTurmaFiles) {
            List<Periodo> currentBimestres = new ArrayList<Periodo>();
            for (TurmaSheet s: f.getSheets()) {
                currentBimestres.add(s.getBimestre());
            }
            assertArrayEquals(expectedBimestres.toArray(), currentBimestres.toArray());
        }   

    }
    
    @Test
    public void verifyProfessorMateria() {
        
        for (TurmaFile f: currentTurmaFiles) {

            TurmaFile expectedFile = null;
            for (TurmaFile tf: expectedTurmaFiles) {
                if (tf.getTurma().equals(f.getTurma()))
                    expectedFile = tf;
            }
            Map<String, String> profMap = new HashMap<String, String>();
            for (Tarjeta t: expectedFile.getSheets().get(0).getTarjetas()) {
                profMap.put(t.getProfessor(), t.getMateria());
            }
            
            for (TurmaSheet s: f.getSheets()) {
                for (Tarjeta t: s.getTarjetas()) {
                    assertTrue(profMap.keySet().contains(t.getProfessor()));
                    assertEquals(profMap.get(t.getProfessor()), t.getMateria());
                }
            }
        }   
    }
    
    @Test
    public void verifyConceitos() {
        
        for (TurmaFile f: currentTurmaFiles) {
            
            for (TurmaSheet sheet: f.getSheets()) {
                
                // bimestres
                if (sheet.getBimestre() != Periodo.ANO) {
                    for (Tarjeta tarj: sheet.getTarjetas()) {
                        for (Conceito nota: tarj.getNotas()) {
                            Conceito n = findNota(f.getTurma(), sheet.getBimestre(), tarj.getMateria(), tarj.getProfessor(), nota.getAluno());
                            if (nota != null && !nota.isNulo())
                            	assertEquals(nota, n);
                        }
                    }
                }
            }
            
            // final
            TurmaSheet finalSheet = f.getSheets().get(4);
            for (Tarjeta tarj: finalSheet.getTarjetas()) {
                for (Conceito nota: tarj.getNotas()) {
                    Conceito n = findNota(f.getTurma(), Periodo.ANO, tarj.getMateria(), tarj.getProfessor(), nota.getAluno());
                    if (n != null && !n.isNulo())
                    	assertEquals(n, nota);                    
                }
            }
        }
    }

    private Conceito findNota(String turma, Periodo bim, String materia, String prof, Aluno aluno) {
        
        TurmaFile file = null;
        for (TurmaFile tf: expectedTurmaFiles) {
            if (tf.getTurma().equals(turma))
                file = tf;
        }
        
        TurmaSheet sheet = null;
        for (TurmaSheet s: file.getSheets()) {
            if (s.getBimestre().equals(bim))
                sheet = s;
        }
        
        Tarjeta tarjeta = null;
        for (Tarjeta tarj: sheet.getTarjetas()) {
            if (tarj.getProfessor().equals(prof) && tarj.getMateria().equals(materia))
                tarjeta = tarj;
        }
        
        Conceito nota = null;
        for (Conceito n: tarjeta.getNotas()) {
            if (n.getAluno().equals(aluno))
                nota = n;
        }
        
        return nota;
    }
 
}
