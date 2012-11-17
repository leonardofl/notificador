package sp.alvaro.gui;

import sp.alvaro.odf.ProgressListener;

/**
 * O método progress do objeto desta classe será 
 * chamado pelo OdsParser quando houver progresso na operação.
 * Este objeto por sua vez, avisará ao Form de que esta na 
 * hora de atualizar o valor de progresso mostrado ao usuário. 
 * 
 * @author leonardo
 *
 */
public class WritingProgressUpdater implements ProgressListener {

	private Form form;
	
	public WritingProgressUpdater(Form form) {
		this.form = form;
	}

	@Override
	public void progress(int progress) {
		this.form.updateWritingProgress(progress);
	}
	
}
