/**
* Hitbox is the abstract superclass for all shapes of hitbox
* (currently Circle and Polygon).
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

import java.awt.Point;
import java.awt.geom.*;
import java.util.ArrayList;

public abstract class Hitbox{
	public abstract void moveBy(double x, double y);
	public abstract void moveTo(double x, double y);
	public abstract void moveCenterBy(double x, double y);
	public abstract void moveCenterTo(double x, double y);
	public abstract Point getPos();
	public abstract Point getCenter();

	//Hit detection functions.
	//TODO: Look up the proper way to handle this -
	//casting to more specific subclass implicitly
	public static boolean collisionBetween(Hitbox h1, Hitbox h2){
		try{
			return collisionBetween((HitboxCircle) h1, (HitboxCircle) h2);
		}
		catch(Exception e){}

		try{
			return collisionBetween((HitboxPolygon) h1, (HitboxCircle) h2);
		}
		catch(Exception e){}

		try{
			return collisionBetween((HitboxCircle) h1, (HitboxPolygon) h2);
		}
		catch(Exception e){}

		try{
			return collisionBetween((HitboxPolygon) h1, (HitboxPolygon) h2);
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

	public static boolean collisionBetween(HitboxCircle h1, HitboxPolygon h2){
		return true;
	}

	public static boolean collisionBetween(HitboxPolygon h1, HitboxCircle h2){
		return collisionBetween(h2, h1);
	}

	//Brute force checks for intersection between every edge of each polygon
	//TODO: If performance is an issue, find a way to optimise this
	public static boolean collisionBetween(HitboxPolygon h1, HitboxPolygon h2){
		ArrayList<Line2D.Float> edgeList1 = h1.getPolygonEdgesGlobalSpace();
		ArrayList<Line2D.Float> edgeList2 = h2.getPolygonEdgesGlobalSpace();

		for(Line2D.Float edge1 : edgeList1){
			for(Line2D.Float edge2 : edgeList2){
				if(edge1.intersectsLine(edge2)){
					return true;
				}
			}
		}

		return false;
	}

	public static Hitbox copy(Hitbox original){
		try{
			HitboxCircle copy = new HitboxCircle((HitboxCircle)original);
			return copy;
		}
		catch(Exception e){}
		
		try{
			HitboxPolygon copy = new HitboxPolygon((HitboxPolygon)original);
			return copy;
		}
		catch(Exception e){}

		return null;
	}
}
