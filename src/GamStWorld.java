import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;





public class GamStWorld extends GamSt implements interState {
	
	private ArrayList<String> levels;// = TextureLoader.getLevelList();
	private String name;// = levels.get(0);
	private ArrayList<BufferedImage> layers;// = TextureLoader.getLevelMap(name);
	private Component window;
	private BufferedImage baseLayer;
	private BufferedImage transLayer;
	private BufferedImage entityLayer;
	private BufferedImage topLayer;
	
	//input Def 
	int W_KEY = KeyEvent.VK_W;
	int A_KEY = KeyEvent.VK_A;
	int S_KEY = KeyEvent.VK_S;
	int D_KEY = KeyEvent.VK_D;
	int P_KEY = KeyEvent.VK_P;
	private PlayerTestEntity player;// = TestDriver.getPlayer();
	private ArrayList<Rectangle> LevelCollisions;// = TextureLoader.getCollisions(name);
	private HashMap<String, Rectangle> Spawns;// = TextureLoader.getSpawns(name);//This list contains the spawns for the character.  multiple entrances to rooms possible
	private ArrayList<Rectangle> Exits;// = TextureLoader.getExitRects(name);
	private HashMap<String,String> exitPaths;// = TextureLoader.getExitPaths(name);
	private boolean exited = false;
	private boolean pause = false;
	private int ExitNum;
	private double scale = TestDriver.getScale();
	private BufferedImage menuImage = TextureLoader.getMenuPause();
	private static InputManager inputs;
	//private BufferedImage OverlayBase;
	//private BufferedImage Overlay;
	//private Rectangle playerRectangle;
	private int count = 0;
	
	
	public GamStWorld(Component Gwindow){
		//This needs to be passed the level it should open as well. String Name
		//levels = TextureLoader.getLevelList();//This Line and the one directly below it need to be moved to the class that calls GamStWorld.  This will allow the statEngine to determine what the next level is by passing the 
		//name = levels.get(0);//Name of the level to the GamStWorld rather than the list of levels.  This ensures that each GamSt instance only refers to ONE level, and cannot refer to multiple.
		
		name = stateEngine.getCurrentLevel();
		layers = TextureLoader.getLevelMap(name);
		player = TestDriver.getPlayer();
		LevelCollisions = TextureLoader.getCollisions(name);
		Spawns = TextureLoader.getSpawns(name);
		Exits = TextureLoader.getExitRects(name);
		exitPaths = TextureLoader.getExitPaths(name);
		//OverlayBase = TextureLoader.getLevelMap("Overlay").get(0);
		//Overlay = TextureLoader.getLevelMap("Overlay").get(1);
		//playerRectangle = new Rectangle((int)TestDriver.getPlayer().getPlayer().getX(), (int)TestDriver.getPlayer().getPlayer().getY(), (int)TestDriver.getPlayer().getPlayer().getWidth(),(int) TestDriver.getPlayer().getPlayer().getHeight());
		//input.update();
		//try {
		//	menuImage = ImageIO.read(new File("Menu.PNG"));
		//} catch (IOException e) {
		//	e.printStackTrace();
	//	}
		gsEnter();
		this.window = Gwindow;
		
		//for(int a = 0; a< layers.size(); a++){
			//BufferedImage levelMap = layers.get(a);
		baseLayer = layers.get(0);
		//transLayer = layers.get(1);
		topLayer = layers.get(1);
		
			//g.drawImage(levelMap,0,0, this);
		//}
		
	}
	
	

	
	public void Render(Graphics g) {
		// TODO Auto-generated method stub
		//System.out.println("world render");
		
		
		
		
		
		//count++;
		//System.out.println(count);
		/*g.fillRect((int)TestDriver.getPlayer().getPlayer().getX(), 
				(int)TestDriver.getPlayer().getPlayer().getY(), 
				(int)TestDriver.getPlayer().getPlayer().getWidth(),
				(int) TestDriver.getPlayer().getPlayer().getHeight());
		*/
		//g.fillRect((int)playerRectangle.getX(), (int)playerRectangle.getY(), (int)playerRectangle.getWidth(), (int)playerRectangle.getHeight());
		g.drawImage(baseLayer, 0, 0, window);
		player.Render(g);
		//g.drawImage(TestDriver.getPlayer().getCurrentSprite(), (int)TestDriver.getPlayer().getPlayer().getX()-(int)(3*scale), (int)TestDriver.getPlayer().getPlayer().getY()-(int)(2*scale), window);
		
		
		//TODO draw entities 
		g.drawImage(topLayer, 0, 0, window);
		stateEngine.getOverlay().Render(g);
		
		//g.drawImage(OverlayBase, 0, 0, window);
		//g.drawImage(Overlay, 0, 0, window);
		
		//if(pause){
		//	g.drawImage(menuImage, 0, 0, (int)(Math.floor(menuImage.getWidth()*scale)), (int)(Math.floor(menuImage.getHeight()*scale)), window);
		//}
		
		//ArrayList<Rectangle> rects = TextureLoader.getCollisions(name);
		//ArrayList<Rectangle> rects = TextureLoader.getExitRects(name);
		//for(int i = 0; i<Exits.size(); i++){
			//Rectangle r = Exits.get(i);
			
		//	g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
			//g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
			
	//	}
		
		//g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		//g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		
		//TODO need to add event listener for mouse input and keyboard input
		
	}

	
	public void gsEnter() {//TODO need to set a 
		// TODO Auto-generated method stub

		name = stateEngine.getCurrentLevel();
		layers = TextureLoader.getLevelMap(name);
		player = TestDriver.getPlayer();
		LevelCollisions = TextureLoader.getCollisions(name);
		Spawns = TextureLoader.getSpawns(name);
		Exits = TextureLoader.getExitRects(name);
		exitPaths = TextureLoader.getExitPaths(name);
		//tring previous = 
		baseLayer = layers.get(0);
		//transLayer = layers.get(1);
		topLayer = layers.get(1);
		if(!stateEngine.getPrevious().equals("Pause")){
			String SpawnKey = stateEngine.getPrevious();
			player.SPAWN((int)Spawns.get(SpawnKey).getX(),(int)Spawns.get(SpawnKey).getY());
			player.UpdateCollision(LevelCollisions);
		}
		
		System.out.println("world enter");
	}


