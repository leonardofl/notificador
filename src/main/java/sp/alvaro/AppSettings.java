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
package sp.alvaro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author leonardo
 */
public class AppSettings {
    
    private static String PROPERTIES_FILE = "properties";
    private Properties properties;
    
    public AppSettings() throws IOException {
    	FileLoader loader = new FileLoader(PROPERTIES_FILE);
        File file = loader.getFile();
        InputStream is = new FileInputStream(file);
        this.properties = new Properties();
        this.properties.load(is);
        is.close();
    } 
    
	public String getCurrentDirectoryIn() {
        return this.properties.getProperty("currentDirIn");
    }
    
    public String getCurrentDirectoryOut() {
        return this.properties.getProperty("currentDirOut");
    }

    public void setCurrentDirectoryIn(String dir) throws FileNotFoundException, IOException {
    	FileLoader loader = new FileLoader(PROPERTIES_FILE);
    	File file = loader.getFile();
        OutputStream os = new FileOutputStream(file);
        this.properties.setProperty("currentDirIn", dir);
        this.properties.store(os, "#app settings file");
        os.close();
    }

    public void setCurrentDirectoryOut(String dir) throws FileNotFoundException, IOException {
        FileLoader loader = new FileLoader(PROPERTIES_FILE);
    	File file = loader.getFile();
        OutputStream os = new FileOutputStream(file);
        this.properties.setProperty("currentDirOut", dir);
        this.properties.store(os, "#app settings file");
		os.close();
	}

}
