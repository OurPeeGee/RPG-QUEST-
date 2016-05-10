//Erik Olsen
//Driver class to test the Game State framework functionallity gamestate methods. 
// 5/8/2106

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
		
		lastTime=0;
		//game state for each game mode
		cQuest.Add("Menu", new GamStMenu());
		cQuest.Add("World", new GamStWorld());
		cQuest.change("Menu");
		
		System.out.println("Driver window def");
		window.setBounds(0,0,1800,900);  
	    loop42(cQuest);
	    
		
	}
	
	public static void loop42(stateEngine cQuest)
	{
	    lastTime =System.nanoTime();  
	    int targetFPS = 144;
	    float targetFpsMS= (1000/targetFPS);
	    System.out.println(targetFpsMS);
	   
	   while (true)
	   {
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
	      
	      cQuest.Render(window);
	      //System.out.println(targetFpsMS);
	      System.out.println(frameTimeMS);
	      try {
			Thread.sleep((long)(Math.abs(targetFpsMS-frameTimeMS)+1));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      doGameUpdates(frameTimeMS);
	   }
	}
	private static void doGameUpdates(long frameTimeMS)
	{   	
		
	    		cQuest.update(frameTimeMS);  	
		
	}
   
}