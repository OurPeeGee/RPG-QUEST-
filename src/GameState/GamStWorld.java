package GameState;

import java.awt.event.KeyEvent;

public class GamStWorld extends GamSt implements interState {
	
	public void Render() {
		// TODO Auto-generated method stub
		System.out.println("world render");
		
	}

	
	public void gsEnter() {
		// TODO Auto-generated method stub
		System.out.println("world enter");
	}


	public void gsExit() {
		// TODO Auto-generated method stub
		System.out.println("world exit");
	}

	
	public void Update(float elapT) {
		// TODO Auto-generated method stub
			System.out.println("world update");
			
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
	}
}
