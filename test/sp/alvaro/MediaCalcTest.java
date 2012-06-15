package sp.alvaro;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.Tarjeta;

public class MediaCalcTest {

    private List<Tarjeta> tarjetas;
    private Tarjeta expectedMedias;
    
	private String trm = "6aA";
	
    @Before
    public void setUp() throws Exception {
        
        // prepara dados
        tarjetas = new ArrayList<Tarjeta>();
        
        Tarjeta bim1 = new Tarjeta(trm, "Matemática", "Areco", Periodo.BIMESTRE_1, 22, 23);
        bim1.getNotas().add(new Conceito(new Aluno("A", "6aA"), 10, 2));
        bim1.getNotas().add(new Conceito(new Aluno("B", "6aA"), 5, 5));
        bim1.getNotas().add(new Conceito(new Aluno("C", "6aA"), 2, 10));
        tarjetas.add(bim1);

        Tarjeta bim2 = new Tarjeta(trm, "Matemática", "Areco", Periodo.BIMESTRE_2, 22, 23);
        bim2.getNotas().add(new Conceito(new Aluno("A", "6aA"), 8, 3));
        bim2.getNotas().add(new Conceito(new Aluno("B", "6aA"), 6, 6));
        bim2.getNotas().add(new Conceito(new Aluno("C", "6aA"), 4, 7));
        tarjetas.add(bim2);

        Tarjeta bim3 = new Tarjeta(trm, "Matemática", "Areco", Periodo.BIMESTRE_3, 22, 23);
        bim3.getNotas().add(new Conceito(new Aluno("A", "6aA"), 9, 1));
        bim3.getNotas().add(new Conceito(new Aluno("B", "6aA"), 4, 5));
        bim3.getNotas().add(new Conceito(new Aluno("C", "6aA"), 5, 6));
        tarjetas.add(bim3);

        Tarjeta bim4 = new Tarjeta(trm, "Matemática", "Areco", Periodo.BIMESTRE_4, 22, 23);
        bim4.getNotas().add(new Conceito(new Aluno("A", "6aA"), 10, 1));
        bim4.getNotas().add(new Conceito(new Aluno("B", "6aA"), 8, 2));
        bim4.getNotas().add(new Conceito(new Aluno("C", "6aA"), 3, 6));
        tarjetas.add(bim4);
        
        expectedMedias = new Tarjeta(trm, "Matemática", "Areco", Periodo.ANO, 88, 23*4);
        expectedMedias.getNotas().add(new Conceito(new Aluno("A", "6aA"), 37/4d, 7));
        expectedMedias.getNotas().add(new Conceito(new Aluno("B", "6aA"), 23/4d, 18));
        expectedMedias.getNotas().add(new Conceito(new Aluno("C", "6aA"), 14/4d, 29));
    }

    @Test
    public void test() {
        
        MediaCalculator calc = new MediaCalculator();
        Tarjeta currentMedias = calc.calculateMedia(tarjetas);
        assertEquals(expectedMedias, currentMedias);
    }

}
