package src.gameobjects;

import java.util.HashMap;

import src.GameObject;
import src.Hitbox;
import src.animation.*;

public abstract class Projectile extends GameObject{
	private boolean removable;
	private int damage;

	public Projectile(double x, double y, int damage, Hitbox hitbox, HashMap<String, Animation> animations){
		super(x, y, hitbox, animations);
		removable = false;
		this.damage = damage;
	}

	public void setRemovable(boolean removable){
		this.removable = removable;
	}

	public boolean isRemovable(){
		return removable;
	}

	public int getDamage(){
		return damage;
	}
}