
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

//TODO currently only supports one tileMap
public class TextureLoader {//Load will need to return a hashmap of the filename as the key, and a bitmap image for that level to display
	
	private static HashMap<String, BufferedImage[]> tiles = new HashMap<String, BufferedImage[]>();
	private static HashMap<String, ArrayList<BufferedImage>> levelMaps = new HashMap<String, ArrayList<BufferedImage>>();
	private static ArrayList<String> LevelNameList = new ArrayList<String>();
	
	
	public static void loadList(){
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File( "resources\\testLevels\\testLevelList.xml" ));
			//Node levelList = document.getDocumentElement();
			//Element eList = (Element) levelList;
			NodeList lList = document.getElementsByTagName("level");
			
			
			
			String name;
			String filePath;
			for(int i = 0; i<lList.getLength(); i++){
				Element level = (Element) lList.item(i);
				name = level.getAttribute("name");
				filePath = level.getAttribute("dataPath");
				System.out.println(name + " " + filePath);
				LevelNameList.add(name);
				load(name, filePath);
			}
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	//TODO each map file will need to have 3 layers: the base layer, the transparent base layer, and a top layer.  
	public static void load(String LName, String filePath){//Only loads one scene right now
		//HashMap<String, BufferedImage> tiles = new HashMap<String, BufferedImage>();
		
		//TODO this method will need to accept a file source.  Another method will call this one giving the file source for EACH Map of the game
		
		List<TileSet> Tiles = new ArrayList<TileSet>();
		//List<BufferedImage> tileImages = new ArrayList<BufferedImage>();
		int firstGid;
		String Name;
		int mapWidth;
		int mapHeight;
		int tileWidth;
		int tileHeight;
		int imageWidth;
		int imageHeight;
		String ImagePath;
		//String filePathName = filePath;//"resources\\tilemaps\\series1\\testsewer\\SewerTest1.xml";//TODO TEMPORARY FOR TESTING
		ArrayList<BufferedImage> levelMap = new ArrayList<BufferedImage>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			//Document document = builder.parse(new File( "resources\\tilemaps\\series1\\testsewer\\SewerTest1.xml" ));
			Document document = builder.parse(new File(filePath));
			
			//Node mapNode = 
			Node map = document.getDocumentElement();
			//if(map == null){
			//	System.out.println("FAILURE");
			//}
			Element eMap = (Element) map;
			mapWidth = Integer.parseInt(eMap.getAttribute("width"));
			mapHeight = Integer.parseInt(eMap.getAttribute("height"));
			//BufferedImage screenBitmap = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
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
						for(int q = 0; q<tileSetLoad.getTileAmountWidth()-1; q++){//This somehow prevents the method from ever reaching the last part of the tilemap.
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
			ArrayList<BufferedImage> layers = new ArrayList<BufferedImage>();
			NodeList lList = document.getElementsByTagName("layer");
			//TODO I think screenBitmap is getting all layers rendered to it prematurely.
			
			BufferedImage screenBitmapTopLayer = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
			Graphics2D[] gList = new Graphics2D[lList.getLength()];
			//for(int i = 0; i<1; i++){
			System.out.println(lList.getLength());
			for(int i = 0; i<lList.getLength(); i++){
				BufferedImage screenBitmap = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
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
						//System.out.println(gid);
						tileArray[tileLength] = gid;//This constructs the tileArray and assigns each part the proper dig id, unless it is zero, since zero is not stored.
					}
					tileLength++;
				}
				tileLength++;
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
			
			
			//File file = new File("resources\\tilemaps\\series1\\sewer_1.png");
			//File file = new File("resources\\tilemaps\\series1\\sewer_1.png");
			//FileInputStream fis = new FileInputStream(file);
		//BufferedImage[] tileset = new BufferedImage[352];
			//BufferedImage tileset = ImageIO.read(fis);
			
			
			
			//BufferedImage screenBitmap = new BufferedImage(mapWidth*16, mapHeight*16, tileset.getType());
			//BufferedImage screenBitmapTopLayer = new BufferedImage(mapWidth*16, mapHeight*16, tileset.getType());
			//screenBitmap.
			//Graphics2D g = screenBitmap.createGraphics();
			//This loop might need fixing, since I think it loads everything to screenbitmap too early
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
					//if(layerMap == 0){
						//The issue appears to be coming from tileGid.  It appears that the data array in the hashmap may be wrong
						//g.drawImage(tiles.get(currentTileSet.getName())[tileGid], destX, destY, currentTileSet.getTileWidth(), currentTileSet.getTileWidth(), null);
						//System.out.println(tileGid);
						//g.drawImage(tiles.get(currentTileSet.getName())[tileGid], destX, destY, currentTileSet.getTileWidth(), currentTileSet.getTileWidth(), null);
						//gList[i] = 
						Graphics2D g = screenBitmap.createGraphics();
						g.drawImage(tiles.get(currentTileSet.getName())[tileCoordinates[spriteForY][spriteForX]], destX, destY, currentTileSet.getTileWidth(), currentTileSet.getTileWidth(), null);
						
						//layers.add(e)
						
						
						//screenBitmap.setData(new Raster(null, null, null, null, null));
						
						//screenBitmap.createGraphics().drawImage(tiles.get(currentTileSet.getName())., x, y, observer)
						//screenBitmap.
						//screenBitmap.
						
					//}
				//	else{
						
					//	Graphics2D g = screenBitmapTopLayer.createGraphics();
					//	g.drawImage(tiles.get(currentTileSet.getName())[tileCoordinates[spriteForY][spriteForX]], destX, destY, currentTileSet.getTileWidth(), currentTileSet.getTileWidth(), null);
						
					//}
					
					//We found the right tileset for this gid
					//currentTileSet = tileSet1;
				}
				
				
				
				
				
				
			}
			
			layers.add(screenBitmap);
			screenBitmap.flush();
			//g.finalize();
			
				
				
				
				
			}
			//This currently combines 2 layers into one BufferedImage
			BufferedImage combined = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
			Graphics2D gc = combined.createGraphics();
			//for(int w = 0; w<layers.size(); w++){//This needs to combine the first two layers into the background image
			for(int w = 0; w<layers.size()-1; w++){//This loop combines already combined images
				System.out.println(w+ " layers.length: " + layers.size());
				//may need to limit to constant size of 2 so that we only combine the first 2 layers.
				gc.drawImage(layers.get(w), 0, 0, null);
				
				
				
				
			}
			//gc.finalize();
			
			levelMap.add(combined);
			combined.flush();
			
			BufferedImage combined2 = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
			Graphics2D gc2 = combined2.createGraphics();
			gc2.drawImage(layers.get(layers.size()-1), 0, 0, null);
			levelMap.add(combined2);
			combined2.flush();
			
			
			//BufferedImage combined = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
			//Graphics2D gc = combined.createGraphics();
			//gc.drawImage(screenBitmapTopLayer, 0, 0, null);
			//gc.drawImage(screenBitmap, 0, 0, null);
			//gc.finalize();
		//	levelMaps.add
		File outputfile = new File("image3.png");
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
		levelMaps.put(LName, levelMap);
		//return tiles;
	}
	
	
	//private BufferedImage createOneTile(String name,){
		//BufferedImage res = new BufferedImage()
	//}
	
	public static ArrayList<String> getLevelList(){
		return LevelNameList;
	}
	
	public static ArrayList<BufferedImage> getLevelMap(String name){
		return levelMaps.get(name);
	}
	
	public static BufferedImage[] getTileMap(String name){
		return tiles.get(name);
	}

}

//"resources/tilemaps/series1/testSewer/SewerTest1.xml"