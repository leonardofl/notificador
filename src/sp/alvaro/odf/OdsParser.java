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
package sp.alvaro.odf;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import sp.alvaro.NotasParser;
import sp.alvaro.NotasParserException;
import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.Tarjeta;

/**
 * Recupera as notas nos arquivos ods
 * @author leonardo
 *
 */
public class OdsParser implements NotasParser {
    
	private Logger logger = Logger.getLogger(OdsParser.class);
	
    public static final int MAX_ALUNOS = 54; // o máximo que cabe na tarjeta
    private static final String COL_FIRST_TARJETA = "C"; // coluna das notas
    private static final String CELL_PROF = "B64";
    private static final String LIN_TURMAS = "4";
    private static final String LIN_MATERIAS = "3";
    private static final String LIN_AULAS_DADAS = "63";
    private static final String LIN_AULAS_PREVISTAS = "62";   
    private static final String LIN_FIRST_NOTA = "7";
    private static final String LIN_NOME_PROF = "64";
    private static final int TARJETAS_DISTANCE = 4;
    
    private ProgressListener listener;

    public OdsParser() {
    	
    }
    
    /**
     * 
     * @param listener é avisado quando OdsListener terminar de processar um arquivo
     */
    public OdsParser(ProgressListener listener) {
    	this.listener = listener;
    }
    
    public Set<ProfFile> parse(Collection<File> files) throws NotasParserException {
        
        Set<ProfFile> profFiles = new HashSet<ProfFile>();
        int i = 0;
        for (File file: files) {

        	profFiles.add(this.parseFile(file));
        	this.callListener(++i, files.size());
        }
        return profFiles;
    }
    
    /**
     * 
     * @param i arquivos já processados
     * @param n arquivos recebidos para processar
     */
    private void callListener(int i, int n) {

    	if (this.listener != null) {
    		int progress = (int) ((float) i / n * 100);
    		this.listener.progress(progress);
    	}
	}

	public ProfFile parseFile(File file) throws NotasParserException {
        
    	logger.debug("Lendo arquivo " + file.getName());
    	
        SpreadsheetDocument planilha;
        try {
            planilha = SpreadsheetDocument.loadDocument(file);
        } catch (Exception e) {
        	String msg = "Não pude ler o arquivo " + file.getName();
        	logger.error(msg, e);
            throw new NotasParserException(msg);
        }
        
        Table table = null;
        try {
        	table = planilha.getTableList().get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
			String msg = "Arquivo " + file.getName() + " não possui planilha 0";
        	logger.error(msg, e);
        	throw new NotasParserException(msg);
        }
        
        // extract basic information
        String prof = table.getCellByPosition(CELL_PROF).getDisplayText();
        if (prof == null || prof.isEmpty()) {
			String msg = "Célula B64 do arquivo " + file.getName() 
					+ " (1o bimestre) deveria conter o nome do professor";
        	logger.error(msg);
        	throw new NotasParserException(msg);
        }
        
        ProfFile profFile = new ProfFile(prof, file.getName());
        for (int bim=1; bim<=4; bim++) {
			ProfSheet profSheet = parseSheet(
					planilha.getTableList().get(bim - 1), prof,
					Periodo.valueOf(bim));
        	profFile.getSheets().add(profSheet);
        }
        
        return profFile;
    }
    
//	logger.warn("Período " + bim + " de " + prof
//			+ " não foi incluído (Aulas Dadas não foi preenchida na primeira tarjeta)");

	/**
	 * 
	 * @param table
	 * @param prof
	 * @param bim
	 * @return a planilha parseada ou <code>null</code> caso a primeira célula
	 *         de aulas dadas esteja vazia, ou algum outro problema aconteça
     * @throws NotasParserException 
	 */
    private ProfSheet parseSheet(Table table, String prof, Periodo bim) throws NotasParserException {
    	
    	logger.debug("Lendo período " + bim);

        ProfSheet profSheet = new ProfSheet(bim, prof);

        Coluna y = new Coluna(COL_FIRST_TARJETA);
        int i = 0;
        String turma = table.getCellByPosition(y.getValor().concat(LIN_TURMAS)).getDisplayText();
        // while !turma.isEmpty
        while (!turma.isEmpty() && !turma.contains("FIM")) {
    	// TODO: não deveria precisar do "FIM"

        	try {
        		Tarjeta tarj = parseTarjeta(table, i, prof, bim);
        		profSheet.getTarjetas().add(tarj);
        	} catch (NotasParserException e) {
        		logger.warn("Tarjeta da " + turma + " " + bim
        				+ " não incluída na planilha de "
        				+ profSheet.getProfessor());
        	}

            y.inc(TARJETAS_DISTANCE);
            i++;
            turma = table.getCellByPosition(y.getValor().concat(LIN_TURMAS)).getDisplayText();
        }

        return profSheet;
    }
    
