/**
* HitboxCircle represents the hitboxes of circular GameObjects
* Each hitbox is represented by it's top left coordinate which
* is tied to the x,y of the object, an offset for the center
* of the circle, and the radius of the circle.
* 
* When constructing a hitbox circle, if the offset is not
* explicitly given it is assumed to be equal to the radius
* of the circle, so the circle is central to the whole object
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

	//If no offset is given, assume that hitbox is centered at (r,r) relative to topleft.
	public HitboxCircle(double x, double y, double r){
		this(x, y, r, r, r);
	}

	//As all hitboxes are updated to move with the GameObject they represent,
	//Starting them at the correct position is actually rarely necessary.
	//Tihs is a convenience constructor for easy definitions.
	public HitboxCircle(double r){
		this(0, 0, r);
	}

	@Override
	public void moveTo(double x, double y){
		topLeft.move((int)Math.round(x), (int)Math.round(y));
	}

	@Override
	public void moveBy(double x, double y){
		topLeft.move((int)Math.round(topLeft.getX() + x), (int)Math.round(topLeft.getY() + y));
	}

	@Override
	public Point getCenter(){
		return new Point((int)Math.round(topLeft.getX() + centerOffsetX), (int)Math.round(topLeft.getY() + centerOffsetY));
	}

	public Point getTopLeft(){
		return topLeft;
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
