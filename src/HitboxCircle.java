/**
* HitboxCircle represents the hitboxes of circular GameObjects
* by a central Point and a radius.
* Each hitbox works on a kind of 'local' coordinate system where
* all points are relative to the top left point of the objects
* sprite.
*
* @author  Will Taylor
* @version 0.1
* @since   21-07-2017
*/


package src;

import java.awt.Point;

public class HitboxCircle extends Hitbox{
	private Point center;
	private int radius;

	public HitboxCircle(int x, int y, int r){
		center = new Point(x, y);
		radius = r;
	}

	public void moveTo(int x, int y){
		center.move(x, y);
	}

	public void moveBy(int x, int y){
		center.move((int)center.getX() + x, (int)center.getY() + y);
	}

	public Point getCenter(){
		return center;
	}

	public int getRadius(){
		return radius;
	}

	public void setRadius(int r){
		radius = r;
	}

	public void increaseRadius(int change){
		if(radius + change > 0){
			radius += change;
		}
	}
}