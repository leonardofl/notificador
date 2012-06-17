package sp.alvaro.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Planilha montada pelo professor
 * 
 * @author Leonardo Leite
 *
 */
public class ProfSheet {
    
    private Periodo bimestre;
    private String professor;
    private List<Tarjeta> tarjetas;

    public ProfSheet() {
        this.tarjetas = new ArrayList<Tarjeta>();
    }

    public ProfSheet(Periodo bimestre, String professor) {
        this();
        this.bimestre = bimestre;
        this.professor = professor;
    }

    public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
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
		result = prime * result
				+ ((professor == null) ? 0 : professor.hashCode());
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
		ProfSheet other = (ProfSheet) obj;
		if (bimestre != other.bimestre)
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
		return "ProfSheet [bimestre=" + bimestre + ", professor=" + professor
				+ ", tarjetas=" + tarjetas + "]";
	}

	public Tarjeta findTarjeta(String turma, String materia) {
		
		for (Tarjeta tarj: tarjetas) {
			if (tarj.getTurma().equals(turma) &&
					tarj.getMateria().equals(materia)) {
				return tarj;
			}
		}
		return null;
	}
}
