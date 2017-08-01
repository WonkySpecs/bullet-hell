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
	private Point topLeft;
	private double radius, centerOffsetX, centerOffsetY;

	public HitboxCircle(double x, double y, double r, double offX, double offY){
		topLeft = new Point((int)Math.round(x), (int)Math.round(y));
		radius = r;
		centerOffsetX = offX;
		centerOffsetY = offY;
	}

	public HitboxCircle(double x, double y, double r){
		this(x, y, r, r, r);
	}

	public void moveTo(double x, double y){
		topLeft.move((int)Math.round(x), (int)Math.round(y));
	}

	public void moveBy(double x, double y){
		topLeft.move((int)Math.round(topLeft.getX() + x), (int)Math.round(topLeft.getY() + y));
	}

	public Point getTopLeft(){
		return topLeft;
	}

	public Point getCenter(){
		return new Point((int)Math.round(topLeft.getX() + centerOffsetX), (int)Math.round(topLeft.getY() + centerOffsetY));
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