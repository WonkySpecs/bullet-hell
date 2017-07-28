/**
* Abstract superclass for all non-player ships 
*
* @author  Will Taylor
* @version 0.1
* @since   23-07-2017
*/

package src.gameobjects;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;

import src.*;
import src.animation.*;

public abstract class EnemyShip extends GameObject{
	private int hitPoints;
	private boolean removable;
	private ProjectileData projData;

	public EnemyShip(int x, int y, int hp, Hitbox hitbox, HashMap<String, Animation> animations, ProjectileData projData){
		super(x, y, hitbox, animations);
		moveTo(x, y);
		removable = false;
		setHitpoints(hp);
		this.projData = projData;
	}

	public void setHitpoints(int hp){
		hitPoints = hp;
	}

	public int getHitPoints(){
		return hitPoints;
	}

	public void reduceHitpoints(int damage){
		//TODO: Implement taking damge animations
		hitPoints -= damage;
	}

	public void setRemovable(boolean removable){
		this.removable = removable;
	}

	public boolean isRemovable(){
		return removable;
	}

	public ProjectileData getProjData(){
		return projData;
	}

	public abstract ArrayList<Projectile> fire(int playerX, int playerY);
}