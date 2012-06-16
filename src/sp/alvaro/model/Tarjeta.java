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

}
