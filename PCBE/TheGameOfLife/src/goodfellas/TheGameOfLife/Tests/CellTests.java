package goodfellas.TheGameOfLife.Tests;

import static org.junit.Assert.*;
import goodfellas.TheGameOfLife.Cells.AbstractCell;
import goodfellas.TheGameOfLife.Cells.CellState;

import org.junit.Test;

public class CellTests {

	@Test
	/**
	 * Tets if the application can be runned successfully
	 */
	public void testRunApplication()
	{
		AbstractCell asexualCell = new ASCell(0,0);
		asexualCell.run();
		
		AbstractCell sexualCell = new SCell(0,0);
		sexualCell.run();
	}
	
	@Test
	/**
	 * Tests if a cell is in ready to reproduce state
	 */
	public void testIsReadyForReproduce()
	{
		//initial state where a asexual cell will not be ready for reproduction
		AbstractCell asexualCell = new ASCell(0,0);
		assertFalse(asexualCell.isReadyForReproduction());
		
		//initial state where a sexual cell will not be ready for reproduction
		AbstractCell sexualCell = new SCell(0,0);
		assertFalse(sexualCell.isReadyForReproduction());
	}
	
	@Test
	/**
	 * Tests if the getState method returns the correct state
	 */
	public void testGetState()
	{
		//initial happy state for asexual cell
		AbstractCell asexualCell = new ASCell(0,0);
		assertEquals(CellState.HAPPY, asexualCell.getState());
		
		//initial happy state for sexual cell
		AbstractCell sexualCell = new SCell(0,0);
		assertEquals(CellState.HAPPY,sexualCell.getState());
	}

}
