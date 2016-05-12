import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

class MyCanvas extends JComponent {

	//private int x;
	//private int y;
	//private Image[] pull = new Image[2];
 // public MyCanvas(int i, int j,Image[] assets) {
	//  x=i;
	//  y=j;
	//  pull=assets; 
	//}
//public void paint(Graphics g) {
  //  Graphics2D g2 = (Graphics2D) g;

 //   Image img1 = Toolkit.getDefaultToolkit().getImage("resources\\forest_town.png");
 //   Image img2 = Toolkit.getDefaultToolkit().getImage("resources\\town_forest_tiles.png");
  //  g2.drawImage(img1, 0, 0, this);
  //  g2.drawImage(img2,x, y, this);
  // g2.finalize();


  public void paint(Graphics g) {
    //Graphics2D gr = (Graphics2D) g;
    
    try{//This works and puts into a BufferedImage array each tile of a tilemap when the tilewidth/height and width and height are specified.
		//File file = new File("resources\\tilemaps\\series1\\sewer_1.png");
		//FileInputStream fis = new FileInputStream(file);
	//BufferedImage[] tileset = new BufferedImage[352];
		/*
		BufferedImage[] tileset = TextureLoader.getTileMap("sewer_1");
	//BufferedImage tileBase = ImageIO.read(fis);//This contains the tileSet image and is accessed through the key which is it's name.
	//BufferedImage tileBase = TextureLoader.getTileMap()
	for(int a = 0; a< 22; a++){
		for(int q = 0; q<16; q++){
			//tileset[q+a*16] = tileBase.getSubimage(q*16, a*16, 16, 16);
			
			gr.drawImage(tileset[q+a*16], q*16, a*16, this);
			//Graphics2D gr = tileset[a].createGraphics();  
			//gr.drawImage(tileBase, 0, 0, 16, 16, 16 * a%32, 16 * a, 16 * a%32 + 16, 16 * a + 16, this);  
			
		}
		
		
		
	}
	*/
		ArrayList<String> levels = TextureLoader.getLevelList();
		String name;
		//System.out.println("size = " + levels.size());
		//int count = 0;
		for(int i = 0; i<levels.size(); i++){
			//System.out.println("MADE IT TO G-LOOP");
			name = levels.get(i);
			ArrayList<BufferedImage> layers = TextureLoader.getLevelMap(name);
			for(int a = 0; a< layers.size(); a++){
				BufferedImage levelMap = layers.get(a);
				g.drawImage(levelMap,i*512,0, this);
			}
			
		//count++;	
		}
		//System.out.println("Count: " + count);
		//BufferedImage levelMap = (BufferedImage)TextureLoader.getLevelMap("resources\\tilemaps\\series1\\testsewer\\SewerTest1.xml").get(0);
		
		//gr.drawImage(levelMap,0,0, this);
		
		
	//g.finalize();
	//Graphics2DDrawImage.main(null);
	}catch(Exception e){
		e.printStackTrace();
	}
	

    //Image img1 = Toolkit.getDefaultToolkit().getImage("resources\\forest_town.png");
    //Image img1 = Toolkit.getDefaultToolkit().getImage("resources\\forest_town.png");
  //  BufferedImage img2 = null;
	//try {
	//Image	img2 = Toolkit.getDefaultToolkit().getImage("resources\\town_forest_tiles.png");
	//} catch (IOException e) {
	//	// TODO Auto-generated catch block
	//	e.printStackTrace();
	//}
   // g2.drawImage(img1, 0, 0, this);
   // g2.drawImage(img2, 200, 200, this);
    //g2.finalize();

  }
}

