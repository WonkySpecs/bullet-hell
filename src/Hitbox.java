package src;

import java.awt.Point;
import java.awt.geom.*;
import java.util.ArrayList;

public abstract class Hitbox{
	public abstract void move(int x, int y);

	//Hit detection functions. May move these to a separate class/into the hitbox classes themselves
	public boolean collisionBetween(HitboxCircle h1, HitboxCircle h2){
		double dist = h1.getCenter().distance(h2.getCenter());

		if(dist <= h1.getRadius() + h2.getRadius()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean collisionBetween(HitboxCircle h1, HitboxPolygon h2){
		return true;
	}

	public boolean collisionBetween(HitboxPolygon h1, HitboxCircle h2){
		return collisionBetween(h2, h1);
	}

	//Brute force checks for intersection between every edge of each polygon
	//TODO: If performance is an issue, find a way to optimise this
	public boolean collisionBetween(HitboxPolygon h1, HitboxPolygon h2){
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
}