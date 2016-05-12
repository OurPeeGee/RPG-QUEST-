//Erik Olsen
//Driver class to test the Game State framework functionallity gamestate methods. 
// 5/8/2106

import javax.swing.JFrame;

public class TestDriver {
	
	static JFrame window = new JFrame(); 
	static stateEngine cQuest = new stateEngine(); 
	static int frameRate=144;/the framerate of the game 
	static int count = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//game state for everey game mode
		cQuest.Add("Menu", new GamStMenu());
		cQuest.Add("World", new GamStWorld());
		cQuest.change("Menu");//changes the selected window to Menu
		
		System.out.println("Driver window def");
		window.setBounds(0,0,1800,900);  //initial frame deffinition 
		
	    loop42(window);//game loop call;
	    	
	}
	public static void loop42(JFrame window) //Primary game loop 
	{
		long loopTime=0;//time for a single loop to be completed. must be consistantly below the frametime to achieve selected framerate
		long frameTime=1000/frameRate;//milliseconds bettween frames 
		int wait=0;
		while(true)
		{	
			long loopStart=System.currentTimeMillis();
			cQuest.Render(window);
			//System.out.print("loops this cycle"+loopTime/frameTime+" time ");
			for(int i=0;i<((int)loopTime/frameTime);i++);//call the game loop more frequently if the update time is longer than the target frame time
			{
				gameLoopUpdate(loopTime);
				
			}
			if(loopTime>=frameTime)//prevents below zero thread stops 
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
	  
	   for(int i=0;i<((int)frameTime);i++);//changes the game update rate based on hte frame rate. 
		{
			cQuest.Update(window);//calls update method of 	
		}		     
   }
}

