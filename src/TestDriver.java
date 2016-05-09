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
	private static GamSt GamStMenu;
	private static int lastFpsTime;
	private static int fps;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		lastTime=0;
		// A state for each game mode
		cQuest.Add("Menu", new GamStMenu());
		cQuest.Add("World", new GamStWorld());
		cQuest.change("Menu");
		
		System.out.println("welcome to the smack down brother");
		   
	    loop42(cQuest);
	    
		
	}
	
	public static void loop42(stateEngine cQuest)
	{
	   long lastLoopTime = System.nanoTime();
	   final int FrameTarget = 60;
	   final long TimeTarget = 1000000000 / FrameTarget;   

	   
	   while (true)
	   {
	      
	      long now = System.nanoTime();
	      long updateLength = now - lastLoopTime;
	      lastLoopTime = now;
	      double elapT = updateLength / ((double)TimeTarget);

	      
	      lastFpsTime += updateLength;
	      fps++;
	     // System.out.println("(FrameTime: "+lastFpsTime+")");
	      
	      if(lastFpsTime >= 1000000000)
	      {
	         System.out.println("(FPS: "+fps+")");
	         lastFpsTime = 0;
	         fps = 0;
	      }
	      
	      doGameUpdates(elapT);
	      
	    
	      cQuest.Render(window);
	      
	     try {
			Thread.sleep((Math.abs(lastLoopTime-System.nanoTime()) + TimeTarget)/1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	}

	private static void doGameUpdates(double elapT)
	{
	   
		    cQuest.update((float) elapT);  
	   
	      
	      
	      
	   
	}
   
}