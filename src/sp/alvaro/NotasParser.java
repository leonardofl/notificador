package sp.alvaro;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import sp.alvaro.model.ProfFile;

public interface NotasParser {

    public Set<ProfFile> parse(Collection<File> files) throws IOException;
    
    /**
     * Recupera notas a partir de um arquivo feito pelo professor
     * @param file
     * @return
     * @throws IOException 
     */
    public ProfFile parseFile(File file) throws IOException;
}
