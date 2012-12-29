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

import org.apache.log4j.PropertyConfigurator;


public class LogConfigurator {

	private static String LOG_CONFIG_FILE = "resources/log.config";
	private static String LOG_FILE = "notificador.log";
	
	/**
	 * Configures log4j, erasing any previous log
	 */
	public static void configLog() {
		
		File log = new File(LOG_FILE);
		log.delete();
    	PropertyConfigurator.configure(LOG_CONFIG_FILE);
	}
}
