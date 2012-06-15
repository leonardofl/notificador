package sp.alvaro;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.model.TarjetaFaltasAnuais;
import sp.alvaro.model.TurmaSheet;

public class FaltasAnuaisCalcTest {

	private List<Tarjeta> tarjetas;
	private TarjetaFaltasAnuais expectedFaltasAnuais;

	private String trm = "6aA";
	private Periodo bim = Periodo.BIMESTRE_1;

	@Before
	public void setUp() {
		
        // prepara dados
        tarjetas = new ArrayList<Tarjeta>();
        
        Tarjeta tarj = new Tarjeta(trm, "Matemática", "Areco", bim, 22, 23);
        tarj.getNotas().add(new Conceito(new Aluno("A", "6aA"), 10, 2));
        tarj.getNotas().add(new Conceito(new Aluno("B", "6aA"), 5, 5));
        tarj.getNotas().add(new Conceito(new Aluno("C", "6aA"), 2, 10));
        tarjetas.add(tarj);

        tarj = new Tarjeta(trm, "Inglês", "Thales",  bim, 22, 23);
        tarj.getNotas().add(new Conceito(new Aluno("A", "6aA"), 8, 3));
        tarj.getNotas().add(new Conceito(new Aluno("B", "6aA"), 6, 6));
        tarj.getNotas().add(new Conceito(new Aluno("C", "6aA"), 4, 7));
        tarjetas.add(tarj);

        
        expectedFaltasAnuais = new TarjetaFaltasAnuais("6aA", 44, 46);
        expectedFaltasAnuais.getFaltas().put(new Aluno("A", "6aA"), 5);
        expectedFaltasAnuais.getFaltas().put(new Aluno("B", "6aA"), 11);
        expectedFaltasAnuais.getFaltas().put(new  Aluno("C", "6aA"), 17);
	}
	
	@Test
	public void testCalculaNotasFinais() {

		TurmaSheet notasFinais = new TurmaSheet(Periodo.ANO, trm);
		notasFinais.setTarjetas(tarjetas);
		FaltasAnuaisCalculator calc = new FaltasAnuaisCalculator();
		TarjetaFaltasAnuais faltasAnuais = calc.calculateFaltasAnuais(notasFinais);
		
		assertEquals(expectedFaltasAnuais.getAulasDadas(), faltasAnuais.getAulasDadas());
		assertEquals(expectedFaltasAnuais.getAulasPrevistas(), faltasAnuais.getAulasPrevistas());
		assertEquals(expectedFaltasAnuais.getTurma(), faltasAnuais.getTurma());
		assertEquals(expectedFaltasAnuais.getFaltas().size(), faltasAnuais.getFaltas().size());
		
		for (Aluno aluno: faltasAnuais.getFaltas().keySet()) {
			
			assertEquals(expectedFaltasAnuais.getFaltas().get(aluno), 
					faltasAnuais.getFaltas().get(aluno));  
		}
	}
}
