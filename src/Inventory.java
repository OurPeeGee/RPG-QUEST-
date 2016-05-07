import java.lang.*;
import java.util.*;
import java.text.*;

public class Inventory extends Player{
	
	List <Integer> Bag= new ArrayList<Integer>(30);
	public int select=0;
	public int weight=0;
	public Inventory(int ref, String x,String d) {
		super(ref, x, d);
		//refnum=ref;
		//name=x;
		//Usless shit that needs to be here just so player 
		//can access the inventory class
	}
	public int getNext()
	{
		select+=1;
		return Bag.get(select);
	}
	public void addItem(int refId)
	{
		Bag.add(select, refId);
	}
	public int selectLocation()
	{
		return Bag.get(refId)
	}

}
