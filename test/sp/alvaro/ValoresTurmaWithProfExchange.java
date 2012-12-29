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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sp.alvaro.model.Tarjeta;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.model.TurmaSheet;

/**
 * Esses valores são iguais aos gerados pelo ValoresTurma,
 * exceto que no último bimestre ROBERTO deu aula para
 * três turmas de MANOEL
 * 
 * @author leonardo
 *
 */
public class ValoresTurmaWithProfExchange {
	
	private static final String MANOEL = "MANOEL";
	private static final String ROBERTO = "ROBERTO";
	private List<String> turmasWithProfessorExchange = new ArrayList<String>();

	public ValoresTurmaWithProfExchange() {
		
		turmasWithProfessorExchange.add("6aB");
		turmasWithProfessorExchange.add("6aC");
		turmasWithProfessorExchange.add("6aD");
	}
	
    public Set<TurmaFile> generateData() {
        
    	ValoresTurma valoresOriginais = new ValoresTurma();
        Set<TurmaFile> turmaFiles = valoresOriginais.generateData();
        for (TurmaFile turmaFile: turmaFiles) {
        	if (turmasWithProfessorExchange.contains(turmaFile.getTurma())) {
        		TurmaSheet quartoBim = turmaFile.getSheets().get(3);
        		for (Tarjeta tarj: quartoBim.getTarjetas()) {
        			if (tarj.getProfessor().equals(MANOEL)) {
        				tarj.setProfessor(ROBERTO);
        			}
        		}
        	}
        }
        
        return turmaFiles;
    }
}
