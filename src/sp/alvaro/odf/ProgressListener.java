package sp.alvaro.odf;

public interface ProgressListener {

	/**
	 * Avisa ao listener que o OdsParser fez um progresso
	 * (este método será chamado pelo OdsParser)
	 * 
	 * @param progress um número entre 0 e 100
	 */
	public void progress(int progress);
}
