
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;



public class Graphics2DDrawImage {
	public static void main(String[] a) {
	    JFrame window = new JFrame();
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< HEAD
	    window.setBounds(0, 0, 1030, 1030);
=======
	    window.setBounds(10, 0, 1024, 768);
>>>>>>> refs/remotes/origin/master
	    window.getContentPane().add(new MyCanvas());
	    window.setVisible(true);
	  }
}