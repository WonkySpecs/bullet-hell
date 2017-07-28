/**
* The most basic kind of enemy, EnemyStraightFloater.
* moves at a fixed velocity until it is shot down, hits
* the player or leaves the screen.
* 
*
*
* @author  Will Taylor
* @version 0.1
* @since   23-07-2017
*/

package src.gameobjects;

import java.util.HashMap;
import java.util.ArrayList;

import src.Hitbox;
import src.animation.*;
import src.ProjectileData;

public class EnemyStraightFloaterShooter extends EnemyStraightFloater{
	private int fireDelay, framesSinceFired, projXVel, projYVel;

	public EnemyStraightFloaterShooter(int x, int y, int hp, int xvel, int yvel,
									   Hitbox hitbox, HashMap<String, Animation> animations,
									   ProjectileData projType, int fireDelay){
		super(x, y, hp, xvel, yvel, hitbox, animations, projType);
		this.fireDelay = fireDelay;
		this.projXVel = projXVel;
		this.projYVel = projYVel;
		framesSinceFired = 0;
	}

	//moves down by yvel and right by xvel - these values can be negative to move left/up
	//Once the floater is on screen, it will be removed next time it goes off screen
	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		framesSinceFired++;
	}

	@Override
	public ArrayList<Projectile> fire(int playerX, int playerY){
		if(framesSinceFired > fireDelay){
			ArrayList<Projectile> projectiles = new ArrayList<>();
			projectiles.add(getProjData().newProjectile(getX() + (int)(getSprite().getWidth()/2), getY() + getSprite().getHeight() - 2));
			framesSinceFired = 0;

			return projectiles;
		}
		else{
			return null;
		}
	}
}