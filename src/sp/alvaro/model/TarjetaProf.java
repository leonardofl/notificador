package sp.alvaro.model;

import java.util.ArrayList;
import java.util.List;

public class TarjetaProf {

    private String turma;
    private List<Conceito> notas;
    private int aulasDadas;
    
    public TarjetaProf() {
        this.notas = new ArrayList<Conceito>();
    }

    public TarjetaProf(String turma, int aulasDadas) {
        this.notas = new ArrayList<Conceito>();
        this.turma = turma;
        this.aulasDadas = aulasDadas;
    }

    public int getAulasDadas() {
        return aulasDadas;
    }

    public void setAulasDadas(int aulasDadas) {
        this.aulasDadas = aulasDadas;
    }

    public String getTurma() {
        return turma;
    }

    public List<Conceito> getNotas() {
        return notas;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setNotas(List<Conceito> notas) {
        this.notas = notas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + aulasDadas;
        result = prime * result + ((notas == null) ? 0 : notas.hashCode());
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
        TarjetaProf other = (TarjetaProf) obj;
        if (aulasDadas != other.aulasDadas)
            return false;
        if (notas == null) {
            if (other.notas != null)
                return false;
        } else if (!notas.equals(other.notas))
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
        return "TarjetaProf [turma=" + turma + ", notas=" + notas + ", aulasDadas=" + aulasDadas + "]";
    }

}
