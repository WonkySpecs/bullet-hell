package src.gameobjects;

import java.util.HashMap;

import src.GameObject;
import src.Hitbox;
import src.animation.*;

public abstract class Projectile extends GameObject{
	private boolean removable;
	private int damage;
	private Particle.ExplosionType explosionType;

	public Projectile(double x, double y, int damage, Particle.ExplosionType explosionType, Hitbox hitbox, HashMap<String, Animation> animations){
		super(x, y, hitbox, animations);
		removable = false;
		this.damage = damage;
		this.explosionType = explosionType;
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

	public Particle.ExplosionType getExplosionType(){
		return explosionType;
	}
}
