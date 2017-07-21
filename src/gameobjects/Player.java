/**
* The Player class is a GameObject which represents the
* players ship.
*
* @author  Will Taylor
* @version 0.1
* @since   18-07-2017
*/


package src.gameobjects;

import java.io.File;

public class Player extends Ship{
	public Player(int x, int y){
		//This is 99.9% incorrect and overly localised, TODO: fix
		loadSpriteFromFile(System.getProperty("user.dir")
			+ File.separator + "bin"
			+ File.separator + "src"
			+ File.separator + "gameobjects"
			+ File.separator + "sprites"
			+ File.separator + "player.png");
		
		moveTo(x, y);
	}
}