package sp.alvaro.model;

public enum Periodo {

    
    BIMESTRE_1("1º bimestre", 1), BIMESTRE_2("2º bimestre", 2), 
    BIMESTRE_3("3º bimestre", 3), BIMESTRE_4("4º bimestre", 4), 
    ANO("Média final", 5);

    private String str;
    private int value;
    
    private Periodo(String str, int value) {
        this.str = str;
        this.value = value;
    }
    
    public String toString() {
        return this.str;
    }
    
    public int toInt() {
        return this.value;
    }
    
    public static Periodo valueOf(int bim) {
        
        switch(bim) {
            case 1: return BIMESTRE_1;
            case 2: return BIMESTRE_2;
            case 3: return BIMESTRE_3;
            case 4: return BIMESTRE_4;
            case 5: return ANO;
            default: throw new IllegalArgumentException("Bimestre deve ser entre 1 e 5");
        }
    }
}
