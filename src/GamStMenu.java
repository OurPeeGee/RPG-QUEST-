//Erik Olsen
//Game State Menu 
//5/7/2016

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

public  class GamStMenu extends GamSt implements interState {
			
	private Component window;
	private float movementX;
	private float movementY=0;
	private BufferedImage menuImage = TextureLoader.getMenuPause();
	private double scale = TestDriver.getScale();
	//private BufferedImage lastScreen = stateEngine.getLastScreen();
	private static String PreviousGameStateName = "Default";


	
	private ArrayList<String> levels;// = TextureLoader.getLevelList();
	//private String name;// = levels.get(0);
	private ArrayList<BufferedImage> layers;// = TextureLoader.getLevelMap(name);
	//private Component window;
	private BufferedImage baseLayer;
	private BufferedImage transLayer;
	private BufferedImage entityLayer;
	private BufferedImage topLayer;
	//int W_KEY = KeyEvent.VK_W;
	//int A_KEY = KeyEvent.VK_A;
	//int S_KEY = KeyEvent.VK_S;
	//int D_KEY = KeyEvent.VK_D;
	int ESC_KEY = KeyEvent.VK_ESCAPE;
	private PlayerTestEntity player = TestDriver.getPlayer();
	//private ArrayList<Rectangle> LevelCollisions;// = TextureLoader.getCollisions(name);
	//private HashMap<String, Rectangle> Spawns;// = TextureLoader.getSpawns(name);//This list contains the spawns for the character.  multiple entrances to rooms possible
	//private ArrayList<Rectangle> Exits;// = TextureLoader.getExitRects(name);
	//private HashMap<String,String> exitPaths;// = TextureLoader.getExitPaths(name);
	//private boolean exited = false;
	//private boolean pause = false;
	//private int ExitNum;
	private static InputManager inputs;
	//private static int count = 0;
	//private double scale = TestDriver.getScale();
	//private BufferedImage menuImage = TextureLoader.getMenuPause();
	
	
	
	public GamStMenu(Component GWindow){
		
		
		//try {
		//	menuImage = ImageIO.read(new File("Menu.PNG"));
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		this.window = GWindow;
	}
	
	public String getPreviousName(){
		return "Pause";
	}
	
	public void Render(Graphics g) {//TODO LITERALLY WORTHLESS RIGHTNOW
		// TODO Auto-generated method stub
		//System.out.println("Menu render");
		//test
		//
		//System.out.println("Menu render");
		
		g.drawImage(baseLayer, 0, 0, window);
		player.Render(g);
		//g.drawImage(TestDriver.getPlayer().getCurrentSprite(), (int)TestDriver.getPlayer().getPlayer().getX()-(int)(3*scale), (int)TestDriver.getPlayer().getPlayer().getY()-(int)(2*scale), window);
		g.drawImage(topLayer, 0, 0, window);
		
		
		
		
		//g.drawImage(menuImage, 0, 0, window);
		//g.drawImage(lastScreen)
		g.drawImage(menuImage, 0, 0, (int)(Math.floor(menuImage.getWidth()*scale)), (int)(Math.floor(menuImage.getHeight()*scale)), window);
		//g.drawImage(menuImage)
		//g.drawImage(baseLayer, 0, 0, window);
		
		//TODO draw entities 
		//g.drawImage(topLayer, 0, 0, window);
		//Rectangle2D r = TextureLoader.getCollisions(name).get(0);
		//g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		//g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		/*
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(window.getBounds());
	    window.getContentPane().add(new MyCanvas());
	//	window.getContentPane().add(new MyCanvas(20,16, null));
	    window.setVisible(true);
	    window.getContentPane().removeAll();
	    window.getContentPane().add(new MyCanvas());
	  //  window.getContentPane().add(new MyCanvas((int)movementX, (int)movementY, null));
	    window.setVisible(true);
	    */
		//
		 
	    //test, to be implemented in own class
	}
	public void gsEnter() {
		
		inputs  = (InputManager) (window.getKeyListeners()[0]);
		//name = stateEngine.getCurrentLevel();
		
		
		
		
		
		PreviousGameStateName = stateEngine.getPrevious();
		if(!PreviousGameStateName.equals("Default")){
			layers = TextureLoader.getLevelMap(PreviousGameStateName);
			//player = TestDriver.getPlayer();
			baseLayer = layers.get(0);
			topLayer = layers.get(1);
		}
		
		
		//menuImage = TextureLoader.getMenuPause();
		/*
		try {
			menuImage = ImageIO.read(new File("Menu.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		// TODO Auto-generated method stub
		System.out.println("Menu enter");
	}
	public String gsExit() {
		// TODO Auto-generated method stub
		System.out.println("Menu exit");
		return "";
	}
	public void Update(Component window){
		
		//count++;
		if(inputs.isKeyDown(ESC_KEY)){
			//if(pause){
			//	pause = false;
		//	}
		//	else{
			//	pause = true;
		//	}
			//count = count%count;
			stateEngine.change("World", PreviousGameStateName);
		}
		
		//if(count>30000){
		//	count = count%count;
		//}
		
		// TODO Auto-generated method stub
		//System.out.println("moved::"+movementX+" "+movementY);
		//System.out.print("u");
		//window.addKeyListener(new UserInput());
		//System.out.print(window.getTitle());
		
		
	}
}

