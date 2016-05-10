//Erik Olsen
//Game State Menu 
//5/7/2016

import javax.swing.JFrame;

public  class GamStMenu extends GamSt implements interState {
			
	private int movementX;
	private int movementY;
	
	public void Render(JFrame window) {
		// TODO Auto-generated method stub
		//System.out.println("Menu render");
		//test
		//
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(window.getBounds());
	    
		window.getContentPane().add(new MyCanvas(20,16, null));
	    window.setVisible(true);
	    window.getContentPane().removeAll();
	    window.getContentPane().add(new MyCanvas(movementX, movementY, null));
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
	public void Update(float elapT){
		// TODO Auto-generated method stub
		//System.out.println("moved::"+movementX+" "+movementY);
		
		movementX+=1*elapT/10;
		movementY+=0;
	}
}

	


	