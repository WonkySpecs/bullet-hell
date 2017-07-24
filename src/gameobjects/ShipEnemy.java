/**
* Abstract superclass for all non-player ships 
*
* @author  Will Taylor
* @version 0.1
* @since   23-07-2017
*/

package src.gameobjects;

import java.awt.image.BufferedImage;
import java.io.File;

public abstract class ShipEnemy extends Ship{
	public int hitPoints;
	public boolean removable;

	public ShipEnemy(int x, int y, int hp, BufferedImage sprite){
		moveTo(x, y);
		removable = false;
		if(sprite != null){
			setSprite(sprite);			
		}
		else{
			loadSpriteFromFile((System.getProperty("user.dir")
									+ File.separator + "bin"
									+ File.separator + "src"
									+ File.separator + "gameobjects"
									+ File.separator + "sprites"
									+ File.separator + "GenericEnemy.png"));
		}
		setHitpoints(hp);
	}

	public void setHitpoints(int hp){
		hitPoints = hp;
	}

	public int getHitPoints(){
		return hitPoints;
	}

	public void reduceHitpoints(int damage){
		hitPoints -= damage;
	}

	public void setRemovable(boolean removable){
		this.removable = removable;
	}

	public boolean isRemovable(){
		return removable;
	}

	public abstract void update();
}