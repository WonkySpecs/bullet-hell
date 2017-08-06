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
	private int hitPoints;
	private boolean removable;
	private ProjectileData projData;
	private ItemDrop itemDrop;

	public EnemyShip(double x, double y, int hp, Hitbox hitbox, HashMap<String, Animation> animations, ProjectileData projData, ItemDrop itemDrop){
		super(x, y, hitbox, animations);
		moveTo(x, y);
		removable = false;
		setHitpoints(hp);
		this.projData = projData;
		this.itemDrop = itemDrop;
	}

	public EnemyShip(double x, double y, int hp, Hitbox hitbox, HashMap<String, Animation> animations, ProjectileData projData){
		this(x, y, hp, hitbox, animations, projData, null);
	}

	public EnemyShip(double x, double y, int hp, Hitbox hitbox, HashMap<String, Animation> animations, ItemDrop itemDrop){
		this(x, y, hp, hitbox, animations, null, itemDrop);
	}

	public EnemyShip(double x, double y, int hp, Hitbox hitbox, HashMap<String, Animation> animations){
		this(x, y, hp, hitbox, animations, null, null);
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

	public ItemDrop getItemDrop(){
		return itemDrop;
	}

	public abstract ArrayList<Projectile> fire(HitboxCircle playerHitbox);
}
