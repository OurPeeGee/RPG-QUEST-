package GameState;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

//Erik Olsen, Marcus Moad, Nicholas Marfleet. 
//Parent interface for game state alteration. 

public interface interState
{
	public  void Render(JFrame window);
	public void gsEnter();
	public void gsExit();
	public void Update(JFrame window); 
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	
}