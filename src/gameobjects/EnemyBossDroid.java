/**
* EnemyBossDroid is the boss used in the examle level.
* It is an example of a subclass for EnemyBossLateralPatroller
* which defines a way of shooting, and a frenzy state at a
* certain hp level.
*
* @author  Will Taylor
* @version 0.1
* @since   24-08-2017
*/

package src.gameobjects;

import java.awt.geom.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.geom.*;

import src.animation.*;
import src.*;

public class EnemyBossDroid extends EnemyBossLateralPatroller{
	private int orignalHp;
	private boolean frenzied;
	private int targetShotDelay, timeSinceTargetShot, mainShotDelay, timeSinceMainShot;

	public EnemyBossDroid(EnemyBoss.StartSide startSide, Point2D.Double startPoint,
							double leftEnd, double rightEnd, double y, Double speed,
							int hp, int score,				
							Hitbox hitbox, HashMap<String, Animation> animations, HashMap<String, ProjectileData> projectileDataMap){
		super(startSide, startPoint,
				leftEnd, rightEnd, y, speed,
				hp, score,
				hitbox, animations, projectileDataMap);
		orignalHp = hp;
		frenzied = false;
		mainShotDelay = 30;
		timeSinceMainShot = 0;
		targetShotDelay = 20;
		timeSinceTargetShot = 0;
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		if(isStarted()){
			timeSinceTargetShot += 1;
			timeSinceMainShot += 1;
		}

		if(getHitPoints() < orignalHp / 2.0 && !frenzied){
			frenzied = true;
			setCurAnimation("frenzy");
			setSpeed(getSpeed() * 2.2);
			targetShotDelay = 10;
		}
	}

	@Override
	public ArrayList<Projectile> fire(HitboxCircle playerHitbox){
		ArrayList<Projectile> newProjectiles = new ArrayList<>();
		if(timeSinceTargetShot >= targetShotDelay){
			Point2D.Double targetShotStartPoint = new Point2D.Double(getX() + getSprite().getWidth() / 2, getY() + getSprite().getHeight() - 20);
			double dx = playerHitbox.getCenter().getX() - targetShotStartPoint.getX();
			double dy = playerHitbox.getCenter().getY() - targetShotStartPoint.getY();
			getProjectileData("targetted").setAngle(Math.atan2(dy, dx));
			newProjectiles.add(getProjectileData("targetted").newProjectile(targetShotStartPoint));
			timeSinceTargetShot = 0;
		}

		if(timeSinceMainShot >= mainShotDelay){
			newProjectiles.add(getProjectileData("main").newProjectile(getX() + 20, getY() + getSprite().getHeight() - 20));
			newProjectiles.add(getProjectileData("main").newProjectile(getX() + getSprite().getWidth() - 20, getY() + getSprite().getHeight() - 20));
			timeSinceTargetShot = 0;
			timeSinceMainShot = 0;
		}

		if(!frenzied){

		}
		else{

		}
		return newProjectiles;
	}
}