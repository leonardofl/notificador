/**
 *  Copyright (C) 2012, Leonardo Leite
 *  This file is part of Notificador.
 *
 *  Notificador is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Notificador is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *   along with Notificador. If not, see <http://www.gnu.org/licenses/>.
 */
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
public class ReadingProgressUpdater implements ProgressListener {

	private Form form;
	
	public ReadingProgressUpdater(Form form) {
		this.form = form;
	}

	@Override
	public void progress(int progress) {
		this.form.updateReadingProgress(progress);
	}
	
}
