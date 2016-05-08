import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;

class MyCanvas extends JComponent {

  public void paint(Graphics g,Image part, int posX, int posY) {
    Graphics2D g2 = (Graphics2D) g;

    Image img1 = Toolkit.getDefaultToolkit().getImage("resources\\forest_town.png");
    Image img2 = Toolkit.getDefaultToolkit().getImage("resources\\town_forest_tiles.png");
    g2.drawImage(img1, 0, 0, this);
    g2.drawImage(img2, posX, posY, this);
    g2.finalize();
  }
}
