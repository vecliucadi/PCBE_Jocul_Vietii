package goodfellas.TheGameOfLife.Cells;

public class ASCell extends AbstractCell{
	
	public ASCell(int T_Full,int T_Starve){
		super(T_Full,T_Starve);
	}
	
	
	public void reproduce(){
		
		ASCell asCell1=new ASCell(123,12);
		
		if(asCell1.isReadyForReproduction()){
			(new Thread(new ASCell(123,12))).run();
			(new Thread(new ASCell(123,12))).run();
		}
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
