package sp.alvaro;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.TarjetaFaltasAnuais;
import sp.alvaro.model.TarjetaTurma;
import sp.alvaro.model.TurmaSheet;

public class FaltasAnuaisCalcTest {

	private List<TarjetaTurma> tarjetas;
	private TarjetaFaltasAnuais expectedFaltasAnuais;
	
	@Before
	public void setUp() {
		
        // prepara dados
        tarjetas = new ArrayList<TarjetaTurma>();
        
        TarjetaTurma tarj = new TarjetaTurma("Matemática", "Areco", 22, 23);
        tarj.getNotas().add(new Conceito(new Aluno("A", "6aA"), 10, 2));
        tarj.getNotas().add(new Conceito(new Aluno("B", "6aA"), 5, 5));
        tarj.getNotas().add(new Conceito(new Aluno("C", "6aA"), 2, 10));
        tarjetas.add(tarj);

        tarj = new TarjetaTurma("Inglês", "Thales", 22, 23);
        tarj.getNotas().add(new Conceito(new Aluno("A", "6aA"), 8, 3));
        tarj.getNotas().add(new Conceito(new Aluno("B", "6aA"), 6, 6));
        tarj.getNotas().add(new Conceito(new Aluno("C", "6aA"), 4, 7));
        tarjetas.add(tarj);

        
        expectedFaltasAnuais = new TarjetaFaltasAnuais("6aA", 44, 46);
        expectedFaltasAnuais.getFaltas().put(new Aluno("A", "6aA"), 11.36d);
        expectedFaltasAnuais.getFaltas().put(new Aluno("B", "6aA"), 25d);
        expectedFaltasAnuais.getFaltas().put(new  Aluno("C", "6aA"), 38.64d);
	}
	
	@Test
	public void testCalculaNotasFinais() {

		TurmaSheet notasFinais = new TurmaSheet(Periodo.ANO);
		notasFinais.setTarjetas(tarjetas);
		FaltasAnuaisCalculator calc = new FaltasAnuaisCalculator();
		TarjetaFaltasAnuais faltasAnuais = calc.calculateFaltasAnuais(notasFinais);
		
		assertEquals(expectedFaltasAnuais.getAulasDadas(), faltasAnuais.getAulasDadas());
		assertEquals(expectedFaltasAnuais.getAulasPrevistas(), faltasAnuais.getAulasPrevistas());
		assertEquals(expectedFaltasAnuais.getTurma(), faltasAnuais.getTurma());
		assertEquals(expectedFaltasAnuais.getFaltas().size(), faltasAnuais.getFaltas().size());
		
		for (Aluno aluno: faltasAnuais.getFaltas().keySet()) {
			
			assertEquals(expectedFaltasAnuais.getFaltas().get(aluno), 
					faltasAnuais.getFaltas().get(aluno), 0.01);  
		}
	}
}
