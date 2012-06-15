package sp.alvaro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import sp.alvaro.model.Periodo;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.model.TarjetaFaltasAnuais;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.model.TurmaSheet;

/**
 * Gera as planilhas das turmas com base nas planilhas dos professores
 * @author leonardo
 *
 */
public class TurmaFileBuilder {
    
	private Logger logger = Logger.getLogger(TurmaFileBuilder.class);
	
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
                for (Tarjeta tarj: profSheet.getTarjetas()) {
                    this.process(tarj, profSheet, profFile);
                }
                
            }
        }

        // calcula média final
        MediaCalculator calc = new MediaCalculator();
        for (TurmaFile file: files) {
            TurmaSheet finalSheet = new TurmaSheet(Periodo.ANO, file.getTurma());
            
            // iterando matérias
            for (int i=0; i<file.getSheets().get(0).getTarjetas().size(); i++) {
                
                List<Tarjeta> bimestres = new ArrayList<Tarjeta>();
                for (TurmaSheet sheet: file.getSheets()) {
                	try {
                		bimestres.add(sheet.getTarjetas().get(i));
                	} catch(IndexOutOfBoundsException e) {
						String message = "Não consegui processar a tarjeta "
								+ i + " da planilha " + sheet.getBimestre()
								+ " da turma " + file.getTurma();
                		logger.error(message, e);
                	}
                }
                Tarjeta tarjFinal = calc.calculateMedia(bimestres);
                finalSheet.getTarjetas().add(tarjFinal);
            }

            // calcula notas anuais
            FaltasAnuaisCalculator fcalc = new FaltasAnuaisCalculator();
            TarjetaFaltasAnuais faltasAnuais = fcalc.calculateFaltasAnuais(finalSheet);
            file.setFaltasAnuais(faltasAnuais);
            
            file.getSheets().add(finalSheet);
        }
        
        return files;
    }
    
    private void process(Tarjeta profTarj, ProfSheet profSheet, ProfFile profFile) {

        String turma = profTarj.getTurma();
        String materia = profTarj.getMateria();
        TurmaFile file = turmaInFiles(turma);
        if (file == null) {
            file = new TurmaFile(turma);
            this.files.add(file);
        }
        
        Periodo bim = profSheet.getBimestre();
        TurmaSheet sheet = sheetInFile(bim, file);
        if (sheet == null) {
            sheet = new TurmaSheet(bim, turma);
            file.getSheets().add(sheet);
        }
        
        String prof = profFile.getProfessor();
        int aulasDadas = profTarj.getAulasDadas();
        int aulasPrevistas = profTarj.getAulasPrevistas();
        Tarjeta tarjeta = tarjetaInSheet(prof, materia, sheet);
        if (tarjeta == null) {
            tarjeta = new Tarjeta(turma, materia, prof, bim, aulasDadas, aulasPrevistas);
            sheet.getTarjetas().add(tarjeta);
        }
        
        tarjeta.setNotas(profTarj.getNotas());
    }
    
    private Tarjeta tarjetaInSheet(String prof, String materia, TurmaSheet sheet) {

        for (Tarjeta t: sheet.getTarjetas()) {
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
