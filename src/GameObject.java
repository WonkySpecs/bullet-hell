/**
* GameObject is the abstract base class which is extended
* to represent all of the objects during gameplay -
* this includes the player, enemies, and projectiles
*
* @author  Will Taylor
* @version 0.1
* @since   17-07-2017
*/

package src;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import java.awt.Point;

public abstract class GameObject{
	private int x, y;
	private Hitbox hitbox;
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

	public void move(int x, int y){
		moveRight(x);
		moveDown(y);
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