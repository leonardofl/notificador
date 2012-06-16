package sp.alvaro;

import java.util.List;

import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.Tarjeta;

public class MediaCalculator {

    /**
     * Faz o cálculo da tarjeta de médias
     * Assume-se que tarjetas passadas são do mesmo professor e materia
     * Bimestre da tarjeta retornada é ANO
     * @param tarjetas dos quatro bimestres
     * @return tarjeta de médias
     */
    public Tarjeta calculateMedia(List<Tarjeta> tarjetas) {
        
        if (tarjetas.size() > 4)
            throw new IllegalArgumentException("Média deve ser calculada sobre até 4 tarjetas");
        
        Tarjeta medias = new Tarjeta();
        
        medias.setBimestre(Periodo.ANO);
        medias.setMateria(tarjetas.get(0).getMateria());
        medias.setProfessor(tarjetas.get(0).getProfessor());
        medias.setTurma(tarjetas.get(0).getTurma());
        calculateAulasDadas(tarjetas, medias);
        calculateNotas(tarjetas, medias);
        
        return medias;
    }

    private void calculateAulasDadas(List<Tarjeta> tarjetas, Tarjeta medias) {
        for (Tarjeta tarj: tarjetas) {
            int aulasDadas = medias.getAulasDadas() + tarj.getAulasDadas();
            int aulasPrevistas = medias.getAulasPrevistas() + tarj.getAulasPrevistas();
            medias.setAulasDadas(aulasDadas);
            medias.setAulasPrevistas(aulasPrevistas);
        }
    }

    private void calculateNotas(List<Tarjeta> tarjetas, Tarjeta medias) {

        for (Conceito c: tarjetas.get(0).getNotas()) {
            medias.getNotas().add(new Conceito(c.getAluno(), c.getNota()/4d, c.getFaltas()));
        }
        
        for (int i=1; i<tarjetas.size(); i++) {
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
