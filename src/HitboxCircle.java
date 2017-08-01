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
	private Point topLeft, center;
	private double radius;

	public HitboxCircle(double x, double y, double r){
		topLeft = new Point((int)Math.round(x), (int)Math.round(y));
		center = new Point((int)(topLeft.getX() + Math.round(r)), (int)(topLeft.getY() + Math.round(r)));

		radius = r;
	}

	public void moveTo(double x, double y){
		topLeft.move((int)Math.round(x), (int)Math.round(y));
		center.move((int)topLeft.getX() + Math.round(radius), (int)topLeft.getY() + Math.round(radius));
	}

	public void moveBy(double x, double y){
		topLeft.move((int)Math.round(center.getX() + x), (int)Math.round(center.getY() + y));
		center.move((int)Math.round(center.getX() + x), (int)Math.round(center.getY() + y));
	}

	public Point getTopLeft(){
		return topLeft;
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