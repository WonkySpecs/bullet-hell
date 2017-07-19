package src;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public abstract class GameObject{
	private int x, y;
	//some hitbox thing
	private BufferedImage objectSprite;

	public void moveTo(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void moveRight(int dist){
		this.x += dist;
	}

	public void moveUp(int dist){
		this.y -= dist;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
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