/**Erik Olsen	
 * 9:32:37 PM
 *RPG-QUEST-
 */
package GameState;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class menuInputMatrix {
	
	ArrayList<Integer> feedBack = new ArrayList<Integer>();

	public ArrayList ValueReturn(KeyEvent e){
		int posX=0;
		int posY=0;
		int Enter=0;
		int Space=0;
		
	    int key = e.getKeyCode();
		if(key == KeyEvent.VK_KP_UP||key== KeyEvent.VK_W)
			 posY +=1; 
		if(key == KeyEvent.VK_KP_DOWN||key== KeyEvent.VK_S)
			posY -=1; 
		
		if(key == KeyEvent.VK_KP_LEFT||key== KeyEvent.VK_A)
			posX -=1; 
		if(key == KeyEvent.VK_KP_RIGHT||key== KeyEvent.VK_D)
			posX -=1; 
		
		feedBack.add(posX);
		feedBack.add(posY);
		return feedBack;
		
	}

}
