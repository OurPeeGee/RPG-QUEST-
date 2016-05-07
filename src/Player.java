import java.lang.*;
import java.util.*;
import java.text.*;

public class Player extends Character {
	
	public int health=100;
	public int spd= 32*3;
	public int dmg = 15;
	public int weight= 0;
	
	public Player(int ref, String x,String d) {
		super(ref, x, d);
		refnum=ref;
		name=x;
		discript= d;
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
