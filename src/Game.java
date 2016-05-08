
public class Game {

	public static void main(String[] args) {
		
		Graphics2DDrawImage.main(null);

	}

}



/*

Graphic will be set up like this:

the base gamestate will load all the tilemaps, then the current gamestate will inherit the tilemaps and load the proper one to be rendered



need to have a function to load all the xml files at runtime and then create a method that renders each gamestate.  The gamestate.render() function will pass its map and tilemap 
(or maybe not tilemap, if it is already inherited by the graphics class and is constant for every class)


*/

