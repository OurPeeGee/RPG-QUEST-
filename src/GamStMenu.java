//Erik Olsen
//Game State Menu 
//5/7/2016

import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public  class GamStMenu extends GamSt implements interState {
			
	private float movementX;
	private float movementY=0;
	
	public void Render(Graphics g) {//TODO LITERALLY WORTHLESS RIGHTNOW
		// TODO Auto-generated method stub
		//System.out.println("Menu render");
		//test
		//
		System.out.println("Menu render");
		
		//g.drawImage(baseLayer, 0, 0, window);
		
		//TODO draw entities 
		//g.drawImage(topLayer, 0, 0, window);
		//Rectangle2D r = TextureLoader.getCollisions(name).get(0);
		//g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		//g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		/*
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(window.getBounds());
	    window.getContentPane().add(new MyCanvas());
	//	window.getContentPane().add(new MyCanvas(20,16, null));
	    window.setVisible(true);
	    window.getContentPane().removeAll();
	    window.getContentPane().add(new MyCanvas());
	  //  window.getContentPane().add(new MyCanvas((int)movementX, (int)movementY, null));
	    window.setVisible(true);
	    */
		//
		 
	    //test, to be implemented in own class
	}
	public void gsEnter() {
		// TODO Auto-generated method stub
		System.out.println("Menu enter");
	}
	public String gsExit() {
		// TODO Auto-generated method stub
		System.out.println("Menu exit");
		return "";
	}
	public void Update(Component window){
		// TODO Auto-generated method stub
		//System.out.println("moved::"+movementX+" "+movementY);
		//System.out.print("u");
		//window.addKeyListener(new UserInput());
		//System.out.print(window.getTitle());
		
		
	}
}

