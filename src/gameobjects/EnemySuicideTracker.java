/**
* EnemySuicideTracker follows the player until it is shot down,
* hits the player, or goes off the screen
*
* TODO: Currently tracking is thrown off when player passes the
* leftmost point of the tracker at any somewhat high speed due
* to the angle changing from -pi to pi or vice versa.
*
* @author  Will Taylor
* @version 0.1
* @since   29-07-2017
*/

package src.gameobjects;

import java.util.HashMap;
import java.util.ArrayList;

import src.Hitbox;
import src.HitboxCircle;
import src.animation.*;
import src.ProjectileData;

public class EnemySuicideTracker extends EnemyShip{
	private double speed, turnRate;
	public EnemySuicideTracker(double x, double y, int hp, double speed, double turnRate, int score,
								Hitbox hitbox, HashMap<String, Animation> animations, HashMap<String, ProjectileData> projectileDataMap, ItemDrop itemDrop){
		super(x, y, hp, score, hitbox, animations, projectileDataMap, itemDrop);
		this.speed = speed;
		this.turnRate = turnRate;
		setXvel(0);
		setYvel(0);
	}

	//moves down by yvel and right by xvel - these values can be negative to move left/up
	//Once the floater is on screen, it will be removed next time it goes off screen
	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		moveDown();
		moveRight();

		if(!isRemovable() && !isOffScreen(screenWidth, screenHeight)){
			setRemovable(true);
		}
	}

	//The suicide tracker does not fire a projectile, but we use this method to
	//set xvel and yvel based on the players position
	@Override
	public ArrayList<Projectile> fire(HitboxCircle playerHitbox){
		//Gets trackers current direction of travel
		double xv = getXvel();
		double yv = getYvel();
		double curAngle = Math.atan2(yv, xv);
		
		//Gets trackers desired direction of travel - will turn to this direction
		//at a maximum of turnRate radians per frame
		double dx = playerHitbox.getCenter().getX() - getHitbox().getCenter().getX();
		double dy =  playerHitbox.getCenter().getY() - getHitbox().getCenter().getY();
		double targetAngle = Math.atan2(dy, dx);

		double curAngleTransformed = curAngle + Math.PI;
		double targetAngleTransformed = curAngle + Math.PI;

		//When tracker first spawns, head towards player
		if(xv == 0 && yv == 0){
			setXvel(speed * Math.cos(targetAngle));
			setYvel(speed * Math.sin(targetAngle));
			return null;
		}

		double absAngleDiff = Math.abs(targetAngleTransformed - curAngleTransformed);

		if(absAngleDiff <= turnRate){
			setXvel(speed * Math.cos(targetAngle));
			setYvel(speed * Math.sin(targetAngle));
		}
		else{
			double newAngle;

			if(targetAngle > curAngle){
				newAngle = curAngle + turnRate;
			}
			else{
				newAngle = curAngle - turnRate;
			}
			setXvel(speed * Math.cos(newAngle));
			setYvel(speed * Math.sin(newAngle));
		}

		return null;
	}
}
