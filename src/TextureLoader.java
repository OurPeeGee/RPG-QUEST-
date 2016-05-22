//Primary Marcus Moad 
//Erik, Nick
//Texture preloader 
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

//TODO "currently"(most likely will)  only supports one tileMap
public class TextureLoader {//Load will need to return a hashmap of the filename as the key, and a bitmap image for that level to display
	
	private static BufferedImage spriteSheet;
	private static HashMap<String, BufferedImage[]> tiles = new HashMap<String, BufferedImage[]>();//This is the map of the tiles for the image constructor
	private static HashMap<String, ArrayList<BufferedImage>> levelMaps = new HashMap<String, ArrayList<BufferedImage>>(); //This is the map for the constructed images, which are stored in layers
	private static ArrayList<String> LevelNameList = new ArrayList<String>();//This needs to be a hashmap.  //This is only used once, at the start of the first GameStateWorld.  It is never used again after that
	private static List<TileSet> Tiles = new ArrayList<TileSet>();  //This holds the data for each tileset
	private static int MapHeight;//Height of the level in tiles
	private static int MapWidth;//Width of the level in tiles
	private static double scale;//scaling of the window. 
	//private static ArrayList<Rectangle> CollisionTiles = new ArrayList<>();
	private static HashMap<String, ArrayList<Rectangle>> CollisionMap = new HashMap<String, ArrayList<Rectangle>>();//Contains the List of collision rectangles for the level with the name key
	private static HashMap<String, HashMap<String, Rectangle>> SpawnMap = new HashMap<String, HashMap<String, Rectangle>>();//Contains the HashMap of the Spawn rectangles for the map with the name. 
	//The key to the nested hashmap is the name of the level the player just left.
	private static HashMap<String, ArrayList<Rectangle>> ExitMap = new HashMap<String, ArrayList<Rectangle>>();//This stores the list of exits a level has. The key is the value of the exit# property in the xml files
	private static HashMap<String, HashMap<String, String>> ExitPathMap = new HashMap<String, HashMap<String, String>>();//This stores the ExitMap map.  The key is the mapName
	private static BufferedImage menuPause; //This is the image for the pause menu, which is currently the inventory screen.  This may be changed in the future TODO
	private static HashMap<String, HashMap<String, Rectangle>> PlayerStats = new HashMap<String, HashMap<String, Rectangle>>();//This is the map of the player stat display rectangles for that particular level (Menus are levels)
	//This will be used for menu interaction.  The key for the nested HashMap is the StatType, and the key for the outer hashmap is the level name (Menu name)
	
	public static BufferedImage getMenuPause(){
		return menuPause;
	}
	
