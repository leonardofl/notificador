package sp.alvaro;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilePicker {
    
    private String dirName;
    private Pattern pattern;

    public FilePicker(String dirName, String extension) {
        
        this.dirName = dirName;
        this.pattern = Pattern.compile(".*?\\." + extension + "$"); // arquivo tem que terminar com ponto + extensão 
    }

    /**
     * Pega os arquivos no diretório de trabalho, no caso "resources"
     * @return a lista de arquivos do diretório "resources"
     */
    public Set<File> pickFiles() {
        
        File dir = new File(dirName);
        Set<File> files = new HashSet<File>();
        
        for (File f: dir.listFiles()) {
            Matcher matcher = pattern.matcher(f.getAbsolutePath());
            if (matcher.matches())
                files.add(f);
        }
        
        return files;
    }
}
