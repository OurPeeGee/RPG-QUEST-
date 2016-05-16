///Erik Olsen
///Game state engine class, allows game states to be added, changed(switch between operating state) updated and rendered. 
/// 5/8/2016

import java.awt.Component;
import java.util.HashMap;
import java.util.TreeMap;
import java.awt.Graphics;
import javax.swing.JFrame;

public class stateEngine {
	//Why not make this a HashMap?  
	private static HashMap<String, interState> toGamSt = new HashMap<String, interState>();//Map paths (name of Game State as a string TO, the state class(as  
	//private static HashMap<String, String> toLevel = new HashMap<String, String>();
	private static String toLevel;
	private static interState GamstCurrent = toGamSt.get("Menu"); 
	private static String PreviousGameStateName = "Default";
	private static Overlay overlay;
	
	public static void Update(Component window){//update cycle 
		GamstCurrent.Update(window); 
		
	}
	public static void Render(Graphics g){
		GamstCurrent.Render(g);
	}
	
	public static void change(String name){//changes active gamestate  
		GamstCurrent.gsExit();
		GamstCurrent = toGamSt.get(name);
		GamstCurrent.gsEnter();
	}
	
	public static void setOverlay(Overlay over){
		overlay = over;
	}
	
	public static Overlay getOverlay(){
		return overlay;
	}
	
	public static void change(String name, String level){//changes active gamestate  
		
		PreviousGameStateName = (GamstCurrent).getPreviousName();
		GamstCurrent.gsExit();
		toLevel = level;
		GamstCurrent = toGamSt.get(name);
		GamstCurrent.gsEnter();
	}
	
	public static String getPrevious(){
		return PreviousGameStateName;
	}
	
	public static void Add(String name, interState user) {//adds new gamestate if it fits the interStates peramiters 
		// TODO Auto-generated method stub
		toLevel = TextureLoader.getLevelList().get(0);
		toGamSt.put(name,user);
		GamstCurrent = toGamSt.get(name);
	}
	
	public static String getCurrentLevel(){
		return toLevel;
	}
	
	
	
	
}

