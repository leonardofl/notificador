package sp.alvaro.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Tarjeta que consolida as porcentagens de faltas dos alunos. 
 * 
 * A porcentagem é representada como um número entre 0 e 100
 * As porcentagens são calculadas sobre todas as aulas que o aluno teve,
 * considerando todos os bimestres e todas as matérias.
 * Esta tarjeta é usada na 5a planilha (a das notas finais).
 * 
 * @author leonardo
 *
 */
public class TarjetaFaltasAnuais {
	
    private String turma;
    private int aulasDadas;
    private int aulasPrevistas;
    private Map<Aluno, Integer> faltas;

    public TarjetaFaltasAnuais() {

		this.faltas = new HashMap<Aluno, Integer>();
	}

    public TarjetaFaltasAnuais(String turma, int aulasDadas,
			int aulasPrevistas) {

    	this.turma = turma;
		this.aulasDadas = aulasDadas;
		this.aulasPrevistas = aulasPrevistas;
		this.faltas = new HashMap<Aluno, Integer>();
	}

	public Map<Aluno, Integer> getFaltas() {
		return faltas;
	}

	public void setFaltas(Map<Aluno, Integer> faltas) {
		this.faltas = faltas;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public int getAulasDadas() {
		return aulasDadas;
	}

	public void setAulasDadas(int aulasDadas) {
		this.aulasDadas = aulasDadas;
	}

	public int getAulasPrevistas() {
		return aulasPrevistas;
	}

	public void setAulasPrevistas(int aulasPrevistas) {
		this.aulasPrevistas = aulasPrevistas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aulasDadas;
		result = prime * result + aulasPrevistas;
		result = prime * result + ((faltas == null) ? 0 : faltas.hashCode());
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
		TarjetaFaltasAnuais other = (TarjetaFaltasAnuais) obj;
		if (aulasDadas != other.aulasDadas)
			return false;
		if (aulasPrevistas != other.aulasPrevistas)
			return false;
		if (faltas == null) {
			if (other.faltas != null)
				return false;
		} else if (!faltas.equals(other.faltas))
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
		return "TarjetaFaltasAnuais [turma=" + turma + ", aulasDadas="
				+ aulasDadas + ", aulasPrevistas=" + aulasPrevistas
				+ ", faltas=" + faltas + "]";
	}
    
}
