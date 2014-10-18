package goodfellas.TheGameOfLife.Cells;

public interface IReproductionListener {
	void cellReproduced(SCell cell);

	SCell findMate();
}
