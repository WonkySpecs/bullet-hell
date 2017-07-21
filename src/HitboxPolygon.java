/**
* HitboxPolygon represents the hitboxes of non-circular GameObjects
* by an ordered ArrayList of Points.
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
import java.util.ArrayList;

public class HitboxPolygon implements Hitbox{
	public ArrayList<Point> pointList;

	public HitboxPolygon(ArrayList<Point> points){
		pointList = points;
	}

	public void move(int x, int y){

	}

	public void 
}