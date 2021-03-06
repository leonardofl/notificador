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
        System.out.println(bim1.getNotas());
        bim1.getNotas().add(new Conceito(new Aluno("A", trm), 10, 2));
        bim1.getNotas().add(new Conceito(new Aluno("B", trm), 5, 5));
        bim1.getNotas().add(new Conceito(new Aluno("C", trm), 2, 10));
        tarjetas.add(bim1);

        Tarjeta bim2 = new Tarjeta(trm, "Matemática", "Areco", Periodo.BIMESTRE_2, 22, 23);
        bim2.getNotas().add(new Conceito(new Aluno("A", trm), 8, 3));
        bim2.getNotas().add(new Conceito(new Aluno("B", trm), 6, 6));
        bim2.getNotas().add(new Conceito(new Aluno("C", trm), 4, 7));
        tarjetas.add(bim2);

        Tarjeta bim3 = new Tarjeta(trm, "Matemática", "Areco", Periodo.BIMESTRE_3, 22, 23);
        bim3.getNotas().add(new Conceito(new Aluno("A", trm), 9, 1));
        bim3.getNotas().add(new Conceito(new Aluno("B", trm), 4, 5));
        bim3.getNotas().add(new Conceito(new Aluno("C", trm), 5, 6));
        tarjetas.add(bim3);

        Tarjeta bim4 = new Tarjeta(trm, "Matemática", "Areco", Periodo.BIMESTRE_4, 22, 23);
        bim4.getNotas().add(new Conceito(new Aluno("A", trm), 10, 1));
        bim4.getNotas().add(new Conceito(new Aluno("B", trm), 8, 2));
        bim4.getNotas().add(new Conceito(new Aluno("C", trm), 3, 6));
        tarjetas.add(bim4);
        
        expectedMedias = new Tarjeta(trm, "Matemática", "Areco", Periodo.ANO, 88, 23*4);
        expectedMedias.getNotas().add(new Conceito(new Aluno("A", trm), 37/4d, 7));
        expectedMedias.getNotas().add(new Conceito(new Aluno("B", trm), 23/4d, 18));
        expectedMedias.getNotas().add(new Conceito(new Aluno("C", trm), 14/4d, 29));
    }

    @Test
    public void test() {
        
        MediaCalculator calc = new MediaCalculator();
        Tarjeta currentMedias = calc.calculateMedia(tarjetas);
        assertEquals(expectedMedias, currentMedias);
        assertTrue(expectedMedias.notasEquals(currentMedias));
    }

}
