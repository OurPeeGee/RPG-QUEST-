
public class Item extends Entity {
	public int refnum;
	public String name;
	public String discript;
	//public char itemt;
	public int num1;//number of primary mod
	public char stat1;//type of stat mod
	public char stat2;//stat of the 2 mod
	public int num2;//number of the mod2
	public char modt2;//secondary type of stat mod
	public char fun1;
	public char fun2;
	public Item(int ref, String x, String d,  int modnum1,
		char modstat1,char modfun1, int modnum2, char modstat2,char modfun2) {
		super(ref, x, d);
		refnum= ref;
		name=x;
		discript=d;
		//itemt=itemtype;//the item type
		//modnum1=modifynum;//the actual number that the mod will apply
		stat1=modstat1;//the type of stat mod to the player
		stat2=modstat2;//
		num1=modnum1;//the number that will be given
		num2=modnum2;//
		fun1=modfun1;//the function applied m=* s=- d=/ a=+
		fun2=modfun2;
		
		/*Notebly you do not have to pass two modding specifications
		 say the object on the ground is a simple coin then just pass a coin data
		 c throught the mpd stat and the coin value throught the num
		 1
		 and the fun would be the char +
		 the rest would be  */
	}
	
}
