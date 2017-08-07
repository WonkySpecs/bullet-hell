package src;

import java.util.HashMap;

import src.gameobjects.*;
import src.animation.Animation;

public class ProjectileData{
	private String projType;
	private double xVel, yVel;
	private int damage;
	private Particle.ExplosionType explosionType;
	private Hitbox hitbox;
	private HashMap<String, Animation> animations;

	public static final String PROJ_STRAIGHT = "proj_straight";

	public ProjectileData(String projType, double xVel, double yVel, int damage, Particle.ExplosionType explosionType, Hitbox hitbox, HashMap<String, Animation> animations){
		this.projType = projType;
		this.xVel = xVel;
		this.yVel = yVel;
		this.damage = damage;
		this.explosionType = explosionType;
		this.hitbox = hitbox;
		this.animations = animations;
	}

	public Projectile newProjectile(double x, double y){
		Projectile proj;
		switch(projType){
			case PROJ_STRAIGHT:
				proj = new ProjectileStraightLine(x, y, damage, explosionType, xVel, yVel, hitbox, animations);
				break;

			default:
				//This will should lead to easy to track NullPointerExceptions if the projType argument is not correct.
				proj = null;
		}
		return proj;
	}
}
