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

import java.util.HashSet;
import java.util.Set;

import sp.alvaro.model.Periodo;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.model.TurmaSheet;

/**
 * Gera as planilhas das turmas com base nas planilhas dos professores
 * @author leonardo
 *
 */
public class TurmaFileBuilder {
    
	// private Logger logger = Logger.getLogger(TurmaFileBuilder.class);
	
    private Set<TurmaFile> files;
   
    /**
     * Gera as planilhas das turmas com base nas planilhas dos professores
     * @param profFiles planilhas dos professores (uma planilha por professor)
     * @return planilhas das turmas (um arquivo por turma)
     */
    public Set<TurmaFile> buildTurmaFiles(Set<ProfFile> profFiles) throws TurmaFileBuilderException {
        
        files = new HashSet<TurmaFile>();

        for (ProfFile profFile: profFiles) {          
            for (ProfSheet profSheet: profFile.getSheets()) {
                for (Tarjeta tarj: profSheet.getTarjetas()) {
                    this.process(tarj, profSheet, profFile);
                }
                
            }
        }

        // calcula média final para cada turma
        FinalSheetGenerator generator = new FinalSheetGenerator();
        for (TurmaFile file: files) {
        	generator.generateFinalSheet(file);
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
        
        String prof = profTarj.getProfessor();
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
