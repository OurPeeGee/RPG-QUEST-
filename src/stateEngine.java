
public class stateEngine {

	private static interState GamstTemplate;
	static interState GamstCurrent = GamstTemplate; 
	public static void update(float elapT){
		GamstCurrent.Update(elapT); 
	}
	public static void Render(){
		
	}
	public void change(String stateName){
		
	}
	public void Add(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

