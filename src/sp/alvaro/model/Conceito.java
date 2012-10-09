package sp.alvaro.model;

public class Conceito {

    private Aluno aluno; 
    private double nota;
    private int faltas;
    private String alteracao; // TR, NC, AB, RM
    
    public boolean isNulo() {
    	if (nota == 0 && faltas == 0)
    		return true;
    	else
    		return false;
    }
    
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

    public String getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(String alteracao) {
		this.alteracao = alteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alteracao == null) ? 0 : alteracao.hashCode());
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
		if (alteracao == null) {
			if (other.alteracao != null)
				return false;
		} else if (!alteracao.equals(other.alteracao))
			return false;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (faltas != other.faltas)
			return false;
		if (Double.doubleToLongBits(nota) != Double
				.doubleToLongBits(other.nota))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conceito [aluno=" + aluno + ", nota=" + nota + ", faltas="
				+ faltas + ", alteracao=" + alteracao + "]";
	}

}
