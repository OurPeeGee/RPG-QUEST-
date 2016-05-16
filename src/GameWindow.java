import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JPanel{
	/*
	private Component window;
	private GamStWorld state;
	
	public GameWindow() {

        //set the title and assign tracker object

       
		
        // This is a little cheat which means you only ever need one fish image
        // Basically it will flip the master along it's horizontal axies ;)
        BufferedImage GamePicture = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = GamePicture.createGraphics();
        //g2d.setTransform(AffineTransform.getScaleInstance(-1, 1));
        g2d.drawImage(GamePicture, 0, 0, this);
        g2d.dispose();

       // for (int index = 0; index < NUMNBER_OF_FISHIES; index++) {
         //   fishes.add(new Fish(masterFish, flippedMaster, this));
       // }

        //Thread background = new Thread(new Background());
        //background.setDaemon(true);
       // background.start();

    }
	
	public static void GWindowMain(){
		
		JFrame frame = new JFrame("SpriteSouls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new GameWindow());
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		
		
		
	}

	
	public void setGameState(GamSt NewState){
		state = (GamStWorld) NewState;
	}
	
	
	
	
	 @Override
	    protected void paintComponent(Graphics g) {

	        super.paintComponent(g);
	        
	        state.Render(g);
	        
	        
	        

	        //for (Fish fish : fishes) {
	        //    fish.drawFishImage(g);
	      //  }

	    }
	
	*/
	
}
