/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.alvaro.gui;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.log4j.Logger;

import sp.alvaro.FilePicker;
import sp.alvaro.NotasParser;
import sp.alvaro.NotasParserException;
import sp.alvaro.TurmaFileBuilder;
import sp.alvaro.TurmaFileBuilderException;
import sp.alvaro.TurmaFileRecorder;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.odf.OdsParser;
import sp.alvaro.odf.ProgressListener;
import sp.alvaro.odf.OdsRecorder;

/**
 *
 * @author leonardo
 */
public class ProcessHandler {
    
	Logger logger = Logger.getLogger(Form.class);

	public final static String EXTENSION = "ods";
    private String in, out;
    private Form form;
    
    public ProcessHandler(String in, String out, Form form) {
        
        this.in = in;
        this.out = out;
        this.form = form;
    }
    
    public void process() throws NotasParserException, IOException, TurmaFileBuilderException {
        
        FilePicker picker = new FilePicker(this.in, EXTENSION);
        Set<File> files = picker.pickFiles();
        if (files.isEmpty()) {
        	String msg = "Sem planilhas em " + this.in;
        	logger.error(msg);
        	throw new IOException(msg);
        }
        
        ProgressListener listener = new ProgressUpdater(this.form);
        NotasParser parser = new OdsParser(listener);
        Set<ProfFile> sheets = parser.parse(files);
        
        TurmaFileBuilder builder = new TurmaFileBuilder();
        Set<TurmaFile> turmaFiles = builder.buildTurmaFiles(sheets);
        File out_dir = new File(this.out);
        TurmaFileRecorder recorder = new OdsRecorder(out_dir);
        recorder.record(turmaFiles);  
        
    	logger.info("Execução completa");
    }
    
}
