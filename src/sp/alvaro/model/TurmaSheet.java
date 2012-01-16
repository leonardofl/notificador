package sp.alvaro.model;

import java.util.ArrayList;
import java.util.List;

public class TurmaSheet {

    private Periodo bimestre;
    private List<TarjetaTurma> tarjetas;

    public TurmaSheet() {
        this.tarjetas = new ArrayList<TarjetaTurma>();        
    }

    public TurmaSheet(Periodo bimestre) {
        this.tarjetas = new ArrayList<TarjetaTurma>();   
        this.bimestre = bimestre;
    }

    public Periodo getBimestre() {
        return bimestre;
    }

    public List<TarjetaTurma> getTarjetas() {
        return tarjetas;
    }

    public void setBimestre(Periodo bimestre) {
        this.bimestre = bimestre;
    }

    public void setTarjetas(List<TarjetaTurma> tarjetas) {
        this.tarjetas = tarjetas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bimestre == null) ? 0 : bimestre.hashCode());
        result = prime * result + ((tarjetas == null) ? 0 : tarjetas.hashCode());
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
        if (bimestre == null) {
            if (other.bimestre != null)
                return false;
        } else if (!bimestre.equals(other.bimestre))
            return false;
        if (tarjetas == null) {
            if (other.tarjetas != null)
                return false;
        } else if (!tarjetas.equals(other.tarjetas))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TurmaSheet [bimestre=" + bimestre + ", tarjetas=" + tarjetas + "]";
    }
    
}
