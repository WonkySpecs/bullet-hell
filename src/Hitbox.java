/**
* Hitbox is the abstract superclass for all shapes of hitbox
* (currently Circle and Rectangle).
* 
* Also defined are the static methods for detecting collision
* between two hitboxes
* 
*
* @author  Will Taylor
* @version 0.1
* @since   21-07-2017
*/


package src;

import java.awt.geom.*;
import java.util.ArrayList;

public abstract class Hitbox{
	public abstract void moveBy(double x, double y);
	public abstract void moveTo(double x, double y);
	public abstract void moveCenterBy(double x, double y);
	public abstract void moveCenterTo(double x, double y);
	public abstract Point2D.Double getPos();
	public abstract Point2D.Double getCenter();

	//Hit detection functions.
	//TODO: Look up the proper way to handle this -
	//casting to more specific subclass implicitly
	public static boolean collisionBetween(Hitbox h1, Hitbox h2){
		try{
			return collisionBetween((HitboxCircle) h1, (HitboxCircle) h2);
		}
		catch(Exception e){}

		try{
			return collisionBetween((HitboxRectangle) h1, (HitboxCircle) h2);
		}
		catch(Exception e){}

		try{
			return collisionBetween((HitboxCircle) h1, (HitboxRectangle) h2);
		}
		catch(Exception e){}

		try{
			return collisionBetween((HitboxRectangle) h1, (HitboxRectangle) h2);
		}
		catch(Exception e){}

		return false;
	}

	public static boolean collisionBetween(HitboxCircle h1, HitboxCircle h2){
		double dist = h1.getCenter().distance(h2.getCenter());

		if(dist <= h1.getRadius() + h2.getRadius()){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean collisionBetween(HitboxCircle h1, HitboxRectangle h2){
		return true;
	}

	public static boolean collisionBetween(HitboxRectangle h1, HitboxCircle h2){
		return collisionBetween(h2, h1);
	}

	public static boolean collisionBetween(HitboxRectangle h1, HitboxRectangle h2){
		Point2D.Double pos1 = h1.getPos();
		Point2D.Double pos2 = h2.getPos();
		double width1 = h1.getWidth();
		double width2 = h2.getWidth();
		double height1 = h1.getHeight();
		double height2 = h2.getHeight();

		boolean withinX = false;
		boolean withinY = false;

		//Check if boxes overlap in y direction
		if(pos1.getY() <  pos2.getY()){
			if(pos2.getY() <= pos1.getY() + height1){
				withinY = true;
			}
		}
		else{
			if(pos1.getY() <= pos2.getY() + height2){
				withinY = true;
			}
		}

		//Check if boxes overlap in x direction
		if(pos1.getX() <  pos2.getX()){
			if(pos2.getX() <= pos1.getX() + width1){
				withinX = true;
			}
		}
		else{
			if(pos1.getX() <= pos2.getX() + width2){
				withinX = true;
			}
		}

		if(withinX && withinY){
			return true;
		}
		else{
			return false;			
		}
	}

	public static Hitbox copy(Hitbox original){
		try{
			HitboxCircle copy = new HitboxCircle((HitboxCircle)original);
			return copy;
		}
		catch(Exception e){}
		
		try{
			HitboxRectangle copy = new HitboxRectangle((HitboxRectangle)original);
			return copy;
		}
		catch(Exception e){}

		return null;
	}
}
