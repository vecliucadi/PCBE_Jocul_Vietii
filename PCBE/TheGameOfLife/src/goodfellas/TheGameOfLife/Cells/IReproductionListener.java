package goodfellas.TheGameOfLife.Cells;

interface IReproductionListener
	{
	void cellReproduced();
	SCell findMate();
	}
