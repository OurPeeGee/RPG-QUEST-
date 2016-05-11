package GameState;
//Erik Olsen
//Game State Menu 
//5/7/2016

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public  class GamStMenu extends JPanel implements interState,KeyListener,ActionListener {
			
	private float movementX;
	private float movementY=0;
	
	public GamStMenu()
	{
		addKeyListener(this);
		setFocusable(true);
		
	}
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
	    window.getContentPane().add(new MyCanvas((int)movementX, (int)movementY, null));
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
		
				
			
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		UserInput force = new UserInput();
		
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

