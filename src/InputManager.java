import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener{
	
	private int[] keys = new int[256];
	private static InputManager singleton = null;
	private boolean[] key_state_up = new boolean[256];
	private boolean[] key_state_down = new boolean[256];
	private boolean keyPressed = false;
	private boolean keyReleased = false;
	
	private String keyCache = "";
	private static InputManager instance = new InputManager();
	
	protected InputManager(){
		
	}
	
	public static InputManager getInstance(){
		//if(singleton == null){
		//	singleton = new InputManager();
		//}
		return singleton;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 //System.out.println("InputManager: A key has been pressed code=" + e.getKeyCode());
		 if( e.getKeyCode() >= 0 && e.getKeyCode() < 256 ) {
			// System.out.println("SOME KEY GOT PRESSED");
			 keys[e.getKeyCode()] = (int) System.currentTimeMillis();
			 key_state_down[e.getKeyCode()] = true;
			 key_state_up[e.getKeyCode()] = false;
			 keyPressed = true;
			 keyReleased = false;
		 }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		 //System.out.println("InputManager: A key has been released code=" + e.getKeyCode());
		if( e.getKeyCode() >= 0 && e.getKeyCode() < 256 ) {
			keys[e.getKeyCode()] = 0;
			key_state_up[e.getKeyCode()] = true;
			key_state_down[e.getKeyCode()] = false;
			keyPressed = false;
			keyReleased = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		keyCache += e.getKeyChar();
		
	}
	
	public boolean isKeyDown( int key ) {
		return key_state_down[key];
	}
	
	public boolean isKeyUp( int key ) {
		return key_state_up[key];
	}
	
	public boolean isAnyKeyDown() {
		return keyPressed;
	}
	
	public boolean isAnyKeyUp() {
		return keyReleased;
	}

	public void update() {
		//clear out the key up states
		key_state_up = new boolean[256];
		keyReleased = false;
		if( keyCache.length() > 1024 ) {
			keyCache = "";
		}
	}
	
}