	public static BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	
	public static void loadList(double SCALE){
		
		try {
			spriteSheet = ImageIO.read(new File("MiniKnight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			menuPause = ImageIO.read(new File("Menu.PNG"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scale = SCALE;
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
		
		
		//List<BufferedImage> tileImages = new ArrayList<BufferedImage>();
		int firstGid;
		String Name = "";
		int mapWidth;
		int mapHeight;
		int tileWidth;
		int tileHeight;
		int imageWidth;
		int imageHeight;
		int tileCount;
		int columns;
		int rows;
		//double scale=SCALE;
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
			MapWidth = mapWidth;
			MapHeight = mapHeight;
			NodeList property = document.getElementsByTagName("properties");
			if(property.getLength()>0){
				
			
				Element eProp = (Element) property.item(0);
				NodeList pList = eProp.getElementsByTagName("property");
				HashMap<String, String> exitPaths = new HashMap<String, String>();
				System.out.println("PropertiesLength: " + pList.getLength());
				for(int i = 0; i<pList.getLength(); i++){
					
					Node pNode = pList.item(i);
					if (pNode.getNodeType() == Node.ELEMENT_NODE){
						
						Element eProperty = (Element) pNode;
						exitPaths.put(eProperty.getAttribute("name"), eProperty.getAttribute("value"));
						//exitPaths.add(arg0)
						
						
						
					}
					
					
					
					
				}
				ExitPathMap.put(LName, exitPaths);
			}
			//BufferedImage screenBitmap = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
			NodeList nList = document.getElementsByTagName("tileset");
			int totalTileSets = 0;
			for(int i = 0; i < nList.getLength(); i++){
				System.out.println("tilesetsLength : " + nList.getLength());
				Node node =  nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) node;
					NodeList ImageNodes = document.getElementsByTagName("image");
					Element eImage = (Element) ImageNodes.item(i);
					System.out.println("Images Length: " + ImageNodes.getLength());
					//Element eImage = (Element) ImageNode;
					//Node ImageNode =  eElement.getFirstChild();
					//Element eImage = (Element) ImageNode;
					Name = eElement.getAttribute("name");
					firstGid =(int) ( Integer.parseInt(eElement.getAttribute("firstgid")));
					tileWidth =(int) ( Integer.parseInt(eElement.getAttribute("tilewidth")));
					tileHeight =(int) ( Integer.parseInt(eElement.getAttribute("tileheight")));
					imageWidth = (int) (Integer.parseInt(eImage.getAttribute("width")));
					imageHeight = (int) (Integer.parseInt(eImage.getAttribute("height")));
					ImagePath = eImage.getAttribute("source");
					tileCount = (int) (Integer.parseInt(eElement.getAttribute("tilecount")));
					columns = (int) (Integer.parseInt(eElement.getAttribute("columns")));
					rows = (int) (tileCount/columns);
					//ImagePath = eElement.getFirstChild()
					TileSet tileSetLoad = new TileSet(firstGid, Name, tileWidth, tileHeight, ImagePath, imageHeight, imageWidth);
					if(!tiles.containsKey(Name)){
						Tiles.add(new TileSet(firstGid, Name, tileWidth, tileHeight, ImagePath, imageHeight, imageWidth));
					}
					
					
					//BufferedImage tiled = ImageIO.read(new File(ImagePath));
					BufferedImage[] tileset = new BufferedImage[tileSetLoad.getTileAmountWidth()*Math.floorDiv(imageHeight,tileHeight)];
					BufferedImage tileBase = ImageIO.read(new File(ImagePath));//This contains the tileSet image and is accessed through the key which is it's name.
					//for(int a = 0; a< Math.floorDiv(imageHeight,tileHeight); a++){
					for(int a = 0; a< rows; a++){
						//for(int q = 0; q<tileSetLoad.getTileAmountWidth()-1; q++){//This somehow prevents the method from ever reaching the last part of the tilemap.
						for(int q = 0; q<columns-1; q++){
							//TODO this adds all of the tiles into the array, but they are all accessed in a way that 
							//leaves the first index empty to serve as the transparent tile, and then the last tile may or may not actually get put into the array, 
							//This needs to be tested to make sure we don't run into errors or limitations while building the game
							//tileset[q+a*tileSetLoad.getTileAmountWidth()+1] = tileBase.getSubimage(q*tileWidth, a*tileHeight, tileWidth, tileHeight);
							tileset[q+a*columns+1] = tileBase.getSubimage(q*tileWidth, a*tileHeight, tileWidth, tileHeight);
							//tileset[q+a*tileSetLoad.getTileAmountWidth()] = tileBase.getSubimage(1,1,1,1);
							
						}
						
						//tileset[a] = tileBase.getSubimage(a*16, a*16, 16, 16);
						//Graphics2D gr = tileset[a].createGraphics();  
						//gr.drawImage(tileBase, 0, 0, 16, 16, 16 * a%32, 16 * a, 16 * a%32 + 16, 16 * a + 16, null);  
						
					}
					tileBase.flush();
					
					if(!(tiles.containsKey(Name))){
						
						tiles.put(Name,tileset);
						
						//totalTileSets++;
					}
					
					
				}
				
				totalTileSets++;
				
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
			
			BufferedImage screenBitmapTopLayer = new BufferedImage((int)Math.floor(mapWidth*16*scale), (int) Math.floor(mapHeight*16*scale), BufferedImage.TYPE_INT_ARGB);
			Graphics2D[] gList = new Graphics2D[lList.getLength()];
			//for(int i = 0; i<1; i++){
			//System.out.println(lList.getLength());
			for(int i = 0; i<lList.getLength(); i++){
				BufferedImage screenBitmap = new BufferedImage((int)Math.floor(mapWidth*16*scale), (int) Math.floor(mapHeight*16*scale), BufferedImage.TYPE_INT_ARGB);
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
				//tileLength++;s
			String layerName = eLayer.getAttribute("name");
			//Decide where' we're going to put the layer
			int layerMap = 0;
			switch(layerName){
			case"TileLayer1": layerMap = 1; break;
			default: break;
			}
			
			int[][] tileCoordinates = new int[mapHeight][mapWidth];
			
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
			//for(TileSet tileSet1: Tiles){
			//	System.out.println(tileSet1.getName());
			//}
			
			
			//BufferedImage screenBitmap = new BufferedImage(mapWidth*16, mapHeight*16, tileset.getType());
			//BufferedImage screenBitmapTopLayer = new BufferedImage(mapWidth*16, mapHeight*16, tileset.getType());
			//screenBitmap.
			//Graphics2D g = screenBitmap.createGraphics();
			//This loop might need fixing, since I think it loads everything to screenbitmap too early
			System.out.println(Tiles.size());
			for(int spriteForY = 0; spriteForY < mapHeight; spriteForY++){
				for(int spriteForX = 0; spriteForX < mapWidth; spriteForX++){
					int tileGid = (int) tileCoordinates[spriteForY][spriteForX];
					TileSet currentTileSet = null;
					// only use tiles from this tileset (we get the source image from here)
					for(TileSet tileSet1: Tiles){
						if(tileSet1.getName().equals(Name)){
							currentTileSet = tileSet1;
							break;
						}
					//	System.out.println(tileSet1.getName());
						//System.out.println(tileSet1.getName());//TODO remove this for loop
						//if(tileGid >= tileSet1.getFirstGid()-1 && tileGid < tileSet1.getLastGid()){
							//We found the right tileset for this gid
							
							//currentTileSet = tileSet1;
							
							//break;
						//}
					}
					
					//System.out.println(currentTileSet.getName());
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
						g.drawImage(tiles.get(currentTileSet.getName())[tileCoordinates[spriteForY][spriteForX]],(int) (destX*scale),(int) (destY*scale), (int) (currentTileSet.getTileWidth()*scale),(int)( currentTileSet.getTileWidth()*scale), null);
						
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
			BufferedImage combined = new BufferedImage((int)Math.floor(mapWidth*16*scale), (int) Math.floor(mapHeight*16*scale), BufferedImage.TYPE_INT_ARGB);
			Graphics2D gc = combined.createGraphics();
			//for(int w = 0; w<layers.size(); w++){//This needs to combine the first two layers into the background image
			for(int w = 0; w<layers.size()-1; w++){//This loop combines already combined images
				//System.out.println(w+ " layers.length: " + layers.size());
				//may need to limit to constant size of 2 so that we only combine the first 2 layers.
				gc.drawImage(layers.get(w), 0, 0, null);
				//File outputfile = new File(LName + w + ".png");
			//	ImageIO.write(combined, "png", outputfile);
			
			}
			//gc.finalize();
			
			levelMap.add(combined);
			combined.flush();
			
			BufferedImage combined2 = new BufferedImage((int)Math.floor(mapWidth*16*scale), (int) Math.floor(mapHeight*16*scale), BufferedImage.TYPE_INT_ARGB);
			Graphics2D gc2 = combined2.createGraphics();
			gc2.drawImage(layers.get(layers.size()-1), 0, 0, null);
			//File outputfile = new File(LName + 2 + ".png");
			//ImageIO.write(combined, "png", outputfile);
			levelMap.add(combined2);
			combined2.flush();
			
			
			NodeList OList = document.getElementsByTagName("objectgroup");
			
			//This loades the collision rectangles into memory
			System.out.println(OList.getLength());
			for(int i = 0; i<OList.getLength(); i++){
				//System.out.println("UNRECOGNIZED OBJECT TYPE");
				Node ONode = OList.item(i);
				Element eObjectList = (Element) ONode;
				String ObjectGroup = eObjectList.getAttribute("name");
				switch(ObjectGroup){
				case "Collision": NodeList OBJECTSLIST = eObjectList.getElementsByTagName("object");
					ArrayList<Rectangle> CollisionTiles = new ArrayList<>();
					for(int a = 0; a<OBJECTSLIST.getLength(); a++){
						Node object = OBJECTSLIST.item(a);
						Element eObject = (Element) object;
						Rectangle r = new Rectangle();
						r.setBounds((int)Math.ceil(Double.parseDouble(eObject.getAttribute("x"))*scale), 
								(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("y"))*scale)), 
								(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("width"))*scale)), 
								
								
								(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("height"))*scale)));
										//(int)Math.ceil(16*scale));//(int)Math.ceil(Double.parseDouble(eObject.getAttribute("height"))));
						CollisionTiles.add(r);
						
					}System.out.println("Rectangles");
					CollisionMap.put(LName, CollisionTiles);
					break;
				case"Spawn":
					NodeList SpawnsList = eObjectList.getElementsByTagName("object");
					HashMap<String,Rectangle> SpawnRectList = new HashMap<String,Rectangle>();
					for(int a = 0; a<SpawnsList.getLength(); a++){
						
						String SpawnKey = "";
						Node object = SpawnsList.item(a);
						Element eObject = (Element) object;
						NodeList properties = eObject.getElementsByTagName("properties");
						System.out.println("SpawnPropertyLength: " + properties.getLength());
						for(int q = 0; q<properties.getLength();q++){
							Element eSProp = (Element) properties.item(q);
							NodeList pSList = eSProp.getElementsByTagName("property");
							for(int w = 0; w<pSList.getLength(); w++){
								Node SProperty = pSList.item(w);
								Element eSProperty = (Element) SProperty;
								SpawnKey = eSProperty.getAttribute("value");
								System.out.println("SpawnKey: " + SpawnKey);
							}
							//Node SProperty = properties.item(q);
							//Element eSProperty = (Element) SProperty;
							//SpawnKey = eSProperty.getAttribute("value");
							//System.out.println("SpawnKey: " + SpawnKey);
						}
						Rectangle r = new Rectangle();
						r.setBounds((int)Math.ceil(Double.parseDouble(eObject.getAttribute("x"))*scale), 
							(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("y"))*scale)), 
							(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("width"))*scale)), 
							(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("height"))*scale)));
										//(int)Math.ceil(16*scale));//(int)Math.ceil(Double.parseDouble(eObject.getAttribute("height"))));
						SpawnRectList.put(SpawnKey, r);
						
					}System.out.println("SpawnRectangles");
					SpawnMap.put(LName, SpawnRectList);
					break;
				case"LevelExit":
					NodeList ExitList = eObjectList.getElementsByTagName("object");
					ArrayList<Rectangle> ExitRectList = new ArrayList<Rectangle>();
					for(int a = 0; a<ExitList.getLength(); a++){
						Node object = ExitList.item(a);
						Element eObject = (Element) object;
						Rectangle r = new Rectangle();
						r.setBounds((int)Math.ceil(Double.parseDouble(eObject.getAttribute("x"))*scale), 
							(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("y"))*scale)), 
							(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("width"))*scale)), 
							(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("height"))*scale)));
										//(int)Math.ceil(16*scale));//(int)Math.ceil(Double.parseDouble(eObject.getAttribute("height"))));
						ExitRectList.add(r);
						
					}System.out.println("LevelExitRectangles");
					ExitMap.put(LName, ExitRectList);
					break;
				case"PlayerStats":
					NodeList StatList = eObjectList.getElementsByTagName("object");
					HashMap<String,Rectangle> StatRectMap = new HashMap<String,Rectangle>();
					for(int a = 0; a<StatList.getLength(); a++){
						
						String StatType = "";
						Node object = StatList.item(a);
						Element eObject = (Element) object;
						NodeList properties = eObject.getElementsByTagName("properties");
						System.out.println("PlayerStatLength: " + properties.getLength());
						for(int q = 0; q<properties.getLength();q++){
							Element eSProp = (Element) properties.item(q);
							NodeList pSList = eSProp.getElementsByTagName("property");
							for(int w = 0; w<pSList.getLength(); w++){
								Node SProperty = pSList.item(w);
								Element eSProperty = (Element) SProperty;
								StatType = eSProperty.getAttribute("value");
								System.out.println("StatType: " + StatType);
							}
							//Node SProperty = properties.item(q);
							//Element eSProperty = (Element) SProperty;
							//SpawnKey = eSProperty.getAttribute("value");
							//System.out.println("SpawnKey: " + SpawnKey);
						}
						Rectangle r = new Rectangle();
						r.setBounds((int)Math.ceil(Double.parseDouble(eObject.getAttribute("x"))*scale), 
							(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("y"))*scale)), 
							(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("width"))*scale)), 
							(int)(Math.ceil(Double.parseDouble(eObject.getAttribute("height"))*scale)));
										//(int)Math.ceil(16*scale));//(int)Math.ceil(Double.parseDouble(eObject.getAttribute("height"))));
						StatRectMap.put(StatType, r);
						
					}System.out.println("PlayerStats");
					PlayerStats.put(LName, StatRectMap);
					break;
				default: System.out.println("UNRECOGNIZED OBJECT TYPE");			
				}
				//NodeList dataList = lList.
				//Element eLayer = (Element) layer;
			//	NodeList dataList = eLayer.getElementsByTagName("data");
				//Node data = dataList.item(0);
				//Element eData = (Element) data;
				//NodeList tileList = eData.getElementsByTagName("tile");
		
			}
			
			//Loading the objects layer
	
			//BufferedImage combined = new BufferedImage(mapWidth*16, mapHeight*16, BufferedImage.TYPE_INT_ARGB);
			//Graphics2D gc = combined.createGraphics();
			//gc.drawImage(screenBitmapTopLayer, 0, 0, null);
			//gc.drawImage(screenBitmap, 0, 0, null);
			//gc.finalize();
		//	levelMaps.add
	//	File outputfile = new File("image3.png");
		//ImageIO.write(combined, "png", outputfile);
	
			
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
	public static String getExitPath(String mapName, String exitName){
		return ExitPathMap.get(mapName).get(exitName);
	}
	
	public static HashMap<String,String> getExitPaths(String mapName){
		return ExitPathMap.get(mapName);
	}
	
	public static ArrayList<Rectangle> getExitRects(String Name){
		return ExitMap.get(Name);
	}
	
	public static ArrayList<Rectangle> getCollisions(String Name){
		return CollisionMap.get(Name);
	}
	
	public static HashMap<String, Rectangle> getSpawns(String Name){
		return SpawnMap.get(Name);
	}
	
	public static int getMapHeight(){
		return MapHeight;
	}
	
	public static int getMapWidth(){
		return MapWidth;
	}
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