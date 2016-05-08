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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		lastTime=0;
		// A state for each game mode
		cQuest.Add("Menu", new GamStMenu());
		cQuest.Add("World", new GamStWorld());
		
		
		cQuest.change("Menu");
		
		System.out.println("welcome to the smack down brother");
		
		System.out.println("Menu Update");
		JFrame window = new JFrame();
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(10, 0, 1024, 768);
	    window.getContentPane().add(new MyCanvas(20,16, null));
	    window.setVisible(true);
	    
		
	}
	public static void loop42(long startTime)
	{
		while(true)
		{
			cQuest.update(GetElapsedFrameTime());
			while(true)
			{
				System.out.println("welcome to the smack down brother");
				cQuest.Render();
			}
			
		}
		
	}
	public static void Update()
	{
		cQuest.update(lastTime);
	    
	}
	private static long GetElapsedFrameTime() {
		return lastTime;
		// TODO Auto-generated method stub
		
		
	}
   
}