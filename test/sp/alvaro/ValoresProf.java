package sp.alvaro;

import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.ProfFile;
import sp.alvaro.model.ProfSheet;
import sp.alvaro.model.TarjetaProf;
import sp.alvaro.odf.OdsParser;

public class ValoresProf {

    private ProfFile file1, file2;
    
    public ProfFile getProfSheet1() {
        
        if (file1 == null)
            generateData1();
        
        return this.file1;
    }
    
    private void generateData1() {
        
        // MANOEL, inglês
        
        // 1o bim
        String trm = "6aA"; 
        TarjetaProf t16a = new TarjetaProf(trm, 22, 23);
        t16a.getNotas().add(new Conceito(new Aluno("1", trm), 6, 2));
        t16a.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t16a.getNotas().add(new Conceito(new Aluno("3", trm), 3, 1));
        t16a.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t16a.getNotas().add(new Conceito(new Aluno("5", trm), 2, 3));
        trm = "6aB";
        TarjetaProf t16b = new TarjetaProf(trm, 23, 23);
        t16b.getNotas().add(new Conceito(new Aluno("1", trm), 2, 2));
        t16b.getNotas().add(new Conceito(new Aluno("2", trm), 3, 5));
        t16b.getNotas().add(new Conceito(new Aluno("3", trm), 6, 1));
        t16b.getNotas().add(new Conceito(new Aluno("4", trm), 8, 5));
        t16b.getNotas().add(new Conceito(new Aluno("5", trm), 1, 3));
        trm = "6aC";
        TarjetaProf t16c = new TarjetaProf(trm, 22, 23);
        t16c.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t16c.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t16c.getNotas().add(new Conceito(new Aluno("3", trm), 3, 1));
        t16c.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t16c.getNotas().add(new Conceito(new Aluno("5", trm), 2, 3));
        trm = "6aD";
        TarjetaProf t16d = new TarjetaProf(trm, 23, 23);
        t16d.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t16d.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t16d.getNotas().add(new Conceito(new Aluno("3", trm), 5, 1));
        t16d.getNotas().add(new Conceito(new Aluno("4", trm), 3, 5));
        t16d.getNotas().add(new Conceito(new Aluno("5", trm), 4, 3));
        ProfSheet sheet1 = new ProfSheet(Periodo.BIMESTRE_1);
        sheet1.getTarjetas().add(t16a);
        sheet1.getTarjetas().add(t16b);
        sheet1.getTarjetas().add(t16c);
        sheet1.getTarjetas().add(t16d);
        // 2o bim
        trm = "6aA"; 
        TarjetaProf t26a = new TarjetaProf(trm, 22, 23);
        t26a.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t26a.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t26a.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t26a.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t26a.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aB";
        TarjetaProf t26b = new TarjetaProf(trm, 23, 23);
        t26b.getNotas().add(new Conceito(new Aluno("1", trm), 2, 5));
        t26b.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t26b.getNotas().add(new Conceito(new Aluno("3", trm), 1, 9));
        t26b.getNotas().add(new Conceito(new Aluno("4", trm), 5, 4));
        t26b.getNotas().add(new Conceito(new Aluno("5", trm), 3, 9));
        trm = "6aC";
        TarjetaProf t26c = new TarjetaProf(trm, 22, 23);
        t26c.getNotas().add(new Conceito(new Aluno("1", trm), 10, 2));
        t26c.getNotas().add(new Conceito(new Aluno("2", trm), 10, 5));
        t26c.getNotas().add(new Conceito(new Aluno("3", trm), 4, 11));
        t26c.getNotas().add(new Conceito(new Aluno("4", trm), 8, 5));
        t26c.getNotas().add(new Conceito(new Aluno("5", trm), 4, 15));
        trm = "6aD";
        TarjetaProf t26d = new TarjetaProf(trm, 23, 23);
        t26d.getNotas().add(new Conceito(new Aluno("1", trm), 1, 10));
        t26d.getNotas().add(new Conceito(new Aluno("2", trm), 2, 9));
        t26d.getNotas().add(new Conceito(new Aluno("3", trm), 3, 8));
        t26d.getNotas().add(new Conceito(new Aluno("4", trm), 4, 12));
        t26d.getNotas().add(new Conceito(new Aluno("5", trm), 2, 15));
        ProfSheet sheet2 = new ProfSheet(Periodo.BIMESTRE_2);
        sheet2.getTarjetas().add(t26a);
        sheet2.getTarjetas().add(t26b);
        sheet2.getTarjetas().add(t26c);
        sheet2.getTarjetas().add(t26d);
        // 3o bim
        trm = "6aA"; 
        TarjetaProf t36a = new TarjetaProf(trm, 22, 23);
        t36a.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t36a.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t36a.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t36a.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t36a.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aB";
        TarjetaProf t36b = new TarjetaProf(trm, 23, 23);
        t36b.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t36b.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t36b.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t36b.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t36b.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aC";
        TarjetaProf t36c = new TarjetaProf(trm, 22, 23);
        t36c.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t36c.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t36c.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t36c.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t36c.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aD";
        TarjetaProf t36d = new TarjetaProf(trm, 23, 23);
        t36d.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t36d.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t36d.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t36d.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t36d.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        ProfSheet sheet3 = new ProfSheet(Periodo.BIMESTRE_3);
        sheet3.getTarjetas().add(t36a);
        sheet3.getTarjetas().add(t36b);
        sheet3.getTarjetas().add(t36c);
        sheet3.getTarjetas().add(t36d);        
        // 4o bim
        trm = "6aA"; 
        TarjetaProf t46a = new TarjetaProf(trm, 22, 23);
        t46a.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t46a.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t46a.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t46a.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t46a.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aB";
        TarjetaProf t46b = new TarjetaProf(trm, 23, 23);
        t46b.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t46b.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t46b.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t46b.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t46b.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aC";
        TarjetaProf t46c = new TarjetaProf(trm, 22, 23);
        t46c.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t46c.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t46c.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t46c.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t46c.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aD";
        TarjetaProf t46d = new TarjetaProf(trm, 23, 23);
        t46d.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t46d.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t46d.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t46d.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t46d.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        ProfSheet sheet4 = new ProfSheet(Periodo.BIMESTRE_4);
        sheet4.getTarjetas().add(t46a);
        sheet4.getTarjetas().add(t46b);
        sheet4.getTarjetas().add(t46c);
        sheet4.getTarjetas().add(t46d); 
        
        this.file1 = new ProfFile("MANOEL", "00119-Inglês", "prof1");
        this.file1.getSheets().add(sheet1);
        this.file1.getSheets().add(sheet2);
        this.file1.getSheets().add(sheet3);
        this.file1.getSheets().add(sheet4);
        fillTarjetas(file1);
    }

