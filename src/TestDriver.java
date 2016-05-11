//Erik Olsen
//Driver class to test the Game State framework functionallity gamestate methods. 
// 5/8/2106

import javax.swing.JFrame;

public class TestDriver {
	
	static JFrame window = new JFrame(); 
	static stateEngine cQuest = new stateEngine(); 
	static int frameRate=100;
	static int count = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//game state for each game mode
		cQuest.Add("Menu", new GamStMenu());
		cQuest.Add("World", new GamStWorld());
		cQuest.change("Menu");
		
		System.out.println("Driver window def");
		window.setBounds(0,0,1800,900);  
		
	    loop42(window);
	    	
	}
	public static void loop42(JFrame window) 
	{
		long loopTime=0;
		long frameTime=1000/frameRate;
		int wait=0;
		while(true)
		{	
			long loopStart=System.currentTimeMillis();
			cQuest.Render(window);
			//System.out.print("loops this cycle"+loopTime/frameTime+" time ");
			for(int i=0;i<((int)loopTime/frameTime);i++);
			{
				gameLoopUpdate(loopTime);
				
			}
			if(loopTime>=frameTime)
			{
				wait=0;
			}
			else
			{
			 wait=(int) (frameTime-loopTime);
			}
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loopTime=System.currentTimeMillis()-loopStart;
			//System.out.println(loopTime);	
		}
	}
   public static void gameLoopUpdate(long frameTime){
	  
	   for(int i=0;i<((int)frameTime);i++);
		{
			cQuest.Update(window);	
		}		     
   }
}

