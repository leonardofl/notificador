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
