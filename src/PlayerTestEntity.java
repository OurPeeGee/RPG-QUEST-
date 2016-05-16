import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Component;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class PlayerTestEntity {
	
	private Rectangle sprite;
	private Component window;
	private ArrayList<Rectangle> Collision;
	private double scale = TestDriver.getScale();
	private HashMap<String, ArrayList<BufferedImage>> sprites = new HashMap<String, ArrayList<BufferedImage>>();
	private ArrayList<BufferedImage> walkUp = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> walkLeft = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> walkDown = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> walkRight = new ArrayList<BufferedImage>();
	private BufferedImage spriteSheet; 
	private BufferedImage scaledSpriteSheet;
	private BufferedImage currentSprite; 
	private String direction = "S";
	private String PreviousDirection = "S";
	int count = 0;
	int num = 0;
	int AnimationSpeed = 30;
	
	public void Render(Graphics g){
		
		
		g.drawImage(currentSprite, (int)sprite.getX()-(int)(3*scale), (int)sprite.getY()-(int)(2*scale), window);
		
	}
	
	
	
	
	
	
	public PlayerTestEntity(Component Gwindow){
		window = Gwindow;
		
		spriteSheet = TextureLoader.getSpriteSheet();
		scaledSpriteSheet = new BufferedImage((int)Math.floor(spriteSheet.getWidth()*scale), (int)Math.floor(spriteSheet.getHeight()*scale), BufferedImage.TYPE_INT_ARGB);
		Graphics s = scaledSpriteSheet.createGraphics();
		s.drawImage(spriteSheet, 0, 0, (int)Math.floor(spriteSheet.getWidth()*scale), (int)Math.floor(spriteSheet.getHeight()*scale), null);
		//=  spriteSheet.getScaledInstance((int)Math.floor(16*scale), (int)Math.floor(16*scale), 0);
		for(int y = 0; y<scaledSpriteSheet.getWidth()/Math.floor(16*scale);y++){
			walkDown.add(scaledSpriteSheet.getSubimage((int)Math.floor(y*16*scale),0,(int)Math.floor(16*scale),(int)Math.floor(16*scale)));
		}
		sprites.put("S", walkDown);
		for(int y = 0; y<scaledSpriteSheet.getWidth()/Math.floor(16*scale);y++){
			walkLeft.add(scaledSpriteSheet.getSubimage((int)Math.floor(y*16*scale),(int)Math.floor(16*scale),(int)Math.floor(16*scale),(int)Math.floor(16*scale)));
		}
		sprites.put("W", walkLeft);
		for(int y = 0; y<scaledSpriteSheet.getWidth()/Math.floor(16*scale);y++){
			walkUp.add(scaledSpriteSheet.getSubimage((int)Math.floor(y*16*scale),(int)Math.floor(32*scale),(int)Math.floor(16*scale),(int)Math.floor(16*scale)));
		}
		sprites.put("N", walkUp);
		for(int y = 0; y<scaledSpriteSheet.getWidth()/Math.floor(16*scale);y++){
			walkRight.add(scaledSpriteSheet.getSubimage((int)Math.floor(y*16*scale),(int)Math.floor(48*scale),(int)Math.floor(16*scale),(int)Math.floor(16*scale)));
		}
		sprites.put("E", walkRight);
		currentSprite = walkDown.get(0);
		sprite = new Rectangle(0,0,(int)Math.floor(9*scale),(int)Math.floor(14*scale));
		
	}
	
	public BufferedImage getCurrentSprite(){
		return currentSprite;
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
	
	public void animateWalk(int num){
		try{
		if(num>=AnimationSpeed){
			//System.out.println("MADE IT");
			currentSprite = sprites.get(direction).get(count);
			count++;
			if(count>=sprites.get(direction).size()-1){
				count = 0;
			}
		}
		if(direction.equals(PreviousDirection)){
			currentSprite = sprites.get(direction).get(count);
		}
		
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	}
	
	public void setRectangle(double X, double Y){
		int x = 0;
		int y = 0;
		if(X>0){
			x = (int) Math.ceil(X*scale);
		}
		else if(X<0){
			x = (int) Math.floor(X*scale);
		}
		else{
			x=0;
		}
		
		if(Y>0){
			y = (int) Math.ceil(Y*scale);
		}
		else if(Y<0){
			y = (int) Math.floor(Y*scale);
		}
		else{
			y=0;
		}
		//int x = (int) Math.floor(X*scale);
		//int y = (int) Math.floor(Y*scale);
		num++;
		if(X+x>x){
			direction = "E";
			//currentSprite = walkRight.get(0);
			animateWalk(num);
		}
		else if(X+x<x){
			direction = "W";
			//currentSprite = walkLeft.get(0);
			animateWalk(num);
		}
		else if(Y+y>y){
			direction = "S";
			//currentSprite = walkDown.get(0);
			animateWalk(num);
		}
		else if(Y+y<y){
			direction = "N";
			//currentSprite = walkUp.get(0);
			animateWalk(num);
		}
		else{
			currentSprite = sprites.get(direction).get(0);
			count = 0;
		}
		
		if(num>AnimationSpeed){
			num = num%num;
		}
		PreviousDirection = direction;
		
		sprite.setLocation((int)sprite.getX()+x, (int)sprite.getY());
		for(Rectangle box: Collision){
			//Detection for X collision
			if(sprite.intersects(box)){
				sprite.setLocation((int)sprite.getX()-x, (int)sprite.getY());
				break;
			}
		}
		sprite.setLocation((int)sprite.getX(), (int)sprite.getY()+y);
		for(Rectangle box: Collision){
			//Detection for Y collision
			if(sprite.intersects(box)){
				sprite.setLocation((int)sprite.getX(), (int)sprite.getY()-y);
				break;
			}
			
		}
		
		
		
	}

}