    /**
     * Extrai informação da tarjeta.
     * Tarjeta gerada possui nome do professor identificado na própria tarjeta.
     * Mas mensagens de erro são dadas em nome do professor dono do arquivo,
     * pois a mensagem serve para ajudar o operador a identificar o local do problema.
     * @param table
     * @param index índice da tarjeta na planilha, indo de 0 a N-1
     * @param professor dono da planilha, a princípio prof de todas as tarjetas
     * @param bim
     * @return
     * @throws NotasParserException 
     */
    private Tarjeta parseTarjeta(Table table, int index, String profSheet, Periodo bim) throws NotasParserException {

        Coluna y = new Coluna(COL_FIRST_TARJETA);
        y.inc(TARJETAS_DISTANCE*index);
		String turma = table.getCellByPosition(y.getValor().concat(LIN_TURMAS))
				.getDisplayText();
		String aulasDadasStr = table.getCellByPosition(
				y.getValor().concat(LIN_AULAS_DADAS)).getDisplayText();
		String aulasPrevistasStr = table.getCellByPosition(
				y.getValor().concat(LIN_AULAS_PREVISTAS)).getDisplayText();
		String materia = table.getCellByPosition(
				y.getValor().concat(LIN_MATERIAS)).getDisplayText();
		Coluna yprof = new Coluna(y.getValor());
		yprof.dec();
		String profTarjeta = table.getCellByPosition(
				yprof.getValor().concat(LIN_NOME_PROF)).getDisplayText();
		
		// se prof não foi identificado na tarjeta,
		// assumimos que o professor é o dono da planilha
		if (profTarjeta == null || profTarjeta.isEmpty()) {
			profTarjeta = profSheet;
		}
		
		if (turma.isEmpty()) {
			String msg = "Turma não especificada na tarjeta " + (index + 1)
					+ " do " + bim + " do prof " + profSheet;
			logger.error(msg);
			throw new NotasParserException(msg);
		}
		if (materia.isEmpty()) {
			String msg = "Matéria não especificada na tarjeta " + (index + 1)
					+ " do " + bim + " do prof " + profSheet;
			logger.error(msg);
			throw new NotasParserException(msg);
		}
        		
        int aulasDadas=0, aulasPrevistas=0;
        try {
        	aulasDadas = Integer.parseInt(aulasDadasStr);
        } catch (NumberFormatException e) {
        	String message = "Aulas dadas não foram registradas na célula" + y.getValor() + LIN_AULAS_DADAS;
        	logger.warn(message);
        }
        try {
        	aulasPrevistas = Integer.parseInt(aulasPrevistasStr);
	    } catch (NumberFormatException e) {
			String message = "Aulas previstas não foram registradas na célula "
					+ y.getValor() + LIN_AULAS_PREVISTAS + " (" + profSheet + " - "
					+ bim + ")";
        	logger.warn(message);
	    }
        
        Tarjeta tarj = new Tarjeta(turma, materia, profTarjeta, bim, aulasDadas, aulasPrevistas);
        
        // notas
        int row = Integer.parseInt(LIN_FIRST_NOTA);
        for (int i=0; i<MAX_ALUNOS; i++) { 

            String nota = table.getCellByPosition(y.getValor().concat(Integer.toString(row))).getDisplayText();
            
            Coluna ya = new Coluna(y.getValor());
            ya.dec();
            String aluno = table.getCellByPosition(ya.toString().concat(Integer.toString(row))).getDisplayText();
            Coluna yf = new Coluna(y.getValor());
            yf.inc();
            String faltas = table.getCellByPosition(yf.getValor().concat(Integer.toString(row))).getDisplayText();

            int notaInt = 0, faltasInt = 0;
            String alteracao = null;
            if (nota != null && !nota.isEmpty()) {
                try {
                    notaInt = Integer.parseInt(nota);
                }
                catch(NumberFormatException e) {
                    if (nota != null && !nota.isEmpty())
                    	alteracao = nota;
                }
                try {
                    faltasInt = Integer.parseInt(faltas);
                }
                catch(NumberFormatException e) {
                    ;
                }                
            }
            Conceito conc = new Conceito(new Aluno(aluno, turma), notaInt, faltasInt);
            conc.setAlteracao(alteracao);
            tarj.getNotas().add(conc);
            
            row++;
        }
        
        return tarj;
    }
    
}
