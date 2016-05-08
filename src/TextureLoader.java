
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;


public class TextureLoader {//Load will need to return a hashmap of the filename as the key, and a bitmap image for that level to display
	
	public static HashMap<String, BufferedImage> load(){//Only loads one scene right now
		HashMap<String, BufferedImage> tiles = new HashMap<String, BufferedImage>();
		List<TileSet> Tiles = new ArrayList<TileSet>();
		List<BufferedImage> tileImages = new ArrayList<BufferedImage>();
		int firstGid;
		String Name;
		int mapWidth;
		int mapHeight;
		int tileWidth;
		int tileHeight;
		int imageWidth;
		int imageHeight;
		String ImagePath;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File( "resources/tilemaps/series1/testSewer/SewerTest1.xml" ));
			Element map = document.getElementById("map");
			mapWidth = Integer.parseInt(map.getAttribute("width"));
			mapHeight = Integer.parseInt(map.getAttribute("height"));
			NodeList nList = document.getElementsByTagName("tileset");
			int totalTileSets = 0;
			for(int i = 0; i < nList.getLength(); i++){
				Node node =  nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) node;
					Element eImage = (Element) node.getFirstChild();
					Name = eElement.getAttribute("name");
					firstGid = Integer.parseInt(eElement.getAttribute("firstgid"));
					tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
					tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
					imageWidth = Integer.parseInt(eImage.getAttribute("width"));
					imageHeight = Integer.parseInt(eImage.getAttribute("height"));
					ImagePath = eImage.getAttribute("source");
					Tiles.add(new TileSet(firstGid, Name, tileWidth, tileHeight, ImagePath, imageHeight, imageWidth));
					
					BufferedImage tileset = ImageIO.read(new File(ImagePath));
					if(!(tiles.containsKey(Name))){
						tiles.put(Name,tileset);
						totalTileSets++;
					}
					
					
				}
				
				
				
			}
			//If the layer's name is Top, then that layer will be flagged to be rendered on the top layer of the Bitmap, so that the player can pass underneath it.
			//If the name is't top, then it will just follow the order layed out by the Tiled program.
			NodeList lList = document.getElementsByTagName("layer");
			for(int i = 0; i<lList.getLength(); i++){
				int[] tileArray = new int[767];
				Node layer = lList.item(i);
				NodeList tileList = layer.getChildNodes();
				int tileLength = 0;
				for(int z = 0; z<tileList.getLength(); z++){
					Node tile = tileList.item(i);
					Element eTile = (Element) tile;
					int gid = Integer.parseInt(eTile.getAttribute("gid"));
					if(gid>0){
						tileArray[tileLength] = gid;
					}
					tileLength++;
				}
			Element eLayer = (Element) layer;
			String layerName = eLayer.getAttribute("name");
			//Decide where' we're going to put the layer
			int layerMap = 0;
			switch(layerName){
			case"TileLayer1": layerMap = 1; break;
			default: break;
			}
			
			int[][] tileCoordinates = new int[32][24];
			
			for(int tileX = 0; tileX<mapWidth; tileX++){
				
				for(int tileY = 0; tileY<mapHeight;tileY++){
					tileCoordinates[tileY][tileX] = tileArray[(tileX+(tileY*mapWidth))];
				}
				
			}
			
			for(int spriteForX = 0; spriteForX < mapWidth; spriteForX++){
				for(int spriteForY = 0; spriteForY < mapHeight; spriteForY++){
					int tileGid = (int) tileCoordinates[spriteForY][spriteForX];
					TileSet currentTileSet = null;
					// only use tiles from this tileset (we get the source image from here)
					for(TileSet tileSet1: Tiles){
						if(tileGid >= tileSet1.getFirstGid()-1){
							//We found the right tileset for this gid
							currentTileSet = tileSet1;
							break;
						}
					}
					
					// basic math to find out where the tile is coming from on the source image
					int destY = spriteForY*currentTileSet.getTileWidth();//might need to be replace with tileHeight, since I suspect this is a bug here.  //TODO
					int destX = spriteForX*currentTileSet.getTileWidth();
					
					tileGid = currentTileSet.getFirstGid() -1;
					
					 // copy the tile from the tileset onto our bitmap
					if(layerMap == 0){
						
					}
					
					//We found the right tileset for this gid
					//currentTileSet = tileSet1;
				}
				
				
				
				
				
				
			}
				
				
				
				
				
			}
			/*
			 * List<BufferedImage> tileImages = new ArrayList<BufferedImage>();
			for(int i = 0; i<totalTileSets; i++){
				BufferedImage tileset = ImageIO.read(file)
				
				
				
			}
			*/
			/*
			int[][] tileCoordinates;
			for(int i = 0; i<mapWidth; i++){
				for(int x = 0; x<mapHeight; x++){
					tileCoordinates[i][x] = Tiles.get(i+(x*mapWidth));
				}
			}
			*/
			
			//tiles.put( ,Tiles);
		
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return tiles;
	}
	
	

}

//"resources/tilemaps/series1/testSewer/SewerTest1.xml"