package sp.alvaro.generator;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import sp.alvaro.LogConfigurator;
import sp.alvaro.model.ProfFile;

/**
 * Gera planilhas de professores para utilização em teste de carga.
 * 
 * @author leonardo
 *
 */
public class ProfFilesGenerator {

	private final static String FOLDER_FOR_GENERATED_FILES = "/home/leonardo/Dropbox/notificador-planilhas/profs-gerados";
	
	private final static String[] ENSINO_MEDIO_1 = new String[] { "1oA", "1oB", "1oC", "1oD", "2oA", "2oB" };

	private final static String[] ENSINO_MEDIO_2 = new String[] { "2oC", "2oD", "3oA", "3oB", "3oC", "3oD" };

	private final static String[] QUINTAS = new String[] { "5aA", "5aB", "5aC", "5aD", "5aE", "5aF" };

	private final static String[] SEXTAS = new String[] { "6aA", "6aB", "6aC", "6aD", "6aE", "6aF" };

	private final static String[] SETIMAS = new String[] { "7aA", "7aB", "7aC", "7aD", "7aE", "7aF" };

	private final static String[] OITAVAS = new String[] { "8aA", "8aB", "8aC", "8aD", "8aE", "8aF" };

	private final static String[] QUINTAS_E_SEXTAS = new String[] { "5aA", "5aB", "5aC", "5aD", "5aE", "5aF", "6aA",
			"6aB", "6aC", "6aD", "6aE", "6aF" };

	private final static String[] SETIMAS_E_OITAVAS = new String[] { "7aA", "7aB", "7aC", "7aD", "7aE", "7aF", "8aA",
			"8aB", "8aC", "8aD", "8aE", "8aF" };
	
	private static Set<ProfFile> profFiles = new HashSet<ProfFile>();

	public static void main(String[] args) {

		LogConfigurator.initLog();
		
		long t0 = System.nanoTime();
		
		add(profFile("lucia", "Eng de SW").turmas(ENSINO_MEDIO_1).generate());
		add(profFile("jj", "Autômatos").turmas(ENSINO_MEDIO_1).generate());
		add(profFile("nakamura", "Computação Gráfica").turmas(ENSINO_MEDIO_1).generate());
		add(profFile("fabio", "Orientação a Objetos").turmas(ENSINO_MEDIO_1).generate());
		add(profFile("gubitoso", "Sistemas Distribuídos").turmas(ENSINO_MEDIO_1).generate());
		add(profFile("alfredo", "Programação Extrema").turmas(ENSINO_MEDIO_1).generate());
		add(profFile("reverbel", "Conceitos de Linguagens de Programação").turmas(ENSINO_MEDIO_1).generate());
		add(profFile("ze-augusto", "Introdução a Computação").turmas(ENSINO_MEDIO_1).generate());
		add(profFile("gerosa", "Desenvolvimento Web").turmas(ENSINO_MEDIO_2).generate());
		add(profFile("maciel", "Elétrica").turmas(ENSINO_MEDIO_2).generate());
		add(profFile("gomes", "Sistemas Digitais").turmas(ENSINO_MEDIO_2).generate());
		add(profFile("ana-heali", "Inteligência Artificial").turmas(ENSINO_MEDIO_2).generate());
		add(profFile("wurtman-saad", "Programação").turmas(ENSINO_MEDIO_2).generate());
		add(profFile("Harmione", "Magia").turmas(ENSINO_MEDIO_2).generate());

		add(profFile("rosangela", "Português").turmas(QUINTAS_E_SEXTAS).generate());
		add(profFile("camoes", "Português").turmas(SETIMAS_E_OITAVAS).generate());
		add(profFile("areco", "Matemática").turmas(QUINTAS).generate());
		add(profFile("descartes", "Matemática", "Filosofia").turmas(SEXTAS).generate());
		add(profFile("tales", "Matemática").turmas(SETIMAS).generate());
		add(profFile("mateus", "Matemática", "Física").turmas(OITAVAS).generate());
		add(profFile("wanderley", "Física").turmas(QUINTAS).generate());
		add(profFile("evandro", "Química", "Física").turmas(SEXTAS).generate());
		add(profFile("marcondes", "Biologia", "Química").turmas(SETIMAS).generate());
		add(profFile("mendel", "Biologia").turmas(QUINTAS_E_SEXTAS).generate());
		add(profFile("edileusa", "História").turmas(QUINTAS).generate());
		add(profFile("camila", "História", "Geografia").turmas(SEXTAS).generate());
		add(profFile("marta", "História").turmas(SETIMAS).generate());
		add(profFile("herodoto", "Historia").turmas(OITAVAS).generate());
		add(profFile("magalhes", "Geografia").turmas(SETIMAS_E_OITAVAS).generate());
		add(profFile("nelson", "Inglês").turmas(QUINTAS_E_SEXTAS).generate());
		add(profFile("victoria", "Inglês").turmas(SETIMAS_E_OITAVAS).generate());
		add(profFile("katia", "Artes").turmas(QUINTAS_E_SEXTAS).generate());
		add(profFile("leonardo", "Artes").turmas(SETIMAS_E_OITAVAS).generate());
		add(profFile("marcelo", "Educação Física").turmas(QUINTAS).generate());
		add(profFile("eduardo", "Educação Física").turmas(SETIMAS).generate());
		add(profFile("socrates", "Filosofia").turmas(QUINTAS).generate());
		add(profFile("platao", "Filosofia").turmas(OITAVAS).generate());
		
		record();
		
		long tf = System.nanoTime();
		long deltaSeconds = (tf - t0) / 1000 / 1000 / 1000;
		System.out.println("Generated in " + deltaSeconds + " seconds");
	}

	private static ProfFileGenerator profFile(String prof, String... materias) {
		ProfFileGenerator profFileGenerator = new ProfFileGenerator(prof, materias);
		return profFileGenerator;
	}
	
	private static void add(ProfFile profFile) {
		profFiles.add(profFile);
	}

	private static void record() {
		File outputFolder = new File(FOLDER_FOR_GENERATED_FILES);
		OdsProfRecorder recorder = new OdsProfRecorder(outputFolder);
		recorder.record(profFiles);
	}
}
