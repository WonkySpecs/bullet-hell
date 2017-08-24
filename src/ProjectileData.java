package src;

import java.util.HashMap;
import java.awt.geom.*;

import src.gameobjects.*;
import src.animation.Animation;

public class ProjectileData{
	private ProjType projType;
	private double speed, angle;
	private int damage;
	private Particle.ExplosionType explosionType;
	private Particle.ExplosionDirection explosionDirection;
	private Hitbox hitbox;
	private HashMap<String, Animation> animations;

	public static double DIR_UP = -Math.PI / 2;
	public static double DIR_DOWN = Math.PI / 2;

	public enum ProjType{
		STRAIGHT;
	}

	public ProjectileData(ProjType projType, double speed, double angle, int damage, Particle.ExplosionType explosionType, Particle.ExplosionDirection explosionDirection, Hitbox hitbox, HashMap<String, Animation> animations){
		this.projType = projType;
		this.speed = speed;
		this.angle = angle;
		this.damage = damage;
		this.explosionType = explosionType;
		this.explosionDirection = explosionDirection;
		this.hitbox = hitbox;
		this.animations = animations;
	}

	public void setAngle(double angle){
		this.angle = angle;
	}

	public Projectile newProjectile(double x, double y){
		Projectile proj;
		switch(projType){
			case STRAIGHT:
				proj = new ProjectileStraightLine(x, y, damage, explosionType, explosionDirection, speed * Math.cos(angle), speed * Math.sin(angle), hitbox, animations);
				break;

			default:
				//This will should lead to easy to track NullPointerExceptions if the projType argument is not correct.
				proj = null;
		}
		return proj;
	}

	public Projectile newProjectile(Point2D.Double startPoint){
		return newProjectile(startPoint.getX(), startPoint.getY());
	}
}
