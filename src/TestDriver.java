//Erik Olsen
//Marcus Moad
//Driver class to test the Game State framework functionallity gamestate methods. 
// 5/8/2106

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestDriver extends JPanel{
	
	//static JFrame window = new JFrame(); 
	private static double scale = 4;
	//stateEngine cQuest = new stateEngine(); 
	private int TickRate = 144;
	private int skipTicks = 1000/TickRate;
	private int frameRate=144;//the framerate of the game //Marcus: Somehow the physics of the game is tied to framerate.  I don't know if it is my code or the way this driver works that is causing it.
	int cQuest = 0;//I did some test and it turns out that the gameUpdate is bound by the framerate value and the Render function is NOT.  This means that frameRate actually sets the rate of the game physics. TODO 
	private Component window;
	private BufferedImage GamePicture;
	private Graphics g;
	private InputManager input;
	private static PlayerTestEntity player;
	private static Overlay overlay;
	private int MAX_FRAMESKIP = 40;//wth does this do marcus -Erik TODO

	public static PlayerTestEntity getPlayer(){
		return player;
	}
	
	public TestDriver(){
		TextureLoader.loadList(scale);
		
		GamePicture = new BufferedImage((int)Math.floor(TextureLoader.getMapWidth()*16*scale), (int) Math.floor(TextureLoader.getMapHeight()*16*scale), BufferedImage.TYPE_INT_ARGB);
		g = GamePicture.createGraphics();
		//BufferedImage GamePicture = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = GamePicture.createGraphics();
        //g2d.setTransform(AffineTransform.getScaleInstance(-1, 1));
        g2d.drawImage(GamePicture, 0, 0, this);
        
        g2d.dispose();
        
        input = new InputManager();
		
        
        
		
		
	}
	
	//This starts the Game Engine
	public void start() {
		
		Component COMPONENT = new TestDriver();
		JFrame frame = new JFrame("SpriteSouls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(COMPONENT);
        //frame.setSize((int)Math.floor(TextureLoader.getMapWidth()*16*scale), (int) Math.floor(TextureLoader.getMapHeight()*16*scale)+2*(int)Math.floor(16*scale));
        frame.setSize(GamePicture.getWidth(),GamePicture.getHeight());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        window = frame;
        //this.addKeyListener(input);
        window.addKeyListener(input);
        
        //COMPONENT.addKeyListener(input);
        this.setDoubleBuffered(true);
        player = new PlayerTestEntity(window);
        stateEngine.setOverlay(new Overlay(window));
        stateEngine.Add("Menu", new GamStMenu(window));
        stateEngine.Add("World", new GamStWorld(window));
      //  this.addComponentListener(new ComponentAdapter(){
        	//public void componentShown(Component window) {
        	//	loop42(window);//game loop call;
		//	}
      //  });
       
        
		
		
		//game state for every game mode
		
		
		//cQuest.change("Menu");//changes the selected window to Menu
		//cQuest.change("World");//changes the selected window to Menu
		
		//System.out.println("Driver window def");
		//window.setBounds(0,0,1800,900);  //initial frame deffinition 
		
	    loop42(window);//game loop call;
		//loop42(window);//game loop call;
	    	
	}
	
	
	//private void addComponentListener(ComponentAdapter componentAdapter) {
	//	// TODO Auto-generated method stub
		
	//}

	public void loop42(Component window) //Primary game loop 
	{
		long loopTime=0;//time for a single loop to be completed. must be consistantly below the frametime to achieve selected framerate
		long frameTime=1000/frameRate;//milliseconds bettween frames 
		int wait=0;
		int frames_Skipped = 0;
		int loops = 0;
		long interpolation = 0;
		long loopStart=System.currentTimeMillis();
		long nextTick = loopStart;
		Boolean running = true;
		stateEngine.Update(window);
		paintComponent(g);
		
		while(running){
			loops = 0;
			loopStart=System.currentTimeMillis();
			//nextTick = System.currentTimeMillis();
			frames_Skipped = 0;
			//stateEngine.Update(window);
			
			while(loopStart>nextTick && loops < MAX_FRAMESKIP){
				stateEngine.Update(window);
				nextTick+=skipTicks;
				loops++;
			}
			loopTime=System.currentTimeMillis()-loopStart;
			wait = (int)(frameTime-loopTime);
			if(wait>0){
				try{
					Thread.sleep(wait);
					System.out.println("wait: " + wait);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			window.repaint();
			//loopStart=System.currentTimeMillis();
			//window.repaint();
			//loopTime=System.currentTimeMillis()-loopStart;
			//interpolation = (long)(System.currentTimeMillis()-loopStart + skipTicks - nextTick)/(long)(skipTicks);
			//paintComponent(g);
			//gameLoopUpdate(interpolation);
			//window.repaint();
			//while(System.currentTimeMillis()-loopStart>=frameTime){
				//paintComponent(g);
				
			//}
			//else{
				//frames_Skipped++;
		//	}
			
			//Render(interpolation, g);
			
		}
		
		/*
		while(running)
		{	
			
			loopStart=System.currentTimeMillis();
			frames_Skipped = 0;
			stateEngine.Update(window);
			//paintComponent(g);
			window.repaint();
			loopTime=System.currentTimeMillis()-loopStart;
			System.out.println("loops this cycle"+ loopTime +" time ");
			wait = (int)(skipTicks-loopTime);
			if(wait>0){
				try {
					Thread.sleep(wait);
					System.out.println("wait: " + wait);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			while(wait < 0 && frames_Skipped < MAX_FRAMESKIP ){
				stateEngine.Update(window);
				wait += frameTime;
				frames_Skipped++;
				System.out.println("Frames skipped: " + frames_Skipped);
			}
			
			//System.out.println("loops this cycle"+loopTime/frameTime+" time ");
			//for(int i=0;i<((int)loopTime/frameTime);i++);//call the game loop more frequently if the update time is longer than the target frame time
			//{
			//	gameLoopUpdate(loopTime);
				
			//}
			//if(loopTime>=frameTime)//prevents below zero thread stops 
			//{
			//	wait=0;
			//}
			//else
			//{
			// wait=(int) (frameTime-loopTime);
			//}
			
			//System.out.println(1000/(loopTime+1));	
		}
		*/
	}
	/*
	private void Render(float interp, Graphics g){
		
		for(float i=0;i<(interp);i++);//changes the game update rate based on hte frame rate. 
		{
			//paintComponent(g);
			//stateEngine.Update(window);//calls update method of 	
		}		 
		
		
	}
	*/
	@Override
    protected void paintComponent(Graphics g) {
		//WHY THE FUCK IS THIS CALLED EVEN WHEN IT IS NEVER CALLED IN THE LOOP42?????
        super.paintComponent(g);
        
        stateEngine.Render(g);
        
        //repaint();

        //for (Fish fish : fishes) {
        //    fish.drawFishImage(g);
      //  }

    }
	public static double getScale(){
		return scale;
	}
   public void gameLoopUpdate(long frameTime){
	  
	   for(int i=0;i<((int)frameTime);i++);//changes the game update rate based on hte frame rate. 
		{
			stateEngine.Update(window);//calls update method of 	
		}		     
   }
}

