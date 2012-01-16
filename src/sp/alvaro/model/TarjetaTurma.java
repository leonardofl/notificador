package sp.alvaro.model;

import java.util.ArrayList;
import java.util.List;

public class TarjetaTurma {

    private String materia;
    private String professor;
    private List<Conceito> notas;
    private int aulasDadas;

    public TarjetaTurma() {
        this.notas = new ArrayList<Conceito>();
    }

    public TarjetaTurma(String materia, String professor, int aulasDadas) {
        this.notas = new ArrayList<Conceito>();
        this.materia = materia;
        this.professor = professor;
        this.aulasDadas = aulasDadas;
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
        result = prime * result + aulasDadas;
        result = prime * result + ((materia == null) ? 0 : materia.hashCode());
        result = prime * result + ((notas == null) ? 0 : notas.hashCode());
        result = prime * result + ((professor == null) ? 0 : professor.hashCode());
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
        TarjetaTurma other = (TarjetaTurma) obj;
        if (aulasDadas != other.aulasDadas)
            return false;
        if (materia == null) {
            if (other.materia != null)
                return false;
        } else if (!materia.equals(other.materia))
            return false;
        if (notas == null) {
            if (other.notas != null)
                return false;
        } else if (!notas.equals(other.notas))
            return false;
        if (professor == null) {
            if (other.professor != null)
                return false;
        } else if (!professor.equals(other.professor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TarjetaTurma [materia=" + materia + ", professor=" + professor + ", notas=" + notas + ", aulasDadas="
                + aulasDadas + "]";
    }

}
