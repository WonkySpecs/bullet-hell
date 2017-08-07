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

import java.awt.geom.*;

public class HitboxCircle extends Hitbox{
	private Point2D.Double topLeft;
	private double radius, centerOffsetX, centerOffsetY;

	public HitboxCircle(double x, double y, double r, double offX, double offY){
		topLeft = new Point2D.Double(x, y);
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
	//This is a convenience constructor for easy definitions.
	public HitboxCircle(double r){
		this(0, 0, r);
	}

	public HitboxCircle(HitboxCircle original){
		this(original.getPos().getX(), original.getPos().getY(), original.getRadius(), original.getCenterOffsetX(), original.getCenterOffsetY());
	}

	@Override
	public void moveTo(double x, double y){
		topLeft.setLocation(x, y);
	}

	public void moveTo(Point2D.Double p){
		moveTo(p.getX(), p.getY());
	}

	@Override
	public void moveBy(double x, double y){
		moveTo((double)topLeft.getX() + x, (double)topLeft.getY() + y);
	}

	@Override
	public void moveCenterTo(double x, double y){
		moveTo(x - radius - centerOffsetX, y - radius - centerOffsetY);
	}

	public void moveCenterTo(Point2D.Double p){
		moveCenterTo(p.getX(), p.getY());
	}

	@Override
	public void moveCenterBy(double x, double y){
		moveTo(x - radius - centerOffsetX, y - radius - centerOffsetY);
	}

	@Override
	public Point2D.Double getCenter(){
		return new Point2D.Double(topLeft.getX() + centerOffsetX, topLeft.getY() + centerOffsetY);
	}

	public Point2D.Double getPos(){
		return topLeft;
	}

	public double getCenterOffsetX(){
		return centerOffsetX;
	}

	public double getCenterOffsetY(){
		return centerOffsetY;
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
