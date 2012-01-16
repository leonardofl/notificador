package sp.alvaro.model;

public class Conceito {

    private Aluno aluno; 
    private double nota;
    private int faltas;
    
    
    public Conceito() {
        
    }


    public Conceito(Aluno aluno, double nota, int faltas) {
        this.aluno = aluno;
        this.nota = nota;
        this.faltas = faltas;
    }


    public Aluno getAluno() {
        return aluno;
    }


    public double getNota() {
        return nota;
    }


    public int getFaltas() {
        return faltas;
    }


    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }


    public void setNota(double nota) {
        this.nota = nota;
    }


    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
        result = prime * result + faltas;
        long temp;
        temp = Double.doubleToLongBits(nota);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Conceito other = (Conceito) obj;
        if (aluno == null) {
            if (other.aluno != null)
                return false;
        } else if (!aluno.equals(other.aluno))
            return false;
        if (faltas != other.faltas)
            return false;
        if (Double.doubleToLongBits(nota) != Double.doubleToLongBits(other.nota))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Conceito [aluno=" + aluno + ", nota=" + nota + ", faltas=" + faltas + "]";
    }

}
