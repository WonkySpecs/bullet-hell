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
	private double radius;

	public HitboxCircle(double x, double y, double r){
		center = new Point((int)Math.round(x), (int)Math.round(y));
		radius = r;
	}

	public void moveTo(double x, double y){
		center.move((int)Math.round(x), (int)Math.round(y));
	}

	public void moveBy(double x, double y){
		center.move((int)Math.round(center.getX() + x), (int)Math.round(center.getY() + y));
	}

	public Point getCenter(){
		return center;
	}

	public double getRadius(){
		return radius;
	}

	public void setRadius(double r){
		radius = r;
	}

	public void increaseRadius(double change){
		if(radius + change > 0){
			radius += change;
		}
	}
}