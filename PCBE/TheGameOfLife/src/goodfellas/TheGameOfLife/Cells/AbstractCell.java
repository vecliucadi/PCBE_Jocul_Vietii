package goodfellas.TheGameOfLife.Cells;

public abstract class AbstractCell implements Runnable
{
private int T_Full;
private int T_Starve;
private int timesItAte=0;
private CellState cellState=CellState.HUNGRY;
private IFoodListener foodListener;
private IDieListener dieListener;
private IReproductionListener reproductionListener;

protected AbstractCell(int T_Full,int T_Starve)
	{
	this.T_Full=T_Full;
	this.T_Starve=T_Starve;
	}

public abstract void reproduce();

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
}
