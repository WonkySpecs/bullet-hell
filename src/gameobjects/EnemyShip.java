/**
* Abstract superclass for all non-player ships.
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
	private int hitPoints, damageTimer, score;
	private boolean removable, immediatelyRemovable;
	private ProjectileData projectileData;
	private ItemDrop itemDrop;

	public EnemyShip(double x, double y, int hp, int score, Hitbox hitbox, HashMap<String, Animation> animations, ProjectileData projectileData, ItemDrop itemDrop){
		super(x, y, hitbox, animations);
		moveTo(x, y);
		removable = false;
		immediatelyRemovable = false;
		damageTimer = -1;
		setHitpoints(hp);
		this.score = score;
		this.projectileData = projectileData;
		this.itemDrop = itemDrop;
	}

	public EnemyShip(double x, double y, int hp, int score, Hitbox hitbox, HashMap<String, Animation> animations, ProjectileData projectileData){
		this(x, y, hp, score, hitbox, animations, projectileData, null);
	}

	public EnemyShip(double x, double y, int hp, int score, Hitbox hitbox, HashMap<String, Animation> animations, ItemDrop itemDrop){
		this(x, y, hp, score, hitbox, animations, null, itemDrop);
	}

	public EnemyShip(double x, double y, int hp, int score, Hitbox hitbox, HashMap<String, Animation> animations){
		this(x, y, hp, score, hitbox, animations, null, null);
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
		if(hasAnimation("damaged")){
			setCurAnimation("damaged");			
		}
		else{
			//Do something?
		}
		damageTimer = 20;
	}

	public int getScore(){
		return score;
	}

	public void setRemovable(boolean removable){
		this.removable = removable;
	}

	public boolean isRemovable(){
		return removable;
	}

	public void setImmediatelyRemovable(boolean b){
		immediatelyRemovable = b;
	}

	public boolean isImmediatelyRemovable(){
		return immediatelyRemovable;
	}

	public ProjectileData getProjectileData(){
		return projectileData;
	}

	public ItemDrop getItemDrop(){
		return itemDrop;
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		if(damageTimer >= 0){
			damageTimer--;
		}
		if(damageTimer == 0){
			setCurAnimation("neutral");
		}
	}

	public abstract ArrayList<Projectile> fire(HitboxCircle playerHitbox);
}
