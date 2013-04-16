package sp.alvaro.odf;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Table;

public class SaferCellRetriever {
	
	/**
	 * Retorna a celula de uma tabela de acordo com a posição fornecida.
	 * Existe um bug no odf tool kit que faz com que em algumas situações
	 * a thread trave na hora de ler a célula.
	 * Caso isso aconteça, lançaremos a exceção OdfReadCellException.
	 * 
	 * @param table
	 * @param cellPos
	 * @return
	 * @throws OdfReadCellException
	 */
	public Cell getCell(Table table, String cellPos) throws OdfReadCellException {
	
		final int TIMEOUT_SECONDS = 5;
		CellReader cellReader = new CellReader(table, cellPos);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Cell> f = executor.submit(cellReader);
		
		executor.shutdown();
		try {
			executor.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new OdfReadCellException();
		}
		executor.shutdownNow();
		
		if (f.isDone()) {
			Cell cell = null;
			try {
				cell = f.get();
			} catch (InterruptedException e) {
				throw new OdfReadCellException();
			} catch (ExecutionException e) {
				throw new OdfReadCellException();
			}
			return cell;
		} else {
			throw new OdfReadCellException();
		}
	}
	
	private class CellReader implements Callable<Cell> {

		Table table; 
		String cellPos;
		
		public CellReader(Table table, String cellPos) {
			this.table = table;
			this.cellPos = cellPos;
		}
		
		@Override
		public Cell call() throws Exception {
			return table.getCellByPosition(cellPos);
		}
		
	}
	
}
