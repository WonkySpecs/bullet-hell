/**
* The Player class is a GameObject which represents the
* players ship.
*
* @author  Will Taylor
* @version 0.1
* @since   18-07-2017
*/

package src.gameobjects;

import src.GameObject;
import src.HitboxPolygon;
import src.animation.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends GameObject{
	public Player(int x, int y, int screenWidth, int screenHeight, HashMap<String, Animation> animations){
		super(x, y, screenWidth, screenHeight, animations);
		setXvel(5);
		setYvel(5);
		setCurAnimation("neutral");
		getCurAnimation().start();

		//TODO: Move hitbox definition to a file
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(0, 0));
		points.add(new Point(50, 0));
		points.add(new Point(50, 50));
		points.add(new Point(0, 50));

		HitboxPolygon hitbox = new HitboxPolygon(x, y, points);
	}

	public void update(){
		getCurAnimation().update();
	}

	public void fire(String weapon){
		System.out.println(weapon);
	}
}