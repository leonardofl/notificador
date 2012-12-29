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

import java.util.ArrayList;
import java.util.List;

public class TurmaFile {

    private String turma;
    private List<TurmaSheet> sheets;
    private TarjetaFaltasAnuais faltasAnuais;
    
    public TurmaFile() {
        this.sheets = new ArrayList<TurmaSheet>();        
    }

    public TurmaFile(String turma) {
        this.sheets = new ArrayList<TurmaSheet>();        
        this.turma = turma;
    }

    public String getTurma() {
        return turma;
    }

    public List<TurmaSheet> getSheets() {
        return sheets;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setSheets(List<TurmaSheet> sheets) {
        this.sheets = sheets;
    }
    
    public TarjetaFaltasAnuais getFaltasAnuais() {
		return faltasAnuais;
	}

	public void setFaltasAnuais(TarjetaFaltasAnuais faltasAnuais) {
		this.faltasAnuais = faltasAnuais;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sheets == null) ? 0 : sheets.hashCode());
        result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
        TurmaFile other = (TurmaFile) obj;
        if (sheets == null) {
            if (other.sheets != null)
                return false;
        } else if (!sheets.equals(other.sheets))
            return false;
        if (turma == null) {
            if (other.turma != null)
                return false;
        } else if (!turma.equals(other.turma))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "TurmaFile [turma=" + turma + ", sheets=" + sheets
				+ ", faltasAnuais=" + faltasAnuais + "]";
	}

}
