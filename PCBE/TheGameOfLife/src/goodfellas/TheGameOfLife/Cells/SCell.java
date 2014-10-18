package goodfellas.TheGameOfLife.Cells;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SCell extends AbstractCell{
	
	
	public SCell(int T_Full,int T_Starve){
		super(T_Full,T_Starve);
	}
	
	
	public void reproduce(){
		
		Random randomizer = new Random();
		List<AbstractCell> list = new ArrayList<AbstractCell>();
		  list.add(new ASCell(123,3));
		  list.add(new SCell(123,3));

		
		SCell sCell1=new SCell(123,3);
		

		if(sCell1.isReadyForReproduction()){
			sCell1.findMate();
			(new Thread(list.get(randomizer.nextInt(list.size())))).run();
		
		}
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
