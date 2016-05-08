//Erik Olsen
import java.awt.event.KeyEvent;
import java.util.EventListener;
import java.util.Scanner;
import javax.swing.JFrame;



public  class GamStMenu extends GamSt implements interState,EventListener {
	Scanner scan = new Scanner(System.in);			
	private int movementX;
	private int movementY;
	
	public void Render(JFrame window) {
		// TODO Auto-generated method stub
		System.out.println("Menu render");
		
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(10, 0, 1024, 768);
		window.getContentPane().add(new MyCanvas(20,16, null));
	    window.setVisible(true);
	    window.getContentPane().removeAll();
	    window.getContentPane().add(new MyCanvas(movementX, movementY, null));
	    window.setVisible(true);
		    
	}

	
	public void gsEnter() {
		// TODO Auto-generated method stub
		System.out.println("Menu enter");
	}

	public void gsExit() {
		// TODO Auto-generated method stub
		System.out.println("Menu exit");
	}
	public void Update(float elapT){
		// TODO Auto-generated method stub
		System.out.println("moved::"+movementX+" "+movementY);
		
		keyPressed();
			
	   
	    
	}
	public void keyPressed() {

	    int key = 1;

	    if (key == KeyEvent.VK_LEFT) {
	         movementX=  -1;
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	    	movementX = 1;
	    }

	    if (key == KeyEvent.VK_UP) {
	    	movementY = -1;
	    }

	    if (key == KeyEvent.VK_DOWN) {
	    	movementY = 1;
	    }
	}

	

}

