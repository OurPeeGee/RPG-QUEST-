

import javax.swing.JFrame;

public  class GamStMenu extends GamSt implements interState {
					
	private int movement;

	public void Render(JFrame window) {
		// TODO Auto-generated method stub
		System.out.println("Menu render");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    window.getContentPane().removeAll();
	    window.getContentPane().add(new MyCanvas(movement, 600, null));
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

	
	public void Update(float elapT) {
		// TODO Auto-generated method stub
		
	   movement=(int) elapT;
	   System.out.println("moved::"+movement);
	    
	}


	

}

