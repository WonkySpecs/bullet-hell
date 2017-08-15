package src.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;

import src.*;
import src.animation.Animation;

public class EnemyBomb extends EnemyStraightFloater{
	private int explosionDelay, timer, numProjectiles;
	private boolean exploded;

	public EnemyBomb(double x, double y, int hp, double xvel, double yvel, int score,
						int explosionDelay, int numProjectiles,
						Hitbox hitbox,
						HashMap<String, Animation> animations,
						ProjectileData projType){
		super(x, y, hp, xvel, yvel, score, hitbox, animations, projType, null);
		this.explosionDelay = explosionDelay;
		this. numProjectiles = numProjectiles;
		timer = 0;
		exploded = false;
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		timer += 1;

		if((double)timer / explosionDelay > 0.5){
			setCurAnimation("frenzy");
		}
	}

	@Override
	public ArrayList<Projectile> fire(HitboxCircle playerHitbox){
		if(exploded || timer < explosionDelay){
			return null;
		}
		else{
			//Explode
			setXvel(0);
			setYvel(0);
			setImmediatelyRemovable(true);
			exploded = true;

			ArrayList<Projectile> explosion = new ArrayList<>();
			double curAngle = -Math.PI;
			getProjectileData().setAngle(curAngle);

			for(int i = 0; i < numProjectiles; i++){
				explosion.add(getProjectileData().newProjectile(getX(), getY()));
				curAngle += (2 * Math.PI) / numProjectiles;
				getProjectileData().setAngle(curAngle);				
			}
			return explosion;
		}
	}
}