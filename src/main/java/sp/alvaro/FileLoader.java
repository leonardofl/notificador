package sp.alvaro;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;

public class FileLoader {
	
	private static Logger logger = Logger.getLogger(FileLoader.class);

	private String fileName;

	public FileLoader(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() throws IOException {
		if (isAbsolutPath())
			return getFileWithAbsolutPath();
		else
			return getFileFromClassPath();
	}
	
	private boolean isAbsolutPath() {
		// TODO Windows!
		if (fileName.startsWith("/")) 
			return true;
		else 
			return false;
	}
	
	private File getFileWithAbsolutPath() throws IOException {
		File file = new File(fileName);
		if (!file.exists())
			fail();
		return file;
	}
	
	private File getFileFromClassPath() throws IOException {
		URL url = this.getClass().getClassLoader().getResource(fileName);
		if (url == null) 
			fail();
		File file = new File(url.getFile());
		return file;
	}

	private void fail() throws IOException {
		String msg = fileName + " não existe";
		logger.error(msg);
		throw new IOException(msg);
	}
	

}