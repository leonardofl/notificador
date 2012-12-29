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
package sp.alvaro.model;

public enum Periodo {

    
    BIMESTRE_1("1º bimestre", 1), BIMESTRE_2("2º bimestre", 2), 
    BIMESTRE_3("3º bimestre", 3), BIMESTRE_4("4º bimestre", 4), 
    ANO("Média final", 5);

    private String str;
    private int value;
    
    private Periodo(String str, int value) {
        this.str = str;
        this.value = value;
    }
    
    public String toString() {
        return this.str;
    }
    
    public int toInt() {
        return this.value;
    }
    
    public static Periodo valueOf(int bim) {
        
        switch(bim) {
            case 1: return BIMESTRE_1;
            case 2: return BIMESTRE_2;
            case 3: return BIMESTRE_3;
            case 4: return BIMESTRE_4;
            case 5: return ANO;
            default: throw new IllegalArgumentException("Bimestre deve ser entre 1 e 5");
        }
    }
}
