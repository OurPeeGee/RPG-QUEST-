import java.awt.image.BufferedImage;

import java.awt.Component;
import java.awt.Graphics;



public class Overlay {
	
	
	
	private static Component window;
	private static BufferedImage OverlayBase;
	private static BufferedImage OverlayTop;
	
	
	public void Render(Graphics g){
		
		g.drawImage(OverlayBase, 0, 0, window);
		g.drawImage(OverlayTop, 0, 0, window);
		
		
		
	}
	
	public Overlay(Component Gwindow){
		
		window = Gwindow;
		OverlayBase = TextureLoader.getLevelMap("Overlay").get(0);
		OverlayTop = TextureLoader.getLevelMap("Overlay").get(1);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
