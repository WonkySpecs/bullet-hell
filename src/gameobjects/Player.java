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
	private int fireDelay, framesSinceFired;

	public Player(int x, int y, HashMap<String, Animation> animations){
		super(x, y, animations);
		setXvel(5);
		setYvel(5);

		fireDelay = 10;
		framesSinceFired = fireDelay;

		//TODO: Move hitbox definition to a file
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(0, 0));
		points.add(new Point(50, 0));
		points.add(new Point(50, 50));
		points.add(new Point(0, 50));

		HitboxPolygon hitbox = new HitboxPolygon(x, y, points);
	}

	public void update(int screenWidth, int screenHeight){
		getCurAnimation().update();

		//Note if the player does not fire this can grow very quickly. If this
		//is an issue put a check to stop growth if >fireDelay + a bit
		framesSinceFired++;
	}

	public ArrayList<Projectile> fire(String weapon){
		ArrayList<Projectile> bulletsFired = null;

		if(framesSinceFired > fireDelay){
			bulletsFired = new ArrayList<Projectile>();
			HashMap<String, Animation> projAnimations = AnimationMapFactory.getAnimationMap("playerprojectile");
			bulletsFired.add(new ProjectileStraightLine(getX(), getY() - 1, 0, -7, projAnimations));
			bulletsFired.add(new ProjectileStraightLine(getX() + getSprite().getWidth() - 5, getY() - 1, 0, -7, projAnimations));
			framesSinceFired = 0;
		}

		return bulletsFired;
	}
}