package src;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import java.awt.Point;

public abstract class GameObject{
	private int x, y;
	//some hitbox thing
	private BufferedImage objectSprite;

	public void moveTo(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void moveRight(int dist){
		x += dist;
	}

	public void moveDown(int dist){
		y += dist;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public Point getPos(){
		return new Point(x, y);
	}

	public void loadSpriteFromFile(String filename){
		try{
			objectSprite = ImageIO.read(new File(filename));
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(String.format("Failed to load sprite from file %s", filename));
		}
	}

	public BufferedImage getSprite(){
		return objectSprite;
	}
}