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

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class FilePicker {
    
	private static Logger logger = Logger.getLogger(FilePicker.class);
	
    private String dirName;
    private String extension;

    public FilePicker(String dirName, String extension) {
        this.dirName = dirName;
        this.extension = extension;
    }

    /**
     * Pega os arquivos no diretório de trabalho, no caso "resources"
     * @return a lista de arquivos do diretório "resources"
     * @throws IOException se dirName não existir
     */
    public Set<File> pickFiles() throws IOException {
    	File dir = getDir();
        Set<File> files = new HashSet<File>();
        FileFilter filter = getFileFilterByExtension();
        for (File f: dir.listFiles(filter)) {
                files.add(f);
        }
        return files;
    }

	private File getDir() throws IOException {
		FileLoader loader = new FileLoader(dirName);
		File dir = loader.getFile();
        if (!dir.isDirectory()) {
        	String msg = dirName + " não é diretório";
        	logger.error(msg);
        	throw new IOException(msg);
        }
		return dir;
	}
	
	private FileFilter getFileFilterByExtension() {
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith("." + extension);
            }
        };		
        return filter;
	}
}
