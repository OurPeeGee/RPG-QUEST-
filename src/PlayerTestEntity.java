import java.awt.Rectangle;
import java.util.ArrayList;

public class PlayerTestEntity {
	
	private Rectangle sprite;
	private ArrayList<Rectangle> Collision;
	private double scale = TestDriver.getScale();
	
	public PlayerTestEntity(){
		sprite = new Rectangle(0,0,(int)Math.floor(10*scale),(int)Math.floor(10*scale));
		
	}
	
	public void UpdateCollision(ArrayList<Rectangle> NewCollisions){
		Collision = NewCollisions;
		
	}
	
	public void SPAWN(int x, int y){
		sprite.setLocation(x, y);
	}
	
	public Rectangle getPlayer(){
		return sprite;
	}
	
	public void setRectangle(double X, double Y){
		int x = 0;
		int y = 0;
		if(X>0){
			x = (int) Math.ceil(X*scale);
		}
		else{
			x = (int) Math.floor(X*scale);
		}
		
		if(Y>0){
			y = (int) Math.ceil(Y*scale);
		}
		else{
			y = (int) Math.floor(Y*scale);
		}
		//int x = (int) Math.floor(X*scale);
		//int y = (int) Math.floor(Y*scale);
		sprite.setLocation((int)sprite.getX()+x, (int)sprite.getY()+y);
		for(Rectangle box: Collision){
			if(sprite.intersects(box)){
				sprite.setLocation((int)sprite.getX()-x, (int)sprite.getY()-y);
				break;
			}
		}
		
	}

}
