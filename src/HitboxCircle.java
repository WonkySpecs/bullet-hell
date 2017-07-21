package src;

import java.awt.Point;

public class HitboxCircle implements Hitbox{
	private Point center;
	private int radius;

	public HitboxCircle(int x, int y, int r){
		center = new Point(x, y);
		radius = r;
	}

	public void move(int x, int y){
		center.move(x, y);
	}

	public Point getCenter(){
		return center;
	}

	public int getRadius(){
		return radius;
	}

	public void setRadius(int r){
		radius = r;
	}

	public void increaseRadius(int change){
		if(radius + change > 0){
			radius += change;
		}
	}
}