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
        
    	long t0 = System.currentTimeMillis();
    	
        FilePicker picker = new FilePicker(this.in, EXTENSION);
        Set<File> files = picker.pickFiles();
        if (files.isEmpty()) {
        	String msg = "Sem planilhas em " + this.in;
        	logger.error(msg);
        	throw new IOException(msg);
        }
        
        ProgressListener listener = new ReadingProgressUpdater(this.form);
        NotasParser parser = new OdsParser(listener);
        Set<ProfFile> sheets = parser.parse(files);
        
        TurmaFileBuilder builder = new TurmaFileBuilder();
        Set<TurmaFile> turmaFiles = builder.buildTurmaFiles(sheets);
        File out_dir = new File(this.out);
        listener = new WritingProgressUpdater(this.form);
        TurmaFileRecorder recorder = new OdsRecorder(out_dir, listener);
        recorder.record(turmaFiles);  
        
        long tf = System.currentTimeMillis();
        double delstaMin = (tf - t0) / 1000.0 / 60.0;
    	logger.info("Execução completa em " + (delstaMin) + " min.");
    }
    
}
