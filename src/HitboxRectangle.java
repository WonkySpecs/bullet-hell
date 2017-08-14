/**
* HitboxRectangle represents a rectangular hitbox
* (surprise surprise).
* The rectangle is represented by a Point2D.Double
* for top left corener, and two doubles for width
* and height.
*
* @author  Will Taylor
* @version 0.1
* @since   11-08-2017
*/

package src;

import java.awt.Point;
import java.util.ArrayList;
import java.awt.geom.*;

public class HitboxRectangle extends Hitbox{
	private Point2D.Double topLeft;
	private double width, height;

	public HitboxRectangle(double x, double y, double width, double height){
		topLeft = new Point2D.Double(x, y);
		this.width = width;
		this.height = height;
	}

	public HitboxRectangle(double width, double height){
		this(0, 0, width, height);
	}

	public HitboxRectangle(HitboxRectangle original){
		this(original.getPos().getX(), original.getPos().getY(), original.getWidth(), original.getHeight());
	}

	@Override
	public void moveTo(double x, double y){
		topLeft.setLocation(x, y);
	}

	@Override
	public void moveBy(double x, double y){
		topLeft.setLocation(topLeft.getX() + x, topLeft.getY() + y);
	}

	@Override
	public void moveCenterTo(double x, double y){
		moveTo(x - width / 2, y - height / 2);
	}

	@Override
	public void moveCenterBy(double x, double y){
		moveCenterTo(topLeft.getX() + x, topLeft.getY() + y);
	}

	public double getWidth(){
		return width;
	}

	public double getHeight(){
		return height;
	}

	//TODO: IMPLEMENT this properly
	@Override
	public Point2D.Double getCenter(){
		return new Point2D.Double(topLeft.getX() + width / 2, topLeft.getY() + height / 2);
	}

	@Override
	public Point2D.Double getPos(){
		return topLeft;
	}
	
	public ArrayList<Line2D.Double> getEdges(){
		ArrayList<Line2D.Double> edges = new ArrayList<>();
		//Top left - top right
		edges.add(new Line2D.Double(topLeft.getX(), topLeft.getY(), topLeft.getX() + width, topLeft.getY()));
		//Top right - bottom right
		edges.add(new Line2D.Double(topLeft.getX() + width, topLeft.getY(), topLeft.getX() + width, topLeft.getY() + height));
		//Bottom right - bottom left
		edges.add(new Line2D.Double(topLeft.getX() + width, topLeft.getY() + height, topLeft.getX(), topLeft.getY() + height));
		//Bottom left - top left
		edges.add(new Line2D.Double(topLeft.getX(), topLeft.getY() + height, topLeft.getX(), topLeft.getY()));

		return edges;
	}
}
