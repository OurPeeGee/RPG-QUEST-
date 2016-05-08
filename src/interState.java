//Erik Olsen, Marcus Moad, Nicholas Marfleet. 
//Parent interface for game state alteration. 
public interface interState
{
	public  void Render();
	public void gsEnter();
	public void gsExit();
	public void Update(float elapT); 
	
}