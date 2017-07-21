package src;

import java.awt.image.BufferedImage;
import java.awt.Point;

public class SpriteData{
	private BufferedImage sprite;
	private Point pos;
	private int depth;

	public SpriteData(BufferedImage i, Point p, int d){
		sprite = i;
		pos = p;
		depth = d;
	}

	public BufferedImage getSprite(){
		return sprite;
	}

	public Point getPos(){
		return pos;
	}

	public int getDepth(){
		return depth;
	}

	@Override
	public String toString(){
		return String.format("Image at %f %f at depth %d", getPos().getX(), getPos().getY(), getDepth());
	}
}