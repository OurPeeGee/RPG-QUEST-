import java.lang.*;
import java.util.*;
import java.text.*;

public class Entity {
	public int refnum;
	public String name;
	public String discript;
	public Entity(int ref, String x,String d)
	{
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
	
	
}
