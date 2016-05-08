import java.io.IOException;


public class TestDriver {
	
	static stateEngine cQuest = new stateEngine(); 
	private static long lastTime;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		lastTime=0;
		// A state for each game mode
		cQuest.Add("Menu");
		
		cQuest.Add("Map" );
		
		cQuest.Add("World");
		
		cQuest.Add("Com");
		
		cQuest.Add("Inv");
		
		System.out.println("welcome to the smack down brother");
		long startTime = System.currentTimeMillis();
		loop42(startTime);
		
	}
	public static void loop42(long startTime)
	{
		while(true)
		{
			Update();
			while(true)
			{
				GetElapsedFrameTime();
			}
			
		}
		
	}
	public static void Update()
	{
		cQuest.update(lastTime);
	    
	}
	private static long GetElapsedFrameTime() {
		return lastTime;
		// TODO Auto-generated method stub
		
		
	}
   
}