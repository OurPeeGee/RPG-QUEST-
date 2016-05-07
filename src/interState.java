//Erik Olsen, Marcus Moad, Nicholas Marfleet. 
//Parent interface for game state alteration. 
public interface interState
{
	
	public void tick(float elapT);
	public  void Render();
	public void gsEnter();
	public void gsExit(); 
	
}