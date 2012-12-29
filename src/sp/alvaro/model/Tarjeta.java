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

public class Tarjeta {

	private String turma;
    private String materia;
    private String professor;
    private Periodo bimestre;
    private int aulasDadas;
    private int aulasPrevistas;
    private List<Conceito> notas;

    public Tarjeta() {
        this.notas = new ArrayList<Conceito>();
    }

	public Tarjeta(String turma, String materia, String professor,
			Periodo bimestre, int aulasDadas, int aulasPrevistas) {
        this();
        this.turma = turma;
        this.materia = materia;
        this.professor = professor;
        this.bimestre = bimestre;
        this.aulasDadas = aulasDadas;
        this.aulasPrevistas = aulasPrevistas;
    }
    
    public Periodo getBimestre() {
		return bimestre;
	}

	public void setBimestre(Periodo bimestre) {
		this.bimestre = bimestre;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public int getAulasPrevistas() {
        return aulasPrevistas;
    }

    public void setAulasPrevistas(int aulasPrevistas) {
        this.aulasPrevistas = aulasPrevistas;
    }

    public int getAulasDadas() {
        return aulasDadas;
    }

    public void setAulasDadas(int aulasDadas) {
        this.aulasDadas = aulasDadas;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getMateria() {
        return materia;
    }

    public List<Conceito> getNotas() {
        return notas;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setNotas(List<Conceito> notas) {
        this.notas = notas;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bimestre == null) ? 0 : bimestre.hashCode());
		result = prime * result + ((materia == null) ? 0 : materia.hashCode());
		result = prime * result
				+ ((professor == null) ? 0 : professor.hashCode());
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
		Tarjeta other = (Tarjeta) obj;
		if (bimestre != other.bimestre)
			return false;
		if (materia == null) {
			if (other.materia != null)
				return false;
		} else if (!materia.equals(other.materia))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
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
		return "Tarjeta [turma=" + turma + ", materia=" + materia
				+ ", professor=" + professor + ", bimestre=" + bimestre
				+ ", aulasDadas=" + aulasDadas + ", aulasPrevistas="
				+ aulasPrevistas + ", notas=" + notas + "]";
	}

	/**
	 * Verifica se lista de notas de outra tarjeta é igual a dessa tarjeta
	 * 
	 * Todas as notas de <code>other</code> devem estar nesta tarjeta e todas as
	 * notas desta tarjeta devem estar em <code>other</code>. A ordem das notas
	 * não importa O que importa são os pares <aluno,conceito> Ignora informação
	 * de cabeçalho (que podem ser verificadas com o método <code>equals</code>)
	 * 
	 * @param other
	 * @return
	 */
	public boolean notasEquals(Tarjeta other) {
		
		if (other == null || other.getNotas() == null) {
			return false;
		}
		for (Conceito nota: this.notas) {
			if (nota != null && !nota.isNulo()) {
				if (!nota.equals(other.findNota(nota.getAluno()))) {
					return false;
				}
			}
		}
		for (Conceito nota: other.getNotas()) {
			if (nota != null && !nota.isNulo()) {
				if (!nota.equals(this.findNota(nota.getAluno()))) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Procura a nota de um determinado aluno na tarjeta
	 * 
	 * @param aluno
	 * @return o <code>Conceito</code> do aluno ou <code>null</code> se aluno
	 *         não tiver nota na tarjeta
	 */
	public Conceito findNota(Aluno aluno) {
		
		for (Conceito n: notas) {
			if (n.getAluno().equals(aluno)) {
				return n;
			}
		}
		return null;
	}
	
	/**
	 * Devolve uma tarjeta com dados nulos nos conceitos, aulas dadas e aulas previstas
	 * @param bim
	 * @param materia
	 * @param prof
	 * @param turma
	 * @return
	 */
	public static Tarjeta getTarjetaNula(Periodo bim, String materia, String prof, String turma) {
		
		Tarjeta tarj = new Tarjeta();
		tarj.setBimestre(bim);
		tarj.setMateria(materia);
		tarj.setProfessor(prof);
		tarj.setTurma(turma);
		
		return tarj;
	}
	
}
