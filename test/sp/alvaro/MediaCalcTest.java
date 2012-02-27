package sp.alvaro;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.TarjetaTurma;

public class MediaCalcTest {

    private List<TarjetaTurma> tarjetas;
    private TarjetaTurma expectedMedias;
    
    @Before
    public void setUp() throws Exception {
        
        // prepara dados
        tarjetas = new ArrayList<TarjetaTurma>();
        
        TarjetaTurma bim1 = new TarjetaTurma("Matemática", "Areco", 22, 23);
        bim1.getNotas().add(new Conceito(new Aluno("A", "6aA"), 10, 2));
        bim1.getNotas().add(new Conceito(new Aluno("B", "6aA"), 5, 5));
        bim1.getNotas().add(new Conceito(new Aluno("C", "6aA"), 2, 10));
        tarjetas.add(bim1);

        TarjetaTurma bim2 = new TarjetaTurma("Matemática", "Areco", 22, 23);
        bim2.getNotas().add(new Conceito(new Aluno("A", "6aA"), 8, 3));
        bim2.getNotas().add(new Conceito(new Aluno("B", "6aA"), 6, 6));
        bim2.getNotas().add(new Conceito(new Aluno("C", "6aA"), 4, 7));
        tarjetas.add(bim2);

        TarjetaTurma bim3 = new TarjetaTurma("Matemática", "Areco", 22, 23);
        bim3.getNotas().add(new Conceito(new Aluno("A", "6aA"), 9, 1));
        bim3.getNotas().add(new Conceito(new Aluno("B", "6aA"), 4, 5));
        bim3.getNotas().add(new Conceito(new Aluno("C", "6aA"), 5, 6));
        tarjetas.add(bim3);

        TarjetaTurma bim4 = new TarjetaTurma("Matemática", "Areco", 22, 23);
        bim4.getNotas().add(new Conceito(new Aluno("A", "6aA"), 10, 1));
        bim4.getNotas().add(new Conceito(new Aluno("B", "6aA"), 8, 2));
        bim4.getNotas().add(new Conceito(new Aluno("C", "6aA"), 3, 6));
        tarjetas.add(bim4);
        
        expectedMedias = new TarjetaTurma("Matemática", "Areco", 88, 23*4);
        expectedMedias.getNotas().add(new Conceito(new Aluno("A", "6aA"), 37/4d, 7));
        expectedMedias.getNotas().add(new Conceito(new Aluno("B", "6aA"), 23/4d, 18));
        expectedMedias.getNotas().add(new Conceito(new Aluno("C", "6aA"), 14/4d, 29));
    }

    @Test
    public void test() {
        
        MediaCalculator calc = new MediaCalculator();
        TarjetaTurma currentMedias = calc.calculateMedia(tarjetas);
        assertEquals(expectedMedias, currentMedias);
    }

}
