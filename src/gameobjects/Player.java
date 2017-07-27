/**
* The Player class is a GameObject which represents the
* players ship.
*
* @author  Will Taylor
* @version 0.1
* @since   18-07-2017
*/

package src.gameobjects;

import src.*;
import src.animation.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends GameObject{
	private int fireDelay, framesSinceFired;

	public Player(int x, int y, Hitbox hitbox, HashMap<String, Animation> animations){
		super(x, y, hitbox, animations);
		setXvel(5);
		setYvel(5);

		fireDelay = 10;
		framesSinceFired = fireDelay;
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);

		//Note if the player does not fire this can grow very quickly. If this
		//is an issue put a check to stop growth if >fireDelay + a bit
		framesSinceFired++;
	}

	public ArrayList<Projectile> fire(String weapon){
		ArrayList<Projectile> bulletsFired = null;

		if(framesSinceFired > fireDelay){
			bulletsFired = new ArrayList<Projectile>();
			HashMap<String, Animation> projAnimations = AnimationMapFactory.getAnimationMap("playerprojectile");
			int projStartX = getX();
			int projStartY = getY() - 1;
			HitboxCircle projHitbox = new HitboxCircle(projStartX, projStartY, 2);
			bulletsFired.add(new ProjectileStraightLine(projStartX, projStartY, 0, -7, projHitbox, projAnimations));

			projStartX = getX() + getSprite().getWidth() - 5;
			projHitbox = new HitboxCircle(projStartX, projStartY, 2);
			bulletsFired.add(new ProjectileStraightLine(projStartX, projStartY, 0, -7, projHitbox, projAnimations));
			framesSinceFired = 0;
		}

		return bulletsFired;
	}
}