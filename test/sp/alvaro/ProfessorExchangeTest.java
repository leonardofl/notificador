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

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sp.alvaro.model.ProfFile;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.model.TurmaSheet;
import sp.alvaro.odf.OdsParser;

/**
 * No último bimestre o prof MANOEL teve um problema de saúde, e pôde dar aula para penas uma sala.
 * As outras turmas foram assumidas pelo prof ROBERTO.
 * Vamos testar se o prof ROBERTO aparece nas tarjetas de saída.
 * Teste feito com as planilhas em test/resources/prof_exchange
 * 
 * @author leonardo
 *
 */
public class ProfessorExchangeTest {

    public final static String WORK_DIR = "test/resources/prof_exchange";
    public final static String EXTENSION = "ods";

    private Set<ProfFile> profFiles; 
    private Set<TurmaFile> currentTurmaFiles; 
    private Set<TurmaFile> expectedTurmaFiles;
    
    @Before
    public void setUp() throws IOException, TurmaFileBuilderException {
        
        FilePicker picker = new FilePicker(WORK_DIR, EXTENSION);
        Set<File> files = picker.pickFiles();

        NotasParser parser = new OdsParser();
        profFiles = null;
        try {
            profFiles = parser.parse(files);
        } catch (Exception e) {
            e.printStackTrace();
        }        

        TurmaFileBuilder builder = new TurmaFileBuilder();
        this.currentTurmaFiles = builder.buildTurmaFiles(profFiles);
        
        ValoresTurmaWithProfExchange val = new ValoresTurmaWithProfExchange();
        this.expectedTurmaFiles = val.generateData(); 
    }
    
    @Test
    public void professorRobertoShouldAppear() {
    	
    	for (TurmaFile turmaFile: this.currentTurmaFiles) {

    		TurmaFile expectedTurmaFile = findExpectedTurmaFile(turmaFile);
    		for (TurmaSheet turmaSheet: turmaFile.getSheets()) {

    			TurmaSheet expectedTurmaSheet = findExpectedTurmaSheet(expectedTurmaFile, turmaSheet);
    			for (Tarjeta tarj: turmaSheet.getTarjetas()) {
    				
    				String materia = tarj.getMateria();
    				Tarjeta expectedTarjeta = expectedTurmaSheet.findTarjeta(materia);
    				String expectedProf = expectedTarjeta.getProfessor();
    				String prof = tarj.getProfessor();
    				assertEquals(expectedProf, prof);
    			}
    		}
    	}
    }

	private TurmaSheet findExpectedTurmaSheet(TurmaFile expectedTurmaFile,
			TurmaSheet turmaSheet) {
		
		for (TurmaSheet ts: expectedTurmaFile.getSheets()) {
			if (ts.getBimestre().equals(turmaSheet.getBimestre()))
				return ts;
		}
		throw new IllegalArgumentException();
	}

	private TurmaFile findExpectedTurmaFile(TurmaFile turmaFile) {

		for (TurmaFile tf: this.expectedTurmaFiles) {
			if (tf.getTurma().equals(turmaFile.getTurma()))
				return tf;
		}
		throw new IllegalArgumentException();
	}
    
}