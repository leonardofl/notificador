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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sp.alvaro.odf.Coluna;

public class ColunaTest {


    @Test
    public void testInc() {
        Coluna c = new Coluna("S");
        c.inc();
        assertEquals("T", c.getValor());
        
        c.setValor("Z");
        c.inc();
        assertEquals("AA", c.getValor());

        c.setValor("AC");
        c.inc();
        assertEquals("AD", c.getValor());

        c.setValor("AZ");
        c.inc();
        assertEquals("BA", c.getValor());

        c.setValor("ZZ");
        c.inc();
        assertEquals("AAA", c.getValor());
    }
    
    @Test
    public void testIncN() {
        Coluna c = new Coluna("S");
        c.inc(3);
        assertEquals("V", c.getValor());

        c.setValor("AZ");
        c.inc(2);
        assertEquals("BB", c.getValor());
    }
    
    @Test
    public void testDec() {
        Coluna c = new Coluna("T");
        c.dec();
        assertEquals("S", c.getValor());
        
        c.setValor("AA");
        c.dec();
        assertEquals("Z", c.getValor());

        c.setValor("AD");
        c.dec();
        assertEquals("AC", c.getValor());

        c.setValor("BA");
        c.dec();
        assertEquals("AZ", c.getValor());

        c.setValor("AAA");
        c.dec();
        assertEquals("ZZ", c.getValor());
    }
    
    
    @Test
    public void testDecN() {
        Coluna c = new Coluna("V");
        c.dec(3);
        assertEquals("S", c.getValor());

        c.setValor("BB");
        c.dec(2);
        assertEquals("AZ", c.getValor());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testConstructorException() {
        
        new Coluna("A*7BC");
    }

}
