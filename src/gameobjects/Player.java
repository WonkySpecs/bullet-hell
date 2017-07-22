/**
* The Player class is a GameObject which represents the
* players ship.
*
* @author  Will Taylor
* @version 0.1
* @since   18-07-2017
*/

package src.gameobjects;

import src.HitboxPolygon;

import java.io.File;
import java.awt.Point;
import java.util.ArrayList;

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

		//TODO: Move hitbox definition to a file
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(0, 0));
		points.add(new Point(50, 0));
		points.add(new Point(50, 50));
		points.add(new Point(0, 50));

		HitboxPolygon hitbox = new HitboxPolygon(x, y, points);
	}
}