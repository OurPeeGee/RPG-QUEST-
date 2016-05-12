//Erik Olsen
//Game State Menu 
//5/7/2016

import java.awt.event.KeyListener;

import javax.swing.JFrame;

public  class GamStMenu extends GamSt implements interState {
			
	private float movementX;
	private float movementY=0;
	
	public void Render(JFrame window) {
		// TODO Auto-generated method stub
		//System.out.println("Menu render");
		//test
		//
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(window.getBounds());
	    window.getContentPane().add(new MyCanvas());
	//	window.getContentPane().add(new MyCanvas(20,16, null));
	    window.setVisible(true);
	    window.getContentPane().removeAll();
	    window.getContentPane().add(new MyCanvas());
	  //  window.getContentPane().add(new MyCanvas((int)movementX, (int)movementY, null));
	    window.setVisible(true);
		//
	    //test, to be implemented in own class
	}
	public void gsEnter() {
		// TODO Auto-generated method stub
		System.out.println("Menu enter");
	}
	public void gsExit() {
		// TODO Auto-generated method stub
		System.out.println("Menu exit");
	}
	public void Update(JFrame window){
		// TODO Auto-generated method stub
		//System.out.println("moved::"+movementX+" "+movementY);
		//System.out.print("u");
		//window.addKeyListener(new UserInput());
		//System.out.print(window.getTitle());
		
		
	}
}

