package sp.alvaro.debug;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import sp.alvaro.FilePicker;
import sp.alvaro.LogConfigurator;
import sp.alvaro.NotasParser;
import sp.alvaro.NotasParserException;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.odf.NullProgressListener;
import sp.alvaro.odf.OdsParser;

public class DebugHelper {
	
	public final static String EXTENSION = "ods";
	
	private Logger logger = Logger.getLogger(DebugHelper.class);

	/**
	 * Mostra em quais arquivos estão as turmas.
	 * Isso pode ajudar muito no debug do programa.
	 * @param dirPath onde estão as planilhas
	 * @throws IOException 
	 * @throws NotasParserException 
	 */
	public void listTurmasAndProfsAssociations(String dirPath) throws IOException, NotasParserException {
		
        FilePicker picker = new FilePicker(dirPath, EXTENSION);
        Set<File> files = picker.pickFiles();
        if (files.isEmpty()) {
        	String msg = "Sem planilhas em " + dirPath;
        	logger.error(msg);
        	throw new IOException(msg);
        }
        
        NotasParser parser = new OdsParser(new NullProgressListener());
        Set<ProfFile> profFiles = parser.parse(files);
        
        logger.info("** Lista de profs: turmas **");
        for (ProfFile profFile: profFiles) {
        	
			StringBuilder msg = new StringBuilder(profFile.getFileName()+ ": ");
        	Set<String> turmasDoProf = new HashSet<String>();
        	for (ProfSheet profSheet: profFile.getSheets()) {
        		for (Tarjeta tarj: profSheet.getTarjetas()) {
        			turmasDoProf.add(tarj.getTurma());
        		}
        	}
        	for (String turma: turmasDoProf) {
        		msg.append(turma + " ");
        	}
        	logger.info(msg);
        }
	}
	
	public static void main(String[] args) throws IOException, NotasParserException {
		
		LogConfigurator.configLog();
		String dir = "temp";
		DebugHelper debugHelper = new DebugHelper();
		debugHelper.listTurmasAndProfsAssociations(dir);
	}

}
