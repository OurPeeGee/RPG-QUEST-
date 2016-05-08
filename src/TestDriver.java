
public class TestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		stateEngine cQuest = new stateEngine(); 
		 
		// A state for each game mode
		cQuest.Add("Menu"   );
		
		cQuest.Add("Map"   );
		
		cQuest.Add("World");
		
		cQuest.Add("Com");
		
		cQuest.Add("Inv");
		
		System.out.println("welcome to the smack down brother");
		Update();
	}
	public static void Update()
	{
	    float elapT = GetElapsedFrameTime();
	    stateEngine.update(elapT);
	    stateEngine.render();
	}
	private static float GetElapsedFrameTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
