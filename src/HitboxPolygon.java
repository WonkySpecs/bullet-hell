/**
* HitboxPolygon represents the hitboxes of non-circular GameObjects
* by an ordered ArrayList of Points.
* Each hitbox works on a kind of 'local' coordinate system where
* all points are relative to the top left point2D.Double of the objects
* sprite.
*
* @author  Will Taylor
* @version 0.1
* @since   21-07-2017
*/

package src;

import java.awt.Point;
import java.util.ArrayList;
import java.awt.geom.*;

public class HitboxPolygon extends Hitbox{
	private ArrayList<Point2D.Double> pointList;
	private Point2D.Double origin;

	public HitboxPolygon(double x, double y, ArrayList<Point2D.Double> points){
		origin = new Point2D.Double(x, y);
		pointList = points;
	}

	public HitboxPolygon(HitboxPolygon original){
		this(original.getPos().getX(), original.getPos().getY(), original.getPointList());
	}

	@Override
	public void moveTo(double x, double y){
		origin.setLocation(x, y);
	}

	@Override
	public void moveBy(double x, double y){
		origin.setLocation(origin.getX() + x, origin.getY() + y);
	}

	@Override
	public void moveCenterTo(double x, double y){
		
	}

	@Override
	public void moveCenterBy(double x, double y){
		
	}

	//TODO: IMPLEMENT this properly
	@Override
	public Point2D.Double getCenter(){
		return origin;
	}

	@Override
	public Point2D.Double getPos(){
		return origin;
	}

	public ArrayList<Point2D.Double> getPointList(){
		return pointList;
	}

	//If not working, may be i<pointSize() - 1 instead
	public ArrayList<Line2D.Float> getPolygonEdges(){
		ArrayList<Line2D.Float> edges = new ArrayList<>();
		for(int i = 0; i < pointList.size(); i++){
			edges.add(new Line2D.Float(pointList.get(i), pointList.get(i + 1)));
		}

		return edges;
	}

	//All points in pointList are relative to origin.
	//getPolygonEdgesGlobalSpace translates the poitns to the
	//global coordinate space before calculating the edges.
	public ArrayList<Line2D.Float> getPolygonEdgesGlobalSpace(){
		ArrayList<Line2D.Float> localEdges = getPolygonEdges();

		for(Line2D.Float edge : localEdges){

			edge.setLine(edge.getX1() + origin.getX(),
						 edge.getX2() + origin.getX(),
						 edge.getY1() + origin.getY(),
						 edge.getY2() + origin.getY());
		}

		return localEdges;
	}

	public Point2D.Double getorigin(){
		return origin;
	}
}
