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
package sp.alvaro.generator;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import sp.alvaro.FileLoader;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.odf.Coluna;

public class OdsProfRecorder {
	
	private Logger logger = Logger.getLogger(OdsProfRecorder.class);

	// regra de negócio
	public static final int PRESENCA_MINIMA = 75; // 75%
	
    private static final String MODELO = "prof.ods";
    private static final int MAX_TURMAS = 12; 
    private static final int MAX_NOTAS = 54;
    private static final int TARJETA_PASSO = 4; // quantas células de uma tarjeta pra outras
    private static final int LINHA_MATERIA = 3;
    private static final int LINHA_TURMA = 4;
    private static final int LINHA_AULAS_PREVISTAS =62;
    private static final int LINHA_AULAS_DADAS = 63;
    private static final int LINHA_PROFESSOR = 64;
    
    private File outputDir;
    
    public OdsProfRecorder(File outputDir) {
        
        this.outputDir = outputDir;
    }
    
    public void record(Collection<ProfFile> profFiles) {
    	try {
			record_(profFiles);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
    }
    
    private void record_(Collection<ProfFile> profFiles) throws IOException {
        
        for (ProfFile f: profFiles) {
        	
        	logger.info("Gravando arquivo " + f.getProfessor());
            
            // consistência
            int tarjetas_size = f.getSheets().get(0).getTarjetas().size();
            if (tarjetas_size > MAX_TURMAS) {
                throw new IllegalArgumentException(f.getProfessor() + " tem " +
                        tarjetas_size + "tarjetas! Sistema não suporta mais que " + MAX_TURMAS + " matérias.");
            }
            
            SpreadsheetDocument ods;
			try {
				ods = SpreadsheetDocument.newSpreadsheetDocument();
				FileLoader loader = new FileLoader(MODELO);
				File sheetFile = loader.getFile();
				ods = SpreadsheetDocument.loadDocument(sheetFile);
			} catch (Exception e) {
				String msg = "Não consegui carregar a planilha de modelo " + MODELO;
				logger.error(msg, e);
				throw new IOException(msg, e);
			}

        	this.processSheets(ods, f);
            
            String path = outputDir.getAbsolutePath() + "/" + f.getFileName();
            try {
				ods.save(path);
			} catch (Exception e) {
				String msg = "Não consegui salvar planilha em " + path;
				logger.error(msg, e);
				throw new IOException(msg, e);
			}
        }
    }
    
    private void processSheets(SpreadsheetDocument ods, ProfFile f) {

    	int i = 0;
        for (ProfSheet turmaSheet: f.getSheets()) { // percorre bimestres
            
        	logger.debug("Gravando planilha " + turmaSheet.getBimestre());
        	
            Table table = ods.getTableList().get(i);
            
            Coluna col = new Coluna("C");
            for (Tarjeta tarjeta: turmaSheet.getTarjetas()) {

            	table.getCellByPosition(col.getValor() + LINHA_TURMA).setStringValue(tarjeta.getTurma());
                
                // consistência
                int tarj_size = tarjeta.getNotas().size();
                if (tarj_size > MAX_NOTAS){
                	IllegalArgumentException e = new IllegalArgumentException(
                            tarj_size + " é muito! Sistema não suporta mais que " + MAX_NOTAS + " linhas por tarjeta.");
                	logger.warn(e.getMessage() + " enquanto parseando ", e);
                    throw e;
                }
                
                // preenche dados
                table.getCellByPosition(col.getValor()+LINHA_MATERIA).setStringValue(tarjeta.getMateria());
                table.getCellByPosition(col.getValor()+LINHA_AULAS_PREVISTAS).setStringValue(Integer.toString(tarjeta.getAulasPrevistas()));
                table.getCellByPosition(col.getValor()+LINHA_AULAS_DADAS).setStringValue(Integer.toString(tarjeta.getAulasDadas()));
                Coluna colProf = new Coluna(col.getValor());
                colProf.dec();
                table.getCellByPosition(colProf.getValor()+LINHA_PROFESSOR).setStringValue(tarjeta.getProfessor());
                
                int lin = 7;
                for (Conceito nota: tarjeta.getNotas()) {
                    
                    String l = Integer.toString(lin);
                    Coluna cn = new Coluna(col.getValor()); 
                    Coluna cf = new Coluna(col.getValor());
                    cn.inc();
                    cf.inc();
                    table.getCellByPosition(cn.getValor()+l).setStringValue(nota.getAluno().getNome());
                    if (nota.getAlteracao() != null && !nota.getAlteracao().isEmpty()) {
                    	table.getCellByPosition(col+l).setStringValue(nota.getAlteracao());
                    } else {
                    	table.getCellByPosition(col+l).setStringValue(Double.toString(nota.getNota()));
                    }
                    table.getCellByPosition(cf.getValor()+l).setStringValue(Integer.toString(nota.getFaltas()));
                    lin++; 
                }
                
                col.inc(TARJETA_PASSO); 
            }
            
            i++;
        }
    }


}
