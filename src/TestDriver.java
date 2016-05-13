//Erik Olsen
//Driver class to test the Game State framework functionallity gamestate methods. 
// 5/8/2106

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestDriver extends JPanel{
	
	//static JFrame window = new JFrame(); 
	private double scale = 2.5;
	stateEngine cQuest = new stateEngine(); 
	int frameRate=144;//the framerate of the game 
	int count = 0;
	private Component window;
	private BufferedImage GamePicture;
	private Graphics g;

	
	public TestDriver(){
		TextureLoader.loadList(scale);
		GamePicture = new BufferedImage((int)Math.floor(TextureLoader.getMapWidth()*16*scale), (int) Math.floor(TextureLoader.getMapHeight()*16*scale), BufferedImage.TYPE_INT_ARGB);
		g = GamePicture.createGraphics();
		//BufferedImage GamePicture = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = GamePicture.createGraphics();
        //g2d.setTransform(AffineTransform.getScaleInstance(-1, 1));
        g2d.drawImage(GamePicture, 0, 0, this);
        g2d.dispose();
		
        
        
		
		
	}
	
	public void start() {
		
		JFrame frame = new JFrame("Game Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new TestDriver());
        //frame.setSize((int)Math.floor(TextureLoader.getMapWidth()*16*scale), (int) Math.floor(TextureLoader.getMapHeight()*16*scale)+2*(int)Math.floor(16*scale));
        frame.setSize(GamePicture.getWidth(),GamePicture.getHeight());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        window = frame;
        
		// TODO Auto-generated method stub
		
		
		//game state for everey game mode
		
		cQuest.Add("Menu", new GamStMenu());
		cQuest.Add("World", new GamStWorld(window));
		//cQuest.change("Menu");//changes the selected window to Menu
		//cQuest.change("World");//changes the selected window to Menu
		
		//System.out.println("Driver window def");
		//window.setBounds(0,0,1800,900);  //initial frame deffinition 
		
	    //loop42(window);//game loop call;
		loop42(window);//game loop call;
	    	
	}
	
	
	public void loop42(Component window) //Primary game loop 
	{
		long loopTime=0;//time for a single loop to be completed. must be consistantly below the frametime to achieve selected framerate
		long frameTime=1000/frameRate;//milliseconds bettween frames 
		int wait=0;
		while(true)
		{	
			long loopStart=System.currentTimeMillis();
			paintComponent(g);
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
	@Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        
        cQuest.Render(g);
        
        repaint();

        //for (Fish fish : fishes) {
        //    fish.drawFishImage(g);
      //  }

    }
   public void gameLoopUpdate(long frameTime){
	  
	   for(int i=0;i<((int)frameTime);i++);//changes the game update rate based on hte frame rate. 
		{
			cQuest.Update(window);//calls update method of 	
		}		     
   }
}

