
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;


public class TextureLoader {//Load will need to return a hashmap of the filename as the key, and a bitmap image for that level to display
	
	private static HashMap<String, BufferedImage[]> tiles = new HashMap<String, BufferedImage[]>();
	
	public static void load(){//Only loads one scene right now
		//HashMap<String, BufferedImage> tiles = new HashMap<String, BufferedImage>();
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
			Document document = builder.parse(new File( "resources\\tilemaps\\series1\\testsewer\\SewerTest1.xml" ));
			//Node mapNode = 
			Node map = document.getDocumentElement();
			if(map == null){
				System.out.println("FAILURE");
			}
			Element eMap = (Element) map;
			mapWidth = Integer.parseInt(eMap.getAttribute("width"));
			mapHeight = Integer.parseInt(eMap.getAttribute("height"));
			NodeList nList = document.getElementsByTagName("tileset");
			int totalTileSets = 0;
			for(int i = 0; i < nList.getLength(); i++){
				Node node =  nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) node;
					NodeList ImageNodes = document.getElementsByTagName("image");
					Element eImage = (Element) ImageNodes.item(0);
					//Element eImage = (Element) ImageNode;
					//Node ImageNode =  eElement.getFirstChild();
					//Element eImage = (Element) ImageNode;
					Name = eElement.getAttribute("name");
					firstGid = Integer.parseInt(eElement.getAttribute("firstgid"));
					tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
					tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
					imageWidth = Integer.parseInt(eImage.getAttribute("width"));
					imageHeight = Integer.parseInt(eImage.getAttribute("height"));
					ImagePath = eImage.getAttribute("source");
					//ImagePath = eElement.getFirstChild()
					TileSet tileSetLoad = new TileSet(firstGid, Name, tileWidth, tileHeight, ImagePath, imageHeight, imageWidth);
					Tiles.add(new TileSet(firstGid, Name, tileWidth, tileHeight, ImagePath, imageHeight, imageWidth));
					
					//BufferedImage tiled = ImageIO.read(new File(ImagePath));
					BufferedImage[] tileset = new BufferedImage[tileSetLoad.getTileAmountWidth()*Math.floorDiv(imageHeight,tileHeight)];
					BufferedImage tileBase = ImageIO.read(new File(ImagePath));//This contains the tileSet image and is accessed through the key which is it's name.
					for(int a = 0; a< Math.floorDiv(imageHeight,tileHeight); a++){
						for(int q = 0; q<tileSetLoad.getTileAmountWidth()-1; q++){
							//TODO this adds all of the tiles into the array, but they are all accessed in a way that 
							//leaves the first index empty to serve as the transparent tile, and then the last tile may or may not actually get put into the array, 
							//This needs to be tested to make sure we don't run into errors or limitations while building the game
							tileset[q+a*tileSetLoad.getTileAmountWidth()+1] = tileBase.getSubimage(q*tileWidth, a*tileHeight, tileWidth, tileHeight);
							//tileset[q+a*tileSetLoad.getTileAmountWidth()] = tileBase.getSubimage(1,1,1,1);
							
						}
						
						
						
						
						
						
						
						
						//tileset[a] = tileBase.getSubimage(a*16, a*16, 16, 16);
						//Graphics2D gr = tileset[a].createGraphics();  
						//gr.drawImage(tileBase, 0, 0, 16, 16, 16 * a%32, 16 * a, 16 * a%32 + 16, 16 * a + 16, null);  
						
					}
					
					if(!(tiles.containsKey(Name))){
						tiles.put(Name,tileset);
						totalTileSets++;
					}
					
					
				}
				
				
				
			}
			
			
			
			//try{
				
			//}
			
			
			
			
			
			//If the layer's name is Top, then that layer will be flagged to be rendered on the top layer of the Bitmap, so that the player can pass underneath it.
			//If the name is't top, then it will just follow the order layed out by the Tiled program.
			
			//This method constructs the entire image.  This can be done better by putting a hashmap of the map name as the key to a list of layer
			//tilecoordinate arrays which are then constructed and printed
			NodeList lList = document.getElementsByTagName("layer");
			BufferedImage screenBitmap = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
			BufferedImage screenBitmapTopLayer = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
			Graphics2D[] gList = new Graphics2D[lList.getLength()];
			//for(int i = 0; i<1; i++){
			for(int i = 0; i<lList.getLength(); i++){
				int[] tileArray = new int[mapWidth*mapHeight];//TODO might need to change from a constant to a variable
				Node layer = lList.item(i);
				//NodeList dataList = lList.
				Element eLayer = (Element) layer;
				NodeList dataList = eLayer.getElementsByTagName("data");
				Node data = dataList.item(0);
				Element eData = (Element) data;
				NodeList tileList = eData.getElementsByTagName("tile");
				//NodeList tileList = eData.getChildNodes();//This needs to get the tiles only for this specific data node.
				//System.out.println(dataList.getLength());
				//System.out.println(tileList.getLength());
				int tileLength = 0;
				for(int z = 0; z<tileList.getLength(); z++){
					Element tile =  (Element) tileList.item(z);
					
					Element eTile = (Element) tile;
					int gid = Integer.parseInt(eTile.getAttribute("gid"));
					//System.out.println(gid);
					if(gid>0){
						System.out.println(gid);
						tileArray[tileLength] = gid;//This constructs the tileArray and assigns each part the proper dig id, unless it is zero, since zero is not stored.
					}
					tileLength++;
				}
			
			String layerName = eLayer.getAttribute("name");
			//Decide where' we're going to put the layer
			int layerMap = 0;
			switch(layerName){
			case"TileLayer1": layerMap = 1; break;
			default: break;
			}
			
			int[][] tileCoordinates = new int[24][32];
			
			for(int tileY = 0; tileY<mapHeight; tileY++){
				
				for(int tileX = 0; tileX<mapWidth;tileX++){
					//tileCoordinates[tileY][tileX] = tileArray[(tileX+(tileY*mapWidth))];
					tileCoordinates[tileY][tileX] = tileArray[(tileX+(tileY*mapWidth))];//This is the coordinate list for this layer of the bitmap
				//	System.out.println(tileCoordinates[tileY][tileX]);
				}
				
			}
			//BufferedImage screenBitmap = new BufferedImage(mapWidth*currentTileSet.getTileWidth(), mapHeight*currentTileSet.getTileHeight(), BufferedImage.TYPE_BYTE_BINARY);
			//BufferedImage screenBitmapTopLayer = new BufferedImage(mapWidth*currentTileSet.getTileWidth(), mapHeight*currentTileSet.getTileHeight(), BufferedImage.TYPE_BYTE_BINARY);
			//BufferedImage screenBitTiles = new BufferedImage[]
			
			
			File file = new File("resources\\tilemaps\\series1\\sewer_1.png");
			FileInputStream fis = new FileInputStream(file);
		//BufferedImage[] tileset = new BufferedImage[352];
			BufferedImage tileset = ImageIO.read(fis);
			
			
			
			//BufferedImage screenBitmap = new BufferedImage(mapWidth*16, mapHeight*16, tileset.getType());
			//BufferedImage screenBitmapTopLayer = new BufferedImage(mapWidth*16, mapHeight*16, tileset.getType());
			//screenBitmap.
			//Graphics2D g = screenBitmap.createGraphics();
			for(int spriteForY = 0; spriteForY < mapHeight; spriteForY++){
				for(int spriteForX = 0; spriteForX < mapWidth; spriteForX++){
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
					
					
					//img = new BufferedImage()
					//BufferedImage tile = new BufferedImage(currentTileSet.getTileWidth(), currentTileSet.getTileWidth(), tileset.getType());
					
					// basic math to find out where the tile is coming from on the source image
					int destY = spriteForY*currentTileSet.getTileWidth();//might need to be replace with tileHeight, since I suspect this is a bug here.  //TODO
					int destX = spriteForX*currentTileSet.getTileWidth();
					
					tileGid -= currentTileSet.getFirstGid() -1;
					int sourceY = (int) Math.ceil(tileGid/currentTileSet.getTileAmountWidth()-1);//This might be where the error is.  the source seems like it might be wrong
					int sourceX = tileGid - (currentTileSet.getTileAmountWidth()*sourceY) - 1;
					 // copy the tile from the tileset onto our bitmap
					if(layerMap == 0){
						//The issue appears to be coming from tileGid.  It appears that the data array in the hashmap may be wrong
						//g.drawImage(tiles.get(currentTileSet.getName())[tileGid], destX, destY, currentTileSet.getTileWidth(), currentTileSet.getTileWidth(), null);
						//System.out.println(tileGid);
						//g.drawImage(tiles.get(currentTileSet.getName())[tileGid], destX, destY, currentTileSet.getTileWidth(), currentTileSet.getTileWidth(), null);
						//gList[i] = 
						Graphics2D g = screenBitmap.createGraphics();
						g.drawImage(tiles.get(currentTileSet.getName())[tileCoordinates[spriteForY][spriteForX]], destX, destY, currentTileSet.getTileWidth(), currentTileSet.getTileWidth(), null);
						
						
						//screenBitmap.setData(new Raster(null, null, null, null, null));
						
						//screenBitmap.createGraphics().drawImage(tiles.get(currentTileSet.getName())., x, y, observer)
						//screenBitmap.
						//screenBitmap.
						
					}
					else{
						
						Graphics2D g = screenBitmapTopLayer.createGraphics();
						g.drawImage(tiles.get(currentTileSet.getName())[tileCoordinates[spriteForY][spriteForX]], destX, destY, currentTileSet.getTileWidth(), currentTileSet.getTileWidth(), null);
						
					}
					
					//We found the right tileset for this gid
					//currentTileSet = tileSet1;
				}
				
				
				
				
				
				
			}
			//g.finalize();
			
				
				
				
				
			}
			//This currently combines 2 layers into one BufferedImage
			BufferedImage combined = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
			Graphics2D gc = combined.createGraphics();
			gc.drawImage(screenBitmapTopLayer, 0, 0, null);
			gc.drawImage(screenBitmap, 0, 0, null);
			gc.finalize();
		File outputfile = new File("image2.png");
		ImageIO.write(combined, "png", outputfile);
			
			
			
			
			
			
			
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
		//return tiles;
	}
	
	
	//private BufferedImage createOneTile(String name,){
		//BufferedImage res = new BufferedImage()
	//}
	
	public static BufferedImage[] getTileMap(String name){
		return tiles.get(name);
	}

}

//"resources/tilemaps/series1/testSewer/SewerTest1.xml"