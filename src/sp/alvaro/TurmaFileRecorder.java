package sp.alvaro;

import java.util.Collection;

import sp.alvaro.model.TurmaFile;



public interface TurmaFileRecorder {

    public void record(Collection<TurmaFile> turmaFiles) throws Exception;
        
}