    public ProfFile getProfSheet2() {
        
        if (file2 == null)
            generateData2();
        
        return this.file2;
    }
    
    private void generateData2() {
        
        // MARIA, geografia
        
        // 1o bim
        String trm = "6aA"; 
        TarjetaProf t16a = new TarjetaProf(trm, 22, 23);
        t16a.getNotas().add(new Conceito(new Aluno("1", trm), 6, 2));
        t16a.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t16a.getNotas().add(new Conceito(new Aluno("3", trm), 3, 1));
        t16a.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t16a.getNotas().add(new Conceito(new Aluno("5", trm), 2, 3));
        trm = "6aB";
        TarjetaProf t16b = new TarjetaProf(trm, 23, 23);
        t16b.getNotas().add(new Conceito(new Aluno("1", trm), 2, 2));
        t16b.getNotas().add(new Conceito(new Aluno("2", trm), 3, 5));
        t16b.getNotas().add(new Conceito(new Aluno("3", trm), 6, 1));
        t16b.getNotas().add(new Conceito(new Aluno("4", trm), 8, 5));
        t16b.getNotas().add(new Conceito(new Aluno("5", trm), 1, 3));
        trm = "6aC";
        TarjetaProf t16c = new TarjetaProf(trm, 22, 23);
        t16c.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t16c.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t16c.getNotas().add(new Conceito(new Aluno("3", trm), 3, 1));
        t16c.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t16c.getNotas().add(new Conceito(new Aluno("5", trm), 2, 3));
        trm = "6aD";
        TarjetaProf t16d = new TarjetaProf(trm, 23, 23);
        t16d.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t16d.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t16d.getNotas().add(new Conceito(new Aluno("3", trm), 5, 1));
        t16d.getNotas().add(new Conceito(new Aluno("4", trm), 3, 5));
        t16d.getNotas().add(new Conceito(new Aluno("5", trm), 4, 3));
        ProfSheet sheet1 = new ProfSheet(Periodo.BIMESTRE_1);
        sheet1.getTarjetas().add(t16a);
        sheet1.getTarjetas().add(t16b);
        sheet1.getTarjetas().add(t16c);
        sheet1.getTarjetas().add(t16d);
        // 2o bim
        trm = "6aA"; 
        TarjetaProf t26a = new TarjetaProf(trm, 22, 23);
        t26a.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t26a.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t26a.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t26a.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t26a.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aB";
        TarjetaProf t26b = new TarjetaProf(trm, 23, 23);
        t26b.getNotas().add(new Conceito(new Aluno("1", trm), 2, 5));
        t26b.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t26b.getNotas().add(new Conceito(new Aluno("3", trm), 1, 9));
        t26b.getNotas().add(new Conceito(new Aluno("4", trm), 5, 4));
        t26b.getNotas().add(new Conceito(new Aluno("5", trm), 3, 9));
        trm = "6aC";
        TarjetaProf t26c = new TarjetaProf(trm, 22, 23);
        t26c.getNotas().add(new Conceito(new Aluno("1", trm), 10, 2));
        t26c.getNotas().add(new Conceito(new Aluno("2", trm), 10, 5));
        t26c.getNotas().add(new Conceito(new Aluno("3", trm), 4, 11));
        t26c.getNotas().add(new Conceito(new Aluno("4", trm), 8, 5));
        t26c.getNotas().add(new Conceito(new Aluno("5", trm), 4, 15));
        trm = "6aD";
        TarjetaProf t26d = new TarjetaProf(trm, 23, 23);
        t26d.getNotas().add(new Conceito(new Aluno("1", trm), 1, 10));
        t26d.getNotas().add(new Conceito(new Aluno("2", trm), 2, 9));
        t26d.getNotas().add(new Conceito(new Aluno("3", trm), 3, 8));
        t26d.getNotas().add(new Conceito(new Aluno("4", trm), 4, 12));
        t26d.getNotas().add(new Conceito(new Aluno("5", trm), 2, 15));
        ProfSheet sheet2 = new ProfSheet(Periodo.BIMESTRE_2);
        sheet2.getTarjetas().add(t26a);
        sheet2.getTarjetas().add(t26b);
        sheet2.getTarjetas().add(t26c);
        sheet2.getTarjetas().add(t26d);
        // 3o bim
        trm = "6aA"; 
        TarjetaProf t36a = new TarjetaProf(trm, 22, 23);
        t36a.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t36a.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t36a.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t36a.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t36a.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aB";
        TarjetaProf t36b = new TarjetaProf(trm, 23, 23);
        t36b.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t36b.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t36b.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t36b.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t36b.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aC";
        TarjetaProf t36c = new TarjetaProf(trm, 22, 23);
        t36c.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t36c.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t36c.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t36c.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t36c.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aD";
        TarjetaProf t36d = new TarjetaProf(trm, 23, 23);
        t36d.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t36d.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t36d.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t36d.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t36d.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        ProfSheet sheet3 = new ProfSheet(Periodo.BIMESTRE_3);
        sheet3.getTarjetas().add(t36a);
        sheet3.getTarjetas().add(t36b);
        sheet3.getTarjetas().add(t36c);
        sheet3.getTarjetas().add(t36d);        
        // 4o bim
        trm = "6aA"; 
        TarjetaProf t46a = new TarjetaProf(trm, 22, 23);
        t46a.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t46a.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t46a.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t46a.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t46a.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aB";
        TarjetaProf t46b = new TarjetaProf(trm, 23, 23);
        t46b.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t46b.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t46b.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t46b.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t46b.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aC";
        TarjetaProf t46c = new TarjetaProf(trm, 22, 23);
        t46c.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t46c.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t46c.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t46c.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t46c.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        trm = "6aD";
        TarjetaProf t46d = new TarjetaProf(trm, 23, 23);
        t46d.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        t46d.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        t46d.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        t46d.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        t46d.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        ProfSheet sheet4 = new ProfSheet(Periodo.BIMESTRE_4);
        sheet4.getTarjetas().add(t46a);
        sheet4.getTarjetas().add(t46b);
        sheet4.getTarjetas().add(t46c);
        sheet4.getTarjetas().add(t46d); 
        
        this.file2 = new ProfFile("MARIA", "00118-Geografia", "prof2");
        this.file2.getSheets().add(sheet1);
        this.file2.getSheets().add(sheet2);
        this.file2.getSheets().add(sheet3);
        this.file2.getSheets().add(sheet4);
        fillTarjetas(file2);
    }
    
    private void fillTarjetas(ProfFile f) {
    	
    	for (ProfSheet s: f.getSheets()) {
    		for (TarjetaProf t: s.getTarjetas()) {
    			for (int i=t.getNotas().size()+1; i<=OdsParser.MAX_ALUNOS; i++) {
    				t.getNotas().add(new Conceito(
    						new Aluno(Integer.toString(i), t.getTurma()), 0, 0));
    			}
    		}
    	}
    }

}
