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
package sp.alvaro;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import sp.alvaro.model.ProfFile;

public interface NotasParser {

    public Set<ProfFile> parse(Collection<File> files) throws NotasParserException;
    
    /**
     * Recupera notas a partir de um arquivo feito pelo professor
     * @param file
     * @return
     * @throws IOException 
     */
    public ProfFile parseFile(File file) throws NotasParserException;
}
