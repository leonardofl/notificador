package sp.alvaro;

import java.util.HashSet;
import java.util.Set;

import sp.alvaro.model.Aluno;
import sp.alvaro.model.Conceito;
import sp.alvaro.model.Periodo;
import sp.alvaro.model.Tarjeta;
import sp.alvaro.model.TurmaFile;
import sp.alvaro.model.TurmaSheet;

public class ValoresTurma {

    public static String MEDIAS = "Médias";

    public Set<TurmaFile> generateData() {
        
        Set<TurmaFile> turmaFiles = new HashSet<TurmaFile>();
        turmaFiles.add(this.create6A());
        turmaFiles.add(this.create6B());
        turmaFiles.add(this.create6C());
        turmaFiles.add(this.create6D());
        
        return turmaFiles;
    }
    
    private TurmaFile create6A() {
        
        String trm = "6aA";
        TurmaFile turmaFile = new TurmaFile(trm);
        
        Periodo bim = Periodo.BIMESTRE_1;
        TurmaSheet turmaSheet = new TurmaSheet(bim, trm);
        	
        Tarjeta tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 6, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 3, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 2, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 6, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 3, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 2, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_2;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_3;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_4;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);
        
        bim = Periodo.ANO;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 88, 23*4);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5.25, 8));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 20));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 7.5, 4));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 20));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 7.25, 12));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 88, 23*4);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5.25, 8));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 20));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 7.5, 4));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 20));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 7.25, 12));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        return turmaFile;
    }

    private TurmaFile create6B() {
        
        String trm = "6aB";
        TurmaFile turmaFile = new TurmaFile(trm);
        
        Periodo bim = Periodo.BIMESTRE_1;
        TurmaSheet turmaSheet = new TurmaSheet(bim, trm);

        Tarjeta tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 2, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 3, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 6, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 8, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 1, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 2, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 3, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 6, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 8, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 1, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_2;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 2, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 1, 9));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 5, 4));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 3, 9));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 2, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 1, 9));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 5, 4));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 3, 9));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_3;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        bim = Periodo.BIMESTRE_4;
        turmaSheet = new TurmaSheet(bim, trm);

        turmaSheet = new TurmaSheet(Periodo.BIMESTRE_4, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);
        
        bim = Periodo.ANO;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 92, 23*4);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 3.5, 11));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 4.5, 20));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 6.25, 12));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 5.25, 19));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 5.5, 18));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 92, 23*4);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 3.5, 11));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 4.5, 20));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 6.25, 12));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 5.25, 19));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 5.5, 18));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        return turmaFile;
    }

    private TurmaFile create6C() {
        
        String trm = "6aC";
        TurmaFile turmaFile = new TurmaFile(trm);
        
        Periodo bim = Periodo.BIMESTRE_1;
        TurmaSheet turmaSheet = new TurmaSheet(bim, trm);

        Tarjeta tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 3, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 2, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 3, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 2, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_2;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 10, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 10, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 4, 11));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 8, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 4, 15));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 10, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 10, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 4, 11));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 8, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 4, 15));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_3;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_4;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.ANO;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 88, 23*4);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 6.25, 8));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 6.25, 20));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 6.25, 14));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 5, 20));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 6, 24));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 88, 23*4);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 6.25, 8));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 6.25, 20));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 6.25, 14));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 5, 20));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 6, 24));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);
        
        return turmaFile;
    }

    private TurmaFile create6D() {
        
        String trm = "6aD";
        TurmaFile turmaFile = new TurmaFile(trm);
        
        Periodo bim = Periodo.BIMESTRE_1;
        TurmaSheet turmaSheet = new TurmaSheet(bim, trm);

        Tarjeta tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 5, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 3, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 4, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 5, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 3, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 4, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_2;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 1, 10));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 2, 9));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 3, 8));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 12));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 2, 15));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 1, 10));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 2, 9));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 3, 8));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 12));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 2, 15));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_3;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        bim = Periodo.BIMESTRE_4;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 22, 23);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 5, 2));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 5, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 9, 1));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 4, 5));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 9, 3));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);
        
        bim = Periodo.ANO;
        turmaSheet = new TurmaSheet(bim, trm);

        tarjeta = new Tarjeta(trm, "00119-Inglês", "MANOEL", bim, 92, 23*4);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 4, 16));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 4.25, 24));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 6.5, 11));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 3.75, 27));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 6, 24));
        turmaSheet.getTarjetas().add(tarjeta);

        tarjeta = new Tarjeta(trm, "00118-Geografia", "MARIA", bim, 92, 23*4);
        tarjeta.getNotas().add(new Conceito(new Aluno("1", trm), 4, 16));
        tarjeta.getNotas().add(new Conceito(new Aluno("2", trm), 4.25, 24));
        tarjeta.getNotas().add(new Conceito(new Aluno("3", trm), 6.5, 11));
        tarjeta.getNotas().add(new Conceito(new Aluno("4", trm), 3.75, 27));
        tarjeta.getNotas().add(new Conceito(new Aluno("5", trm), 6, 24));
        turmaSheet.getTarjetas().add(tarjeta);

        turmaFile.getSheets().add(turmaSheet);

        return turmaFile;
    }

}
