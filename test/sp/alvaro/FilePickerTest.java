package sp.alvaro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FilePickerTest {

    public final static String WORK_DIR = "test/resources";
    public final static String EXTENSION = "ods";

    @Test
    public void test() throws IOException {

        FilePicker picker = new FilePicker(WORK_DIR, EXTENSION);
        Set<File> files = picker.pickFiles();
        
        assertEquals(2, files.size());

        Set<String> names = new HashSet<String>();
        for (File f: files) 
           names.add(f.getName());
                
        assertTrue(names.contains("prof1.ods"));
        assertTrue(names.contains("prof2.ods"));
    }

}
