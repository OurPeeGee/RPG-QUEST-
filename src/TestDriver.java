
public class TestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
