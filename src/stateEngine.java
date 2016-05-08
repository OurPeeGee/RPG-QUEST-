
import java.util.TreeMap;

import javax.swing.JFrame;

public class stateEngine {
	
	static TreeMap<String, interState> toGamSt = new TreeMap<String, interState>();
	
	static interState GamstCurrent = toGamSt.get("Menu"); 
	
	public void update(float elapT){
		GamstCurrent.Update(elapT); 
	}
	public void Render(JFrame window){
		GamstCurrent.Render(window);
	}
	public void change(String name){
		GamstCurrent.gsExit();
		GamstCurrent = toGamSt.get(name);
		GamstCurrent.gsEnter();
	}
	
	public void Add(String name, interState user) {
		// TODO Auto-generated method stub
		toGamSt.put(name,user);
		GamstCurrent = toGamSt.get(name);
	}
	
	
	
}

