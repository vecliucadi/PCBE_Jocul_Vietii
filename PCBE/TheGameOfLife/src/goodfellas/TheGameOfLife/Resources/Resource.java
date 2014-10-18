package goodfellas.TheGameOfLife.Resources;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import goodfellas.TheGameOfLife.Cells.ASCell;
import goodfellas.TheGameOfLife.Cells.AbstractCell;
import goodfellas.TheGameOfLife.Cells.IDieListener;
import goodfellas.TheGameOfLife.Cells.IFoodListener;
import goodfellas.TheGameOfLife.Cells.IReproductionListener;
import goodfellas.TheGameOfLife.Cells.SCell;

public class Resource {
	private int foodAmount;
	private Object foodLock = new Object();
	private List<SCell> readyForReproduction = Collections.synchronizedList(new ArrayList<>());
	
	private static class Loader {
		static Resource instance = new Resource(); 
	}	
	
	protected Resource() {
		foodAmount = (int) Math.ceil((Math.random() * 100));
		
	}

	public AbstractCell creatCell(int T_HUNGRY, int T_STARVE) {
		AbstractCell cell = null;
		
		if (Math.random() > 0.5) {
			cell = new ASCell(T_HUNGRY, T_STARVE);
		}
		else {
			cell = new SCell(T_HUNGRY, T_STARVE);
		}
		
		cell.addHungryListener(new IFoodListener() {
			@Override
			public int getFood() {
				synchronized (foodLock) {
					if (foodAmount > 0) {
						foodAmount -= 1;
						return 1;
					}
					
					return 0;
				}
			}
		});
		
		cell.addDeathListener(new IDieListener() {

			@Override
			public void cellDied() {
				synchronized (foodLock) {
					foodAmount += (int)Math.ceil(Math.random() * 4) + 1;
				}
			}
		});
		
		cell.addReproductionListener(new IReproductionListener() {
			@Override
			public void cellReproduced(SCell cell) {
				synchronized (readyForReproduction) {
					readyForReproduction.add(cell);
				}
				
			}

			@Override
			public SCell findMate() {
				synchronized (readyForReproduction) {
					if (readyForReproduction.isEmpty()) {
						return null;
					}
					return readyForReproduction.remove(0);
				}
			}
			
		});
	
		
		return cell;
	}
	
	public Resource getInstance() {
		return Loader.instance;
	}
	
	
	
}
