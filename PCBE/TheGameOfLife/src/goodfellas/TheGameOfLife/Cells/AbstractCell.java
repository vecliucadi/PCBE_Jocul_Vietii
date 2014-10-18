package goodfellas.TheGameOfLife.Cells;

public abstract class AbstractCell implements Runnable
{
private int T_Full;
private int T_Starve;
private int timesItAte=0;
private CellState cellState=CellState.HUNGRY;
private IFoodListener foodListener;
private IDieListener dieListener;
protected IReproductionListener reproductionListener;

protected AbstractCell(int T_Full,int T_Starve)
	{
	this.T_Full=T_Full;
	this.T_Starve=T_Starve;
	}

public abstract void reproduce(AbstractCell cell);

public void addHungryListener(IFoodListener foodListener)
	{
	this.foodListener=foodListener;
	}

public void addDeathListener(IDieListener dieListener)
	{
	this.dieListener=dieListener;
	}

public void addReproductionListener(IReproductionListener repListener) 
	{
	this.reproductionListener=repListener;
	}

public boolean isReadyForReproduction()
	{
	if (timesItAte>=10) 
		return true;
	return false;
	}

public CellState getState()
	{
	return cellState;
	}

public void run() 
	{
	try
		{
		switch(cellState)
			{
			case HUNGRY: 
				{
				if (foodListener.getFood()>0)
					{
					timesItAte++;
					if (!isReadyForReproduction())
						cellState=CellState.HAPPY;
					else cellState=CellState.REPRODUCE;
					}
				else 
					{
					wait(T_Full*timesItAte);
					cellState=CellState.STARVING;
					}
				
				};break;
			case HAPPY:
				{
				if (isReadyForReproduction())
					cellState=CellState.REPRODUCE;
				wait(T_Full*timesItAte);
				};break;
			case REPRODUCE:
				{
				reproduce(this);
				timesItAte=0;
				cellState=CellState.HUNGRY;
				}
			case STARVING:
				{
				if (foodListener.getFood()>0)
					{
					timesItAte++;
					if (isReadyForReproduction())
						cellState=CellState.REPRODUCE;
					else
						cellState=CellState.HUNGRY;
					}
				else
					{
					wait(T_Starve);
					cellState=CellState.DEAD;
					}
				};break;
			case DEAD:
				{
				dieListener.cellDied();
				}
			}
			
		} catch (InterruptedException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}
