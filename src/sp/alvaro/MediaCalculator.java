package sp.alvaro;

import java.util.List;

import sp.alvaro.model.Conceito;
import sp.alvaro.model.TarjetaTurma;

public class MediaCalculator {

    /**
     * Faz o cálculo da tarjeta de médias
     * @param tarjetas dos quatro bimestres
     * @return tarjeta de médias
     */
    public TarjetaTurma calculateMedia(List<TarjetaTurma> tarjetas) {
        
        if (tarjetas.size() != 4)
            throw new IllegalArgumentException("Média deve ser calculada sobre tarjetas de 4 bimestres");
        
        TarjetaTurma medias = new TarjetaTurma();
        
        medias.setMateria(tarjetas.get(0).getMateria());
        medias.setProfessor(tarjetas.get(0).getProfessor());
        calculateAulasDadas(tarjetas, medias);
        calculateNotas(tarjetas, medias);
        
        return medias;
    }

    private void calculateAulasDadas(List<TarjetaTurma> tarjetas, TarjetaTurma medias) {
        for (TarjetaTurma tarj: tarjetas) {
            int aulas = medias.getAulasDadas() + tarj.getAulasDadas();
            medias.setAulasDadas(aulas);
        }
    }

    private void calculateNotas(List<TarjetaTurma> tarjetas, TarjetaTurma medias) {

        for (Conceito c: tarjetas.get(0).getNotas()) {
            medias.getNotas().add(new Conceito(c.getAluno(), c.getNota()/4d, c.getFaltas()));
        }
        
        for (int i=1; i<4; i++) {
            for (Conceito conceito: tarjetas.get(i).getNotas()) {
                for (Conceito c: medias.getNotas()) {
                    if (c.getAluno().equals(conceito.getAluno())) {
                        c.setNota(c.getNota() + conceito.getNota()/4d);
                        c.setFaltas(c.getFaltas() + conceito.getFaltas());
                    }
                }
            }
        }
    }
}
