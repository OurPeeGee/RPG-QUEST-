
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

	    window.setBounds(10, 0, 2560, 1440);
	    window.getContentPane().add(new MyCanvas(20,16, null));
	    window.setVisible(true);
	    
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    window.getContentPane().removeAll();
	    window.getContentPane().add(new MyCanvas(1000, 600, null));

	    window.setBounds(0, 0, 1700, 1030);
	   // window.getContentPane().add(new MyCanvas());

	    window.setVisible(true);
	  }
}