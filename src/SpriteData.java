/**
* SpriteData is a data type class used for passing sprites,
* their positions and their depths from GamePlayLogic to GamePlay
* 
*
* @author  Will Taylor
* @version 0.1
* @since   20-07-2017
*/


package src;

import java.awt.image.BufferedImage;
import java.awt.geom.*;

public class SpriteData{
	private BufferedImage sprite;
	private Point2D.Double pos;
	private int depth;
	private float alpha;

	public SpriteData(BufferedImage i, Point2D.Double p, int d, float a){
		sprite = i;
		pos = p;
		depth = d;
		alpha = a;
	}

	public SpriteData(BufferedImage i, Point2D.Double p, int d){
		this(i, p, d, (float)1.0);
	}

	public BufferedImage getSprite(){
		return sprite;
	}

	public Point2D.Double getPos(){
		return pos;
	}

	public int getDepth(){
		return depth;
	}

	public float getAlpha(){
		return alpha;
	}

	@Override
	public String toString(){
		return String.format("Image at %f %f at depth %d, alpha %f", getPos().getX(), getPos().getY(), getDepth(), getAlpha());
	}
}
