package GameState;

import java.awt.event.*;
import javax.swing.*;

class UserInput extends KeyAdapter {
	
	GamSt stateEngine; 
	
	public void keyInput(KeyEvent e){
		this.stateEngine = stateEngine;
	}
	public void keyPressed(KeyEvent e){
		stateEngine.keyPressed(e);
	}
	public void keyReleased(KeyEvent e){
		stateEngine.keyReleased(e);
	}
}
