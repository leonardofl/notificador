package sp.alvaro;

import java.io.File;
import java.util.Set;

import org.apache.log4j.Logger;

import sp.alvaro.model.ProfFile;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.odf.OdsParser;
import sp.alvaro.odf.OdsRecorder;

public class AlvaroMain {

    public final static String WORK_DIR = "temp/tarjetas";
    public final static File OUT_DIR = new File("temp/out");
    public final static String EXTENSION = "ods";

    public static void main(String[] args) throws Exception {
    	
		LogConfigurator.configLog();
    	Logger logger = Logger.getLogger(AlvaroMain.class);
    	logger.info("Log configurado");

        FilePicker picker = new FilePicker(WORK_DIR, EXTENSION);
        Set<File> files = picker.pickFiles();
        NotasParser parser = new OdsParser();
        Set<ProfFile> sheets = parser.parse(files);
        
        TurmaFileBuilder builder = new TurmaFileBuilder();
        Set<TurmaFile> turmaFiles = builder.buildTurmaFiles(sheets);
        
        TurmaFileRecorder recorder = new OdsRecorder(OUT_DIR);
        recorder.record(turmaFiles);
        
    	logger.info("Execução completa");
    }

}
