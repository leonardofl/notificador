package sp.alvaro;

import java.util.HashMap;
import java.util.Map;

import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.TarjetaFaltasAnuais;
import sp.alvaro.model.TarjetaTurma;
import sp.alvaro.model.TurmaSheet;

public class NotasFinaisCalculator {
	
    /**
     * Faz o cálculo das faltas anuais
     * 
     * @param notasFinais 5a planilha, a das notas finais 
     * @return tarjeta com as faltas anuais
     */
    public TarjetaFaltasAnuais calculateFaltasAnuais(TurmaSheet notasFinais) {
    	
    	int totalAulas = 0;
    	Map<Aluno, Integer> totalFaltas = new HashMap<Aluno, Integer>();
    	
    	for (TarjetaTurma tarj: notasFinais.getTarjetas()) {
    		
    		totalAulas += tarj.getAulasDadas();
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
    	
    	TarjetaFaltasAnuais faltasAnuais = new TarjetaFaltasAnuais(totalAulas);
    	
    	for (Map.Entry<Aluno, Integer> entry : totalFaltas.entrySet()) {
    		
    	    Aluno aluno = entry.getKey();
    	    int faltas = entry.getValue();
    	    faltasAnuais.getFaltas().put(aluno, 100.0*faltas/totalAulas);
    	}
    	
    	return faltasAnuais;
    }

}
