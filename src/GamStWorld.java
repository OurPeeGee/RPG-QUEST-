import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
	
	private ArrayList<String> levels = TextureLoader.getLevelList();
	private String name = levels.get(0);
	private ArrayList<BufferedImage> layers = TextureLoader.getLevelMap(name);
	private Component window;
	private BufferedImage baseLayer;
	private BufferedImage transLayer;
	private BufferedImage entityLayer;
	private BufferedImage topLayer;
	int W_KEY = KeyEvent.VK_W;
	int A_KEY = KeyEvent.VK_A;
	int S_KEY = KeyEvent.VK_S;
	int D_KEY = KeyEvent.VK_D;
	private PlayerTestEntity player = TestDriver.getPlayer();
	private ArrayList<Rectangle> LevelCollisions = TextureLoader.getCollisions(name);
	private ArrayList<Rectangle> Spawns = TextureLoader.getSpawns(name);//This list contains the spawns for the character.  multiple entrances to rooms possible
	
	
	public GamStWorld(Component Gwindow){
		
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
		
		g.drawImage(baseLayer, 0, 0, window);
		
		g.fillRect((int)TestDriver.getPlayer().getPlayer().getX(), (int)TestDriver.getPlayer().getPlayer().getY(), (int)TestDriver.getPlayer().getPlayer().getWidth(),(int) TestDriver.getPlayer().getPlayer().getHeight());
		
		//TODO draw entities 
		g.drawImage(topLayer, 0, 0, window);
		
		ArrayList<Rectangle> rects = TextureLoader.getCollisions(name);
		for(int i = 0; i<rects.size(); i++){
			Rectangle r = rects.get(i);
			
			g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
			g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
			
		}
		
		//g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		//g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		
		//TODO need to add event listener for mouse input and keyboard input
		
	}

	
	public void gsEnter() {
		// TODO Auto-generated method stub
		player.SPAWN((int)Spawns.get(0).getX(),(int)Spawns.get(0).getY());
		player.UpdateCollision(LevelCollisions);
		System.out.println("world enter");
	}


	public void gsExit() {
		// TODO Auto-generated method stub
		System.out.println("world exit");
	}

	
	public void Update(Component window) {//TODO replace the speed values with actual values from the player, so that the values are defined by the player class
		// TODO Auto-generated method stub
		//Call all of the game logic code
		//InputManager inputs = InputManager.getInstance();
		InputManager inputs  = (InputManager) (window.getKeyListeners()[0]);
		//if(inputs!=null&&inputs.isAnyKeyDown()){
			//System.out.println("SOME KEY IS DOWN");
			if(inputs.isKeyDown(KeyEvent.VK_W)) {
				player.setRectangle(0, -0.5);
				//++count;
				//System.out.println("Spacebar is down");
			}
			if(inputs.isKeyDown(KeyEvent.VK_A)) {
				player.setRectangle(-0.5 , 0);
				//++count;
				//System.out.println("Spacebar is down");
			}
			if(inputs.isKeyDown(KeyEvent.VK_D)) {
				player.setRectangle(0.5, 0);
				//++count;
				//System.out.println("Spacebar is down");
			}
			if(inputs.isKeyDown(KeyEvent.VK_S)) {
				
				player.setRectangle(0, 0.5);
				//++count;
				//System.out.println("Spacebar is down");
			}
			//input.update();
			
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
