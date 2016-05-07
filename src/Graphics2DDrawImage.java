
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
	    window.setBounds(10, 0, 1024, 768);
	    window.getContentPane().add(new MyCanvas());
	    window.setVisible(true);
	  }
}