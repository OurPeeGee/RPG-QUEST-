import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

class MyCanvas extends JComponent {

  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

   // Image img1 = Toolkit.getDefaultToolkit().getImage("resources\\forest_town.png");
    Image img1 = Toolkit.getDefaultToolkit().getImage("resources\\forest_town.png");
    BufferedImage img2 = null;
	try {
		img2 = ImageIO.read(new File("resources\\town_forest_tiles.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    g2.drawImage(img1, 0, 0, this);
    g2.drawImage(img2, 200, 200, this);
    g2.finalize();
  }
}
