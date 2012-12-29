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

public class TurmaSheet {

    private Periodo bimestre;
    private String turma;
    private List<Tarjeta> tarjetas;

    public TurmaSheet() {
        this.tarjetas = new ArrayList<Tarjeta>();        
    }

    public TurmaSheet(Periodo bimestre, String turma) {
        this();
        this.bimestre = bimestre;
        this.turma = turma;
    }
    
    public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public Periodo getBimestre() {
        return bimestre;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setBimestre(Periodo bimestre) {
        this.bimestre = bimestre;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bimestre == null) ? 0 : bimestre.hashCode());
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
		TurmaSheet other = (TurmaSheet) obj;
		if (bimestre != other.bimestre)
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
		return "TurmaSheet [bimestre=" + bimestre + ", turma=" + turma
				+ ", tarjetas=" + tarjetas + "]";
	}
	
	public Tarjeta findTarjeta(String materia) {
		
		for (Tarjeta tarj: tarjetas) {
			if (tarj.getMateria().equals(materia)) {
				return tarj;
			}
		}
		return null;
	}
	
	public List<String> getMaterias() {
		
		List<String> materias = new ArrayList<String>();
		for (Tarjeta tarj: tarjetas) {
			materias.add(tarj.getMateria());
		}
		return materias;
	}

}
