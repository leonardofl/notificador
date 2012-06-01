package sp.alvaro.odf;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import sp.alvaro.TurmaFileRecorder;
import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.TarjetaFaltasAnuais;
import sp.alvaro.model.TarjetaTurma;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.model.TurmaSheet;

// TODO: testar com mais tarjetas, pra alcançar o AA
public class OdsRecorder implements TurmaFileRecorder {

	// regra de negócio
	public static final int PRESENCA_MINIMA = 90; // 75%
	
    private static final String MODELO_CONSOLIDADO = "resources/consolidado_modelo.ods";
    private static final int MAX_MATERIAS = 6; // pra passar de 6 tem q enderessar TODO de baixo
    private static final int MAX_NOTAS = 40;
    private static final int TARJETA_PASSO = 4; // quantas células de uma tarjeta pra outras
    private static final int LINHA_MATERIA = 3;
    private static final int LINHA_TURMA = 4;
    private static final int LINHA_AULAS_PREVISTAS = 48;
    private static final int LINHA_AULAS_DADAS = 49;
    
    private File outputDir;
    
    public OdsRecorder(File outputDir) {
        
        this.outputDir = outputDir;
    }
    
    @Override
    public void record(Collection<TurmaFile> turmaFiles) throws Exception {
        
        for (TurmaFile f: turmaFiles) {
            
            // consistência
            int turmas_size = f.getSheets().get(0).getTarjetas().size();
            if (turmas_size > MAX_MATERIAS) {
                throw new IllegalArgumentException(
                        turmas_size + " é muito! Sistema não suporta mais que " + MAX_MATERIAS + " matérias.");
            }
            
            SpreadsheetDocument ods = SpreadsheetDocument.newSpreadsheetDocument();
            try {
                ods = SpreadsheetDocument.loadDocument(MODELO_CONSOLIDADO);
            } catch (Exception e) {
                throw new IOException("Falha da API de ODF");
            }

            this.processSheets(ods, f);
            
            String path = outputDir.getAbsolutePath() + "/" + f.getTurma() + ".ods";
            ods.save(path);
        }
    }
    
    private void processSheets(SpreadsheetDocument ods, TurmaFile f) {

        for (int i=0; i<5; i++) { // percorre bimestres
            
            TurmaSheet turmaSheet = f.getSheets().get(i);
            Table table = ods.getTableList().get(i);
            
            table.getCellByPosition("C" + LINHA_TURMA).setStringValue(f.getTurma());
            
            Coluna col = new Coluna("C");
            for (TarjetaTurma tarjeta: turmaSheet.getTarjetas()) {
                
                // consistência
                int tarj_size = tarjeta.getNotas().size();
                if (tarj_size > MAX_NOTAS){
                    throw new IllegalArgumentException(
                            tarj_size + " é muito! Sistema não suporta mais que " + MAX_NOTAS + " matérias.");
                }
                
                // preenche dados
                table.getCellByPosition(col.getValor()+LINHA_MATERIA).setStringValue(tarjeta.getMateria());
                table.getCellByPosition(col.getValor()+LINHA_AULAS_PREVISTAS).setStringValue(Integer.toString(tarjeta.getAulasPrevistas()));
                table.getCellByPosition(col.getValor()+LINHA_AULAS_DADAS).setStringValue(Integer.toString(tarjeta.getAulasDadas()));
                
                int lin = 7;
                for (Conceito nota: tarjeta.getNotas()) {
                    
                    String l = Integer.toString(lin);
                    Coluna cn = new Coluna(col.getValor()); 
                    Coluna cf = new Coluna(col.getValor());
                    cn.inc();
                    cf.inc();
                    table.getCellByPosition(cn.getValor()+l).setStringValue(nota.getAluno().getNome());
                    table.getCellByPosition(col+l).setStringValue(Double.toString(nota.getNota()));
                    table.getCellByPosition(cf.getValor()+l).setStringValue(Integer.toString(nota.getFaltas()));
                    lin++; 
                }
                
                col.inc(TARJETA_PASSO); 
            }
            
            if (i == 4) { // notas finais
            	this.recordFaltasAnuais(f.getFaltasAnuais(), turmaSheet, table, new Coluna("AA"));
            }
        }
    }

	private void recordFaltasAnuais(TarjetaFaltasAnuais faltasAnuais, TurmaSheet finalSheet, 
			Table table, Coluna col) {

		int lin = 7;
		Coluna col2 = new Coluna(col.getValor());
		col2.inc();
		for (Conceito conc: finalSheet.getTarjetas().get(0).getNotas()) {
			
			Aluno aluno = conc.getAluno();
			double falta = faltasAnuais.getFaltas().get(aluno);
			
			String faltaLabel = "" + Math.round(falta) + "%";
			String l = Integer.toString(lin);
            table.getCellByPosition(col.getValor()+l).setStringValue(aluno.getNome());
            table.getCellByPosition(col2.getValor()+l).setStringValue(faltaLabel);
            
//            if (falta > 100 - PRESENCA_MINIMA) {
//            	Font font = (Font) table.getCellByPosition(col2.getValor()+l).getFont();
//            	font.setColor(Color.RED);
//            	table.getCellByPosition(col2.getValor()+l).setFont(font);
//            }
            
            lin++;
		}
		
		String totalAulas = Integer.toString(faltasAnuais.getAulasDadas());
		table.getCellByPosition(col2.getValor()+LINHA_AULAS_DADAS).setStringValue(totalAulas);
	}

}