	public String gsExit() {
		// TODO Auto-generated method stub
		System.out.println("world exit");
		return Integer.toString(ExitNum);
	}

	public String getPreviousName(){
		return name;
	}
	
	public void Update(Component window) {//TODO replace the speed values with actual values from the player, so that the values are defined by the player class
		// TODO Auto-generated method stub
		//Call all of the game logic code
		//InputManager inputs = InputManager.getInstance();
		//InputManager inputs  = (InputManager) (window.getKeyListeners()[0]);
		inputs  = (InputManager) (window.getKeyListeners()[0]);
		for(int i = 0; exited!=true&&i<Exits.size(); i++){
			Rectangle r = Exits.get(i);
			if(TestDriver.getPlayer().getPlayer().intersects(r)){//TODO will need to have stateEngine.change() called.  
				System.out.println(exitPaths.get("Exit"+i));
				ExitNum = i;
				stateEngine.change("World", exitPaths.get("Exit"+i));
				//exited = true;//Set here to prevent multiple exit detections
				
				break;
			}
		}
		
		
		if(inputs.isKeyDown(P_KEY)){
			//if(pause){
			//	pause = false;
		//	}
		//	else{
			//	pause = true;
		//	}
			stateEngine.change("Menu", name);
		}
		//if(inputs!=null&&inputs.isAnyKeyDown()){
			//System.out.println("SOME KEY IS DOWN");
			if(inputs.isKeyDown(KeyEvent.VK_W)) {
				if(inputs.isKeyDown(KeyEvent.VK_A)){
					player.setRectangle(-0.35, -0.35);
				}
				else if(inputs.isKeyDown(KeyEvent.VK_D)) {
					player.setRectangle(0.35, -0.35);
				}
				else{
					player.setRectangle(0, -0.5);
				}
				
				//++count;
				//System.out.println("Spacebar is down");
			}
			else if(inputs.isKeyDown(KeyEvent.VK_S)) {
				if(inputs.isKeyDown(KeyEvent.VK_A)){
					player.setRectangle(-0.35, 0.35);
				}
				else if(inputs.isKeyDown(KeyEvent.VK_D)) {
					player.setRectangle(0.35, 0.35);
				}
				else{
					player.setRectangle(0, 0.5);
				}
				
				//++count;
				//System.out.println("Spacebar is down");
			}
			else if(inputs.isKeyDown(KeyEvent.VK_A)) {
				player.setRectangle(-0.5 , 0);
				//++count;
				//System.out.println("Spacebar is down");
			}
			else if(inputs.isKeyDown(KeyEvent.VK_D)) {
				player.setRectangle(0.5, 0);
				//++count;
				//System.out.println("Spacebar is down");
			}
			else{
				player.setRectangle(0, 0);
			}
			/*
			if(inputs.isKeyDown(KeyEvent.VK_S)) {
				if(inputs.isKeyDown(KeyEvent.VK_A)){
					player.setRectangle(-0.25, 0.25);
				}
				else{
					player.setRectangle(0, 0.5);
				}
				
				//++count;
				//System.out.println("Spacebar is down");
			}
			*/
			//playerRectangle.setBounds((int)TestDriver.getPlayer().getPlayer().getX(), (int)TestDriver.getPlayer().getPlayer().getY(), (int)TestDriver.getPlayer().getPlayer().getWidth(),(int) TestDriver.getPlayer().getPlayer().getHeight());
			
			
		//}
		
	//	else{
			//System.out.println("INPUTS IS NULL");
		//}
		
		
		
		//if(((InputManager)window.getKeyListeners()[0]).isAnyKeyDown()){
		//	switch()
			
		//}
		
		//	System.out.println("world update");
			
	}
}
