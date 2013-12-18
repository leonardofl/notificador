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
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FilePickerTest {

    public final static String FOLDER = "picker_test";
    public final static String EXTENSION = "ods";

    @Test
    public void test() throws IOException {

        FilePicker picker = new FilePicker(FOLDER, EXTENSION);
        Set<File> files = picker.pickFiles();
        
        assertEquals(2, files.size());

        Set<String> names = new HashSet<String>();
        for (File f: files) 
           names.add(f.getName());
                
        assertTrue(names.contains("fake1.ods"));
        assertTrue(names.contains("fake2.ods"));
    }

}
