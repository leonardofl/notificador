package sp.alvaro.generator;

import java.util.Random;

import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.Tarjeta;

public class ProfFileGenerator {

	private final static int NUM_ALUNOS = 35;

	private String profNome;
	private String[] materias;
	private String[] turmas;

	public ProfFileGenerator(String profNome, String[] materias) {
		this.profNome = profNome;
		this.materias = materias;
	}

	public ProfFileGenerator turmas(String... turmas) {
		this.turmas = turmas;
		return this;
	}

	public ProfFile generate() {
		
		ProfFile profFile = new ProfFile(profNome, profNome + ".ods");
		profFile.getSheets().add(new ProfSheet(Periodo.BIMESTRE_1, profNome));
		profFile.getSheets().add(new ProfSheet(Periodo.BIMESTRE_2, profNome));
		profFile.getSheets().add(new ProfSheet(Periodo.BIMESTRE_3, profNome));
		profFile.getSheets().add(new ProfSheet(Periodo.BIMESTRE_4, profNome));

		for (String turma : turmas) {
			for (String materia : materias) {
				for (ProfSheet profSheet : profFile.getSheets()) {
					profSheet.getTarjetas().add(tarjeta(turma, materia, profSheet.getBimestre()));
				}
			}
		}
		
		return profFile;
	}

	private Tarjeta tarjeta(String turma, String materia, Periodo periodo) {
		Tarjeta tarjeta = new Tarjeta(turma, materia, profNome, periodo, 20, 22);
		for (int i = 0; i < NUM_ALUNOS; i++) {
			Aluno aluno = new Aluno(Integer.toString(i), turma);
			Conceito conceito = new Conceito(aluno, nota(), faltas());
			tarjeta.getNotas().add(conceito);
		}
		return tarjeta;
	}


	private double nota() {
		int min = 0;
		int max = 10;
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	private int faltas() {
		int min = 0;
		int max = 15;
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
