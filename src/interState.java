import java.awt.Component;
import java.awt.Graphics;


//Erik Olsen, Marcus Moad, Nicholas Marfleet. 
//Parent interface for game state alteration. 
public interface interState
{
	public  void Render(Graphics g);
	public void gsEnter();
	public String gsExit();
	public void Update(Component window);
	public String getPreviousName(); 
	
}