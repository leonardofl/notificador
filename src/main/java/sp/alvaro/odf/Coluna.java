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
package sp.alvaro.odf;

/**
 * Representa o valor da coluna da planilha
 * 
 * O objetivo é encapsular a lógica de incremento da coluna
 * Ex: depois da coluna Z, vem a coluna AA
 * 
 * @author leonardo
 *
 */
public class Coluna {
    
    private String valor;
    
    /**
     * Requer letras maiúsculas
     * @param valor
     */
    public Coluna(String valor) {
        
        for (char c: valor.toCharArray()) {
            if (c < 'A' || c > 'Z')
                throw new IllegalArgumentException("Coluna deve ser composta por letras maiúsculas");
        }
        this.valor = valor;
    }
    
    public String getValor() {
        return this.valor;
    }
    
    public void setValor(String valor) {
        this.valor = valor;
    }    
    
    public void inc() {
        this.valor = this.incr(this.valor);
    }

    /**
     * Incrementa o valor <code>n</code> vezes
     * @param n deve ser maior ou igual a zero
     */
    public void inc(int n) {
        
        if (n < 0)
            throw new IllegalArgumentException("n não pode ser negativo");
        
        for (int i=0; i<n; i++)
            this.valor = this.incr(this.valor);
    }

    private String incr(String val) {
        
        char last = val.charAt(val.length()-1);
        if (last == 'Z') {
            String left = val.substring(0, val.length()-1);
            if (left.isEmpty()) {
                return "AA";
            } else {
                return incr(left).concat("A");
            }
        }
        else {
            last++;
            return val.substring(0, val.length()-1).concat(Character.toString(last));
        }        
    }

    public void dec() {
        this.valor = this.decr(this.valor);
    }

    /**
     * Incrementa o valor <code>n</code> vezes
     * @param n deve ser maior ou igual a zero
     */
    public void dec(int n) {
        
        if (n < 0)
            throw new IllegalArgumentException("n não pode ser negativo");
        
        for (int i=0; i<n; i++)
            this.valor = this.decr(this.valor);
    }

    private String decr(String val) {
        
        if (val == "")
            throw new IllegalArgumentException("Argumento do decremento foi muito grande");
        
        char last = val.charAt(val.length()-1);
        String left = val.substring(0, val.length()-1);
        if (last == 'A') {
            if (left.isEmpty()) {
                return "";
            } else {
                return decr(left).concat("Z");
            }
        }
        else {
            last--;
            return left.concat(Character.toString(last));
        }        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coluna other = (Coluna) obj;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.valor;
    }
}
