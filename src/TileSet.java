//Marcus Moad
public class TileSet {
	
	private int firstGid;
	private int lastGid;
	private String Name;
	private int tileWidth;
	private int tileHeight;
	private String Source;
	private int imageHeight;
	private int imageWidth;
	private int tileAmountWidth;
	
	public TileSet(int fGid, String name, int tWidth, int tHeight, String source, int iHeight, int iWidth){
		firstGid = fGid;
		
		Name = name;
		tileWidth = tWidth;
		tileHeight = tHeight;
		Source = source;
		imageHeight = iHeight;
		imageWidth = iWidth;
		tileAmountWidth = Math.floorDiv(imageWidth, tileWidth);
		
		lastGid = tileAmountWidth * Math.floorDiv(imageHeight, tileHeight)+firstGid - 1;
		
	}
	
	
	public int getFirstGid(){
		return firstGid;
	}
	public int getLastGid(){
		return lastGid;
	}
	public int getTileWidth(){
		return tileWidth;
	}
	public int getTileHeight(){
		return tileHeight;
	}
	public int getTileAmountWidth(){
		return tileAmountWidth;
	}
	public String getName(){
		return Name;
	}

}
