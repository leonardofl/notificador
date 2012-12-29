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

import java.util.HashMap;
import java.util.Map;

import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.model.TarjetaFaltasAnuais;
import sp.alvaro.model.TurmaSheet;

public class FaltasAnuaisCalculator {
	
    /**
     * Faz o cálculo das faltas anuais
     * 
     * @param notasFinais 5a planilha, a das notas finais 
     * @return tarjeta com as faltas anuais
     */
    public TarjetaFaltasAnuais calculateFaltasAnuais(TurmaSheet notasFinais) {
    	
    	if (notasFinais.getTarjetas() == null || notasFinais.getTarjetas().isEmpty()) {
    		throw new IllegalArgumentException("Planilha de notas finais não possui tarjetas");
    	}
    	
    	int totalAulas = 0;
    	int previstas = 0;
    	Map<Aluno, Integer> totalFaltas = new HashMap<Aluno, Integer>();
    	
    	for (Tarjeta tarj: notasFinais.getTarjetas()) {
    		
    		totalAulas += tarj.getAulasDadas();
    		previstas += tarj.getAulasPrevistas();
    		for (Conceito conc: tarj.getNotas()) {
    			
    			Aluno aluno = conc.getAluno();
    			int faltas = 0;
    			if (totalFaltas.containsKey(aluno)) {
    				faltas = totalFaltas.get(aluno);
    			}
    			faltas += conc.getFaltas();
    			totalFaltas.put(aluno, faltas);
    		}
    	}
    	
    	String turma = notasFinais.getTarjetas().get(0).getNotas().get(0).getAluno().getTurma();
    	TarjetaFaltasAnuais faltasAnuais = new TarjetaFaltasAnuais(turma, totalAulas, previstas);
    	
    	for (Map.Entry<Aluno, Integer> entry : totalFaltas.entrySet()) {
    		
    	    Aluno aluno = entry.getKey();
    	    int faltas = entry.getValue();
    	    faltasAnuais.getFaltas().put(aluno, faltas);
    	}
    	
    	return faltasAnuais;
    }

}
