///Erik Olsen
///Game state engine class, allows game states to be added, changed(switch between operating state) updated and rendered. 
/// 5/8/2016

import java.awt.Component;
import java.util.TreeMap;
import java.awt.Graphics;
import javax.swing.JFrame;

public class stateEngine {
	
	static TreeMap<String, interState> toGamSt = new TreeMap<String, interState>();//Map paths (name of Game State as a string TO, the state class(as  
	
	static interState GamstCurrent = toGamSt.get("Menu"); 
	
	public void Update(Component window){//update cycle 
		GamstCurrent.Update(window); 
		
	}
	public void Render(Graphics g){
		GamstCurrent.Render(g);
	}
	public void change(String name){//changes active gamestate
		GamstCurrent.gsExit();
		GamstCurrent = toGamSt.get(name);
		GamstCurrent.gsEnter();
	}
	
	public void Add(String name, interState user) {//adds new gamestate if it fits the interStates peramiters 
		// TODO Auto-generated method stub
		toGamSt.put(name,user);
		GamstCurrent = toGamSt.get(name);
	}
	
	
	
	
}

