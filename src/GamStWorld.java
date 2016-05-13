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

public class GamStWorld extends GamSt implements interState {
	
	private ArrayList<String> levels = TextureLoader.getLevelList();
	private String name = levels.get(0);
	private ArrayList<BufferedImage> layers = TextureLoader.getLevelMap(name);
	private Component window;
	private BufferedImage baseLayer;
	private BufferedImage transLayer;
	private BufferedImage entityLayer;
	private BufferedImage topLayer;
	
	public GamStWorld(Component Gwindow){
		
		
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
		System.out.println("world render");
		
		g.drawImage(baseLayer, 0, 0, window);
		
		//TODO draw entities 
		g.drawImage(topLayer, 0, 0, window);
		Rectangle2D r = TextureLoader.getCollisions(name).get(0);
		g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		
		
		//TODO need to add event listener for mouse input and keyboard input
		
	}

	
	public void gsEnter() {
		// TODO Auto-generated method stub
		System.out.println("world enter");
	}


	public void gsExit() {
		// TODO Auto-generated method stub
		System.out.println("world exit");
	}

	
	public void Update(Component window) {
		// TODO Auto-generated method stub
		//Call all of the game logic code
			System.out.println("world update");
			
	}
}
