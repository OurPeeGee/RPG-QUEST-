
public class TestDriver {
	
	static stateEngine cQuest = new stateEngine(); 
	private static long lastTime;
	private static GamSt GamStMenu;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		lastTime=0;
		// A state for each game mode
		cQuest.Add("Menu", new GamStMenu());
		cQuest.Add("World", new GamStWorld());
		
		
		cQuest.change("Menu");
		
		System.out.println("welcome to the smack down brother");
		long startTime = System.currentTimeMillis();
		loop42(startTime);
		
	}
	public static void loop42(long startTime)
	{
		while(true)
		{
			cQuest.update(GetElapsedFrameTime());
			while(true)
			{
				
				cQuest.Render();
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