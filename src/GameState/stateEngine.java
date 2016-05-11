package GameState;
///Erik Olsen
///Game state engine class, allows game states to be added, changed(switch between operating state) updated and rendered. 
/// 5/8/2016

import java.util.TreeMap;

import javax.swing.JFrame;

public class stateEngine {
	
	static TreeMap<String, interState> toGamSt = new TreeMap<String, interState>();//Map paths (name of Game State as a string TO, the state class(as  
	
	static interState GamstCurrent = toGamSt.get("Menu"); 
	
	public void Update(JFrame window){
		GamstCurrent.Update(window); 
		
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

