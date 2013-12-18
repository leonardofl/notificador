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
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class LogConfigurator {

	private static String LOG_CONFIG_FILE = "log.config";
	private static String LOG_FILE = "notificador.log";

	/**
	 * Configures log4j, erasing any previous log
	 */
	public static void initLog() {
		clearPastLog();
		configLog();
	}
	
    private static void clearPastLog() {
		File log = new File(LOG_FILE);
		log.delete();		
	}

	private static void configLog() {
		ClassLoader loader = Thread.currentThread()
				.getContextClassLoader();
		Properties logProperties = new Properties();
		try {
			InputStream is = loader.getResourceAsStream(LOG_CONFIG_FILE);
			if (is != null) {
				logProperties.load(is);
				PropertyConfigurator.configure(logProperties);
			} else {
				basicConfiguration();
			}
		} catch (IOException e) {
			basicConfiguration();
		}
	}
	
	public static void basicConfiguration() {
        System.out.println("Let's use basic log.");
        BasicConfigurator.configure();
    }
}
