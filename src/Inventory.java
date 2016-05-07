import java.lang.*;
import java.util.*;
import java.text.*;

public class Inventory extends Item{
	
	List <Item> Bag= new ArrayList<Item>(30);
	public int select=0;
	public int weight=0;
	private int end=Bag.size();
	public Inventory(int ref, String x,String d) {
		super(ref, x, d);
		//refnum=ref;
		//name=x;
		//Usless shit that needs to be here just so player 
		//can access the inventory class
		for(int i=0; i<Bag.size(); i++)
		{
			Bag.set(i, null);
		}
	}
	public Item getNext()
	{
		select+=1;
		return Bag.get(select);
	}
	public void addItem(Item refId)
	{
		boolean t= true;
		int i=0;
		while(t)
		{
			if(Bag.get(i) == null)
			{
				Bag.add(end, refId);
				t=false;
			}
			i++;
		}
	}
	public void selectLocation()
	{
		// Pops up text box with discprition
		System.out.println(Bag.get(select).discript);
		
	}
	public void InventoryListPrint()
	{
		for(int i=select; i<Bag.size(); i++)
		{
			System.out.println(Bag.get(select).name);
			
		}
	}
	//weight class
}
