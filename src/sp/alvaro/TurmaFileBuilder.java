package sp.alvaro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sp.alvaro.model.Periodo;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.TarjetaProf;
import sp.alvaro.model.TarjetaTurma;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.model.TurmaSheet;

/**
 * Gera as planilhas das turmas com base nas planilhas dos professores
 * @author leonardo
 *
 */
public class TurmaFileBuilder {
    
    private Set<TurmaFile> files;
   
    /**
     * Gera as planilhas das turmas com base nas planilhas dos professores
     * @param profFiles planilhas dos professores (uma planilha por professor)
     * @return planilhas das turmas (um arquivo por turma)
     */
    public Set<TurmaFile> buildTurmaFiles(Set<ProfFile> profFiles) {
        
        files = new HashSet<TurmaFile>();

        for (ProfFile profFile: profFiles) {          
            for (ProfSheet profSheet: profFile.getSheets()) {
                for (TarjetaProf tarj: profSheet.getTarjetas()) {
                    this.process(tarj, profSheet, profFile);
                }
                
            }
        }

        // calcula média final
        MediaCalculator calc = new MediaCalculator();
        for (TurmaFile file: files) {
            TurmaSheet finalSheet = new TurmaSheet(Periodo.ANO);
            
            // iterando matérias
            for (int i=0; i<file.getSheets().get(0).getTarjetas().size(); i++) {
                
                List<TarjetaTurma> bimestres = new ArrayList<TarjetaTurma>();
                for (TurmaSheet sheet: file.getSheets()) {
                    bimestres.add(sheet.getTarjetas().get(i));
                }
                TarjetaTurma tarjFinal = calc.calculateMedia(bimestres);
                finalSheet.getTarjetas().add(tarjFinal);
            }

            file.getSheets().add(finalSheet);
        }
        
        return files;
    }
    
    private void process(TarjetaProf profTarj, ProfSheet profSheet, ProfFile profFile) {

        String turma = profTarj.getTurma();
        TurmaFile file = turmaInFiles(turma);
        if (file == null) {
            file = new TurmaFile(turma);
            this.files.add(file);
        }
        
        Periodo bim = profSheet.getBimestre();
        TurmaSheet sheet = sheetInFile(bim, file);
        if (sheet == null) {
            sheet = new TurmaSheet(bim);
            file.getSheets().add(sheet);
        }
        
        String prof = profFile.getProfessor();
        String materia = profFile.getMateria();
        int aulasDadas = profTarj.getAulasDadas();
        int aulasPrevistas = profTarj.getAulasPrevistas();
        TarjetaTurma tarjeta = tarjetaInSheet(prof, materia, sheet);
        if (tarjeta == null) {
            tarjeta = new TarjetaTurma(materia, prof, aulasDadas, aulasPrevistas);
            sheet.getTarjetas().add(tarjeta);
        }
        
        tarjeta.setNotas(profTarj.getNotas());
    }
    
    private TarjetaTurma tarjetaInSheet(String prof, String materia, TurmaSheet sheet) {

        for (TarjetaTurma t: sheet.getTarjetas()) {
            if (t.getProfessor().equals(prof) && t.getMateria().equals(materia))
                return t;
        }
        return null;
    }

    private TurmaSheet sheetInFile(Periodo bimestre, TurmaFile file) {

        for (TurmaSheet s: file.getSheets()) {
            if (s.getBimestre() == bimestre)
                return s;
        }
        return null;
    }

    private TurmaFile turmaInFiles(String turma) {
        
        for (TurmaFile f: this.files) {
            if (f.getTurma().equals(turma))
                return f;
        }
        return null;
    }

}
