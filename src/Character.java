import java.lang.*;
import java.util.*;
import java.text.*;

public class Character extends Entity {

	public int health=100;
	public int spd= 32;
	public int dmg = 15;
	
	
	 public Character(int ref, String x,String d) {
		super(ref, x, d);
		refnum=ref;
		name=x;
		discript=d;
		
		
	}
	public int getRef()
	{
		return refnum;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getSpd()
	{
		return spd;
	}
	
	public int getDmg()
	{
		return dmg;
	}
	
	public void setDmgTake(int hit)
	{
		health=health-hit;
	}
	
}
