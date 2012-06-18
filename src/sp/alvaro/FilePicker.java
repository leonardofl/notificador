package sp.alvaro;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class FilePicker {
    
	private Logger logger = Logger.getLogger(FilePicker.class);
	
    private String dirName;
    private Pattern pattern;

    public FilePicker(String dirName, String extension) {
        
        this.dirName = dirName;
        this.pattern = Pattern.compile(".*?\\." + extension + "$"); // arquivo tem que terminar com ponto + extensão 
    }

    /**
     * Pega os arquivos no diretório de trabalho, no caso "resources"
     * @return a lista de arquivos do diretório "resources"
     * @throws IOException se dirName não existir
     */
    public Set<File> pickFiles() throws IOException {
        
        File dir = new File(dirName);
        if (!dir.exists()) {
        	String msg = "Pasta " + dirName + " não existe";
        	logger.error(msg);
        	throw new IOException(msg);
        }
        
        Set<File> files = new HashSet<File>();
        for (File f: dir.listFiles()) {
            Matcher matcher = pattern.matcher(f.getAbsolutePath());
            if (matcher.matches())
                files.add(f);
        }
        
        return files;
    }
}
