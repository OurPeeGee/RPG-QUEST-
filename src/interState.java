import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JFrame;

//Erik Olsen, Marcus Moad, Nicholas Marfleet. 
//Parent interface for game state alteration. 
public interface interState
{
	public  void Render(Graphics g);
	public void gsEnter();
	public void gsExit();
	public void Update(Component window); 
	
}