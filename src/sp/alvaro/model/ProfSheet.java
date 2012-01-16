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
    private List<TarjetaProf> tarjetas;

    public ProfSheet() {
        this.tarjetas = new ArrayList<TarjetaProf>();
    }

    public ProfSheet(Periodo bimestre) {
        this.tarjetas = new ArrayList<TarjetaProf>();
        this.bimestre = bimestre;
    }

    public Periodo getBimestre() {
        return bimestre;
    }

    public List<TarjetaProf> getTarjetas() {
        return tarjetas;
    }

    public void setBimestre(Periodo bimestre) {
        this.bimestre = bimestre;
    }

    public void setTarjetas(List<TarjetaProf> tarjetas) {
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
        ProfSheet other = (ProfSheet) obj;
        if (bimestre != other.bimestre)
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
        return "ProfSheet [bimestre=" + bimestre + ", tarjetas=" + tarjetas + "]";
    }

}
