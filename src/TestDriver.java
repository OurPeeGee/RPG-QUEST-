
public class TestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		stateEngine cQuest = new stateEngine(); 
		 
		// A state for each game mode
		cQuest.Add("Menu",   new GamStMenu());
		cQuest.Add("Map",   new GamStMap(cQuest));
		cQuest.Add("World",   new GamStWorld(cQuest));
		cQuest.Add("Com",     new GamStCombat());
		cQuest.Add("Inv", new GamStInventory(cQuest));
		
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
