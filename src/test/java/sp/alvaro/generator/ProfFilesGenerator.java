package sp.alvaro.generator;

/**
 * Gera planilhas de professores para utilização em teste de carga.
 * 
 * @author leonardo
 *
 */
public class ProfFilesGenerator {

	private final static String[] TODO_ENSINO_MEDIO = new String[] { "1oA", "1oB", "1oC", "1oD", "2oA", "2oB", "2oC",
			"2oD", "3oA", "3oB", "3oC", "3oD" };

	private final static String[] QUINTAS = new String[] { "5aA", "5aB", "5aC", "5aD", "5aE", "5aF" };

	private final static String[] SEXTAS = new String[] { "6aA", "6aB", "6aC", "6aD", "6aE", "6aF" };

	private final static String[] SETIMAS = new String[] { "7aA", "7aB", "7aC", "7aD", "7aE", "7aF" };

	private final static String[] OITAVAS = new String[] { "8aA", "8aB", "8aC", "8aD", "8aE", "8aF" };

	private final static String[] QUINTAS_E_SEXTAS = new String[] { "5aA", "5aB", "5aC", "5aD", "5aE", "5aF", "6aA",
			"6aB", "6aC", "6aD", "6aE", "6aF" };

	private final static String[] SETIMAS_E_OITAVAS = new String[] { "7aA", "7aB", "7aC", "7aD", "7aE", "7aF", "8aA",
			"8aB", "8aC", "8aD", "8aE", "8aF" };

	public static void main(String[] args) {

		long t0 = System.nanoTime();
		
		profFileGenerator("lucia", "Eng de SW").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("jj", "Autômatos").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("nakamura", "Computação Gráfica").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("fabio", "Orientação a Objetos").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("gubitoso", "Sistemas Distribuídos").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("alfredo", "Programação Extrema").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("reverbel", "Conceitos de Linguagens de Programação").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("ze-augusto", "Introdução a Computação").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("gerosa", "Desenvolvimento Web").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("saad", "Programação").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("maciel", "Elétrica").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("gomes", "Sistemas Digitais").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("ana-heali", "Inteligência Artificial").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("wurtman-saad", "Programação").turmas(TODO_ENSINO_MEDIO).generate();
		profFileGenerator("Harmione", "Magia").turmas(TODO_ENSINO_MEDIO).generate();

		profFileGenerator("rosangela", "Português").turmas(QUINTAS_E_SEXTAS).generate();
		profFileGenerator("camoes", "Português").turmas(SETIMAS_E_OITAVAS).generate();
		profFileGenerator("areco", "Matemática").turmas(QUINTAS).generate();
		profFileGenerator("descartes", "Matemática", "Filosofia").turmas(SEXTAS).generate();
		profFileGenerator("tales", "Matemática").turmas(SETIMAS).generate();
		profFileGenerator("mateus", "Matemática", "Física").turmas(OITAVAS).generate();
		profFileGenerator("wanderley", "Física").turmas(QUINTAS).generate();
		profFileGenerator("evandro", "Química", "Física").turmas(SEXTAS).generate();
		profFileGenerator("marcondes", "Biologia", "Química").turmas(SETIMAS_E_OITAVAS).generate();
		profFileGenerator("mendel", "Biologia").turmas(QUINTAS_E_SEXTAS).generate();
		profFileGenerator("edileusa", "História").turmas(QUINTAS).generate();
		profFileGenerator("camila", "História", "Geografia").turmas(SEXTAS).generate();
		profFileGenerator("marta", "História").turmas(SETIMAS).generate();
		profFileGenerator("herodoto", "Historia").turmas(OITAVAS).generate();
		profFileGenerator("magalhes", "Geografia").turmas(SETIMAS_E_OITAVAS).generate();
		profFileGenerator("nelson", "Inglês").turmas(QUINTAS_E_SEXTAS).generate();
		profFileGenerator("victoria", "Inglês").turmas(SETIMAS_E_OITAVAS).generate();
		profFileGenerator("katia", "Artes").turmas(QUINTAS_E_SEXTAS).generate();
		profFileGenerator("leonardo", "Artes").turmas(SETIMAS_E_OITAVAS).generate();
		profFileGenerator("marcelo", "Educação Física").turmas(QUINTAS_E_SEXTAS).generate();
		profFileGenerator("eduardo", "Educação Física").turmas(SETIMAS_E_OITAVAS).generate();
		profFileGenerator("socrates", "Filosofia").turmas(QUINTAS).generate();
		profFileGenerator("platao", "Filosofia").turmas(OITAVAS).generate();
		
		long tf = System.nanoTime();
		long deltaSeconds = (tf - t0) / 1000 / 1000 / 1000;
		System.out.println("Generated in " + deltaSeconds + " seconds");
	}

	private static ProfFileGenerator profFileGenerator(String prof, String... materias) {
		ProfFileGenerator profFileGenerator = new ProfFileGenerator(prof, materias);
		return profFileGenerator;
	}

}
