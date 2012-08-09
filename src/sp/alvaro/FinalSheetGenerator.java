package sp.alvaro;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import sp.alvaro.model.Periodo;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.model.TarjetaFaltasAnuais;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.model.TurmaSheet;

public class FinalSheetGenerator {

	private Logger logger = Logger.getLogger(FinalSheetGenerator.class);
    private MediaCalculator calc = new MediaCalculator();

    /**
     * Gera a 5a planilha e a adiciona ao arquivo da turma
     * @param file
     * @return a planilha do 5o conceito
     */
	public TurmaSheet generateFinalSheet(TurmaFile file) {
        
		TurmaSheet finalSheet = new TurmaSheet(Periodo.ANO, file.getTurma());
        
        // iterando matérias
        for (String materia: file.getSheets().get(0).getMaterias()) {
            
            List<Tarjeta> bimestres = new ArrayList<Tarjeta>();
            for (TurmaSheet sheet: file.getSheets()) {
            	Tarjeta tarj = sheet.findTarjeta(materia);
            	if (tarj != null) {
            		bimestres.add(tarj);
            	} else {
					Tarjeta tarjNula = Tarjeta.getTarjetaNula(
							sheet.getBimestre(), materia, null,
							sheet.getTurma());
            		bimestres.add(tarjNula);
            		String msg = "Não achei a tarjeta de " + materia
							+ " do " + sheet.getBimestre() + " da turma "
							+ file.getTurma();
            		logger.warn(msg);
            	}
            }
            Tarjeta tarjFinal = calc.calculateMedia(bimestres);
            finalSheet.getTarjetas().add(tarjFinal);
        }

        // calcula notas anuais
        FaltasAnuaisCalculator fcalc = new FaltasAnuaisCalculator();
        TarjetaFaltasAnuais faltasAnuais = fcalc.calculateFaltasAnuais(finalSheet);
        file.setFaltasAnuais(faltasAnuais);
        
        file.getSheets().add(finalSheet);
        return finalSheet;
	}
}
