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
