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
    				System.out.println(expectedProf + " - " + prof);
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