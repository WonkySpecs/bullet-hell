/**
* The Player class is a GameObject which represents the
* players ship.
*
* @author  Will Taylor
* @version 0.1
* @since   18-07-2017
*/


//TODO: Implement upgrades, either directly in player class or as a separate
//class which the Player class will own.
//Many currently hard coded values (speed, weapon details etc) will be encapsulated by upgrades

package src.gameobjects;

import src.*;
import src.animation.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends GameObject{
	private int fireDelay, framesSinceFired, primDamage;
	private double speed;

	public Player(double x, double y, Hitbox hitbox, HashMap<String, Animation> animations){
		super(x, y, hitbox, animations);
		speed = 6.5;
		setXvel(speed);
		setYvel(speed);
		primDamage = 50;

		fireDelay = 8;
		framesSinceFired = fireDelay;
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);

		if(framesSinceFired < fireDelay){
			setXvel(speed * 0.55);
			setYvel(speed * 0.55);
		}
		else{
			setXvel(speed);
			setYvel(speed);
		}

		//Stop player moving off screen
		if(getX() < 0){
			moveRight(-getX());
		}
		if(getX() + getSprite().getWidth() > screenWidth){
			moveRight(screenWidth - (getX() + getSprite().getWidth()));
		}
		if(getY() < 0){
			moveDown(-getY());
		}
		if(getY() + getSprite().getHeight() > screenHeight){
			moveDown(screenHeight - (getY() + getSprite().getHeight()));
		}

		//Note if the player does not fire this can grow very quickly. If this
		//is an issue put a check to stop growth if >fireDelay + a bit
		framesSinceFired++;
	}

	public ArrayList<Projectile> fire(String weapon){
		ArrayList<Projectile> bulletsFired = null;

		if(framesSinceFired > fireDelay){
			bulletsFired = new ArrayList<Projectile>();
			HashMap<String, Animation> projAnimations = AnimationMapFactory.getAnimationMap(AnimationMapFactory.PROJ_PLAYER);
			double projStartX = getX();
			double projStartY = getY() - 1;
			HitboxCircle projHitbox = new HitboxCircle(projStartX, projStartY, 2);
			bulletsFired.add(new ProjectileStraightLine(projStartX, projStartY, primDamage, Particle.ExplosionType.GREEN, 0, -10, projHitbox, projAnimations));

			projStartX = getX() + getSprite().getWidth() - 5;
			projHitbox = new HitboxCircle(projStartX, projStartY, 2);
			bulletsFired.add(new ProjectileStraightLine(projStartX, projStartY, primDamage, Particle.ExplosionType.GREEN, 0, -10, projHitbox, projAnimations));
			framesSinceFired = 0;
		}

		return bulletsFired;
	}
}
