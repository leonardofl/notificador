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
	
    private Map<Aluno, Double> faltas;
    private int aulasDadas;

    public TarjetaFaltasAnuais(int aulasDadas) {
		this.aulasDadas = aulasDadas;
		this.faltas = new HashMap<Aluno, Double>();
	}
	
    public TarjetaFaltasAnuais() {
		this.faltas = new HashMap<Aluno, Double>();    	
	}

	public int getAulasDadas() {
		return aulasDadas;
	}

	public void setAulasDadas(int aulasDadas) {
		this.aulasDadas = aulasDadas;
	}

	public Map<Aluno, Double> getFaltas() {
		return faltas;
	}

	public void setFaltas(Map<Aluno, Double> faltas) {
		this.faltas = faltas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aulasDadas;
		result = prime * result + ((faltas == null) ? 0 : faltas.hashCode());
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
		if (faltas == null) {
			if (other.faltas != null)
				return false;
		} else if (!faltas.equals(other.faltas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TarjetaFaltasAnuais [faltas=" + faltas + ", aulasDadas="
				+ aulasDadas + "]";
	}

}
