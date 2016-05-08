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
		
		
		JFrame window = new JFrame();
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(10, 0, 1024, 768);
	    window.getContentPane().add(new MyCanvas(20,16, null));
	    window.setVisible(true);
	    loop42(cQuest);
	    
		
	}
	public static void loop42(stateEngine cQuest)
	{
	   long lastLoopTime = System.nanoTime();
	   final int TARGET_FPS = 60;
	   final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   

	   
	   while (true)
	   {
	      
	      long now = System.nanoTime();
	      long updateLength = now - lastLoopTime;
	      lastLoopTime = now;
	      double delta = updateLength / ((double)OPTIMAL_TIME);

	      
	      lastFpsTime += updateLength;
	      fps++;
	      
	      if (lastFpsTime >= 1000000000)
	      {
	         System.out.println("(FPS: "+fps+")");
	         lastFpsTime = 0;
	         fps = 0;
	      }
	      
	      
	      doGameUpdates(delta);
	      
	    
	      cQuest.Render(window);
	      
	      //try{Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000)};
	   }
	}

	private static void doGameUpdates(double elapT)
	{
	   
	    cQuest.update((float) elapT);  
	      
	      
	   
	}
   
}