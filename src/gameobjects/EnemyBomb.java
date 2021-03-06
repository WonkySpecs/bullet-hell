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
						HashMap<String, ProjectileData> projectileDataMap){
		super(x, y, hp, xvel, yvel, score, hitbox, animations, projectileDataMap, null);
		this.explosionDelay = explosionDelay;
		this. numProjectiles = numProjectiles;
		timer = 0;
		exploded = false;
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		timer += 1;

		if(timer > explosionDelay / 2.0 && getCurAnimationName() == "neutral"){
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
			getProjectileData("main").setAngle(curAngle);

			for(int i = 0; i < numProjectiles; i++){
				explosion.add(getProjectileData("main").newProjectile(getX(), getY()));
				curAngle += (2 * Math.PI) / numProjectiles;
				getProjectileData("main").setAngle(curAngle);				
			}
			return explosion;
		}
	}
}