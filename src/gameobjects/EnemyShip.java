/**
* Abstract superclass for all non-player ships 
*
* @author  Will Taylor
* @version 0.1
* @since   23-07-2017
*/

package src.gameobjects;

import java.util.HashMap;
import java.io.File;

import src.GameObject;
import src.animation.*;

public abstract class EnemyShip extends GameObject{
	private int hitPoints;
	private boolean removable;

	public EnemyShip(int x, int y, int screenWidth, int screenHeight, int hp, HashMap<String, Animation> animations){
		super(x, y, screenWidth, screenHeight, animations);
		moveTo(x, y);
		removable = false;
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
}