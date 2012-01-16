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
    
    private static String PROPERTIES_FILE = "resources/properties";
    private Properties properties;
    
    public AppSettings() throws IOException {
        
        File file = new File(PROPERTIES_FILE);
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
        File file = new File(PROPERTIES_FILE);
        OutputStream os = new FileOutputStream(file);
        this.properties.setProperty("currentDirIn", dir);
        this.properties.store(os, "#app settings file");
        os.close();
    }

    public void setCurrentDirectoryOut(String dir) throws FileNotFoundException, IOException {
        File file = new File(PROPERTIES_FILE);
        OutputStream os = new FileOutputStream(file);
        this.properties.setProperty("currentDirOut", dir);
        this.properties.store(os, "#app settings file");
        os.close();
    }

}
