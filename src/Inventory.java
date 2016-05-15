import java.lang.*;
import java.util.*;
import java.text.*;

public class Inventory /*extends Item*/{
	
	List <Item> Bag= new ArrayList<Item>(1);
	public int select=0;
	public int weight=0;
	private int end=Bag.size();
	
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
	public char getPlayerStat1(Item pickup)
	{
		return pickup.stat1;
	}
	public char getItemFun1(Item pickup)
	{
		return pickup.fun1;
	}
	public char getPlayerStat2(Item pickup)
	{
		return pickup.stat2;
	}
	public char getItemFun2(Item pickup)
	{
		return pickup.fun2;
	}
	public int getModNumber1(Item pickup)
	{
		return pickup.num1;
	}
	public int getModNumber2(Item pickup)
	{
		return pickup.num2;
	}

}
