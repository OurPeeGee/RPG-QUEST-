import java.lang.*;
import java.util.*;
import java.text.*;

public class Player extends Character {
	
	public int healthmax=6;//3 hearts
	public int health=6;//start at max hp
	public static int maxhp=24;
	public int spd= 32;//pixels per second
	public int dmg = 2;//
	public int size=32;//unchanging
	public int width=32;//unchanging
	public int luck=0;//effects drop rates of all items
	public double armour= 1;//1/50 chance to completely block a hit damage
	public int gold=0;//cash money
	public int attackspd=1;//swings per second
	public boolean sheildup=false;//sheild starts down
	public int sheildhits=1;//Starting number of hits the sheild can block at a time
	Random r=new Random();//for armor blocks
	public Inventory player=new Inventory();//creates base inventory log of items 
	//i am a mean lean meme machine
	static Scanner scan=new Scanner(System.in);
	public String name="Player";
	public static void main(String[] args)
	{
		Player p1=new Player(0001,"","");
		p1.setName(scan.next());
	}
	
	public Player(int ref, String x,String d) {
		super(ref, x, d);
		refnum=ref;
		name="Player";//Default can be changed at start of run
	}
	public void setName(String n)
	{
		name=n;
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
	public int getDmg()
	{
		return dmg;
	}
	public void addItemtoInv(Item pickup)
	{
		//stats are as follow s=spd, a= armor, d=dmg,attackspd=k,sheildhits=b(forblock)
		//h=health g=gold
		player.addItem(pickup);
		int num1=player. getModNumber1(pickup);
		int num2=player. getModNumber2(pickup);
		char fun1=player.getItemFun1(pickup);
		char fun2=player.getItemFun2(pickup);
		char stat1=player.getPlayerStat1(pickup);
		char stat2=player.getPlayerStat2(pickup);
		if(stat1=='s')
		{
			if(fun1=='*')
			{
				spd*=num1;
			}
			else if(fun1=='/')
			{
				spd/=num1;
			}
			else if(fun1=='+')
			{
				spd+=num1;
			}
			else if(fun1=='-')
			{
				spd-=num1;
			}
		}
		else if(stat1=='d')
		{
			if(fun1=='*')
			{
				dmg*=num1;
			}
			else if(fun1=='/')
			{
				dmg/=num1;
			}
			else if(fun1=='+')
			{
				dmg+=num1;
			}
			else if(fun1=='-')
			{
				dmg-=num1;
			}
			
		}
		else if(stat1=='b')
		{
			if(fun1=='*')
			{
				sheildhits*=2;
			}
			else if(fun1=='/')
			{
				sheildhits/=2;
			}
			else if(fun1=='+')
			{
				sheildhits+=num1;
			}
			else if(fun1=='-')
			{
				sheildhits-=num1;
			}
			
		}
		else if(stat1=='k')
		{
			if(fun1=='*')
			{
				attackspd*=num1;
			}
			else if(fun1=='/')
			{
				attackspd/=num1;
			}
			else if(fun1=='+')
			{
				attackspd+=num1;
			}
			else if(fun1=='-')
			{
				attackspd-=num1;
			}
			
		}
		else if(stat1=='a')
		{
			if(fun1=='*')
			{
				armour*=num1;
			}
			else if(fun1=='/')
			{
				armour/=num1;
			}
			else if(fun1=='+')
			{
				armour+=num1;
			}
			else if(fun1=='-')
			{
				armour-=num1;
			}
			
		}
		else if(stat1=='g')
		{
			
		    if(fun1=='+')
			{
				gold+=num1;
			}
			else if(fun1=='-')
			{
				gold-=num1;
			}
			
		}
		else if(stat1=='h'&& health<maxhp)
		{
			if(fun1=='*')
			{
				if(healthmax*2<=24)
				{
				  healthmax*=2;
				  health=health+(healthmax/2);
				}
				else if(healthmax*2>=24)
				{
					healthmax=24;
					health=healthmax;
				}
			}
			else if(fun1=='/')
			{
				if(healthmax/2>0)
					healthmax/=2;
			}
			else if(fun1=='+')
			{
				healthmax+=num1;
				health+=num1;
			}
			else if(fun1=='-')
			{
				healthmax-=num1;
				health-=num1;
			}
			
		}
		//secondary stats no hp or gold
		if(stat2=='s')
		{
			if(fun2=='*')
			{
				spd*=num2;
			}
			else if(fun2=='/')
			{
				spd/=num2;
			}
			else if(fun2=='+')
			{
				spd+=num2;
			}
			else if(fun2=='-')
			{
				spd-=num2;
			}
		}
		else if(stat2=='d')
		{
			if(fun2=='*')
			{
				dmg*=num2;
			}
			else if(fun2=='/')
			{
				dmg/=num2;
			}
			else if(fun2=='+')
			{
				dmg+=num2;
			}
			else if(fun2=='-')
			{
				dmg-=num2;
			}
			
		}
		else if(stat2=='b')
		{
			if(fun2=='*')
			{
				sheildhits*=2;
			}
			else if(fun2=='/')
			{
				sheildhits/=2;
			}
			else if(fun2=='+')
			{
				sheildhits+=num2;
			}
			else if(fun2=='-')
			{
				sheildhits-=num2;
			}
			
		}
		else if(stat2=='k')
		{
			if(fun2=='*')
			{
				attackspd*=num2;
			}
			else if(fun2=='/')
			{
				attackspd/=num2;
			}
			else if(fun2=='+')
			{
				attackspd+=num2;
			}
			else if(fun2=='-')
			{
				attackspd-=num2;
			}
			
		}
		else if(stat2=='a')
		{
			if(fun2=='*')
			{
				armour*=num1;
			}
			else if(fun2=='/')
			{
				armour/=num1;
			}
			else if(fun2=='+')
			{
				armour+=num1;
			}
			else if(fun2=='-')
			{
				armour-=num1;
			}
			
			
		}
	}
	public void setDmgTake()
	{
		if(armour<r.nextInt(50)+1)
			health=health-1;
		else
			System.out.println("BLOCKED");
	}
	public int largePotionPickup()
	{
		if(health<healthmax&& health<healthmax-2)
		{
			health=+2;
			return 1;
		}
		else if(health<healthmax && health<healthmax-2)
		{
			health+=1;
			return 1;
		}
		else
		{
			return -1;
		}
	}
	public int smallPotionPickup()
	{
		if(health<healthmax)
		{
			health=+1;
			return 1;
		}
		return -1;
	}
	public void modHealth(Item h)
	{
		
	}
	public void modSpd(Item s)
	{
		
	}
	public void modDmg(Item d)
	{
		
	}
	public void modArmor(Item a)
	{
		
	}

}

