//Erik Olsen
//Driver class to test the Game State framework functionallity gamestate methods. 
// 5/8/2016

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class TestDriver {
	
	static JFrame window = new JFrame(); 
	static stateEngine cQuest = new stateEngine(); 
	private static long lastTime;
	private static int fps;
	private static int lastFpsTime;
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//game state for each game mode
		cQuest.Add("Menu", new GamStMenu());
		cQuest.Add("World", new GamStWorld());
		cQuest.change("Menu");
		
		System.out.println("Driver window def");
		window.setBounds(0,0,1800,900);  /initial jFrame def
	        loop42(cQuest);
	        
		
	}
	
	public static void loop42(stateEngine cQuest)//primary game loop
	{
	    lastTime =System.nanoTime();  
	    int targetFPS = 144;
	    float targetFpsMS= (1000/targetFPS);
	    System.out.println(targetFpsMS);
            lastTime=System.nanoTime();
	   
	   while (true)
	   {
              // block determines the loop time 
	      long curSysT =System.nanoTime(); 
	      long frameTimeMS = ((curSysT-lastTime)/1000000);
	      lastTime=curSysT;
	      lastFpsTime += frameTimeMS;
	      fps++;
 
              
	      if (lastFpsTime >= 1000)
	      {
	         System.out.println("(FPS: "+fps+")");
	         lastFpsTime = 0;
	         fps = 0;
	      }
	      
	      
	      //System.out.println(targetFpsMS);
	      System.out.println(frameTimeMS);
	      try {
			Thread.sleep((long)(targetFpsMS-frameTimeMS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	       doGameUpdates(frameTimeMS);
 
	       cQuest.Render(window);
	   }
	}
	private static void doGameUpdates(long frameTimeMS)

	{   	
		for(int i=0;i<frameTimeMS;i++
	    		cQuest.update(frameTimeMS);  		
	}
   
}
