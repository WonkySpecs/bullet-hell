/**
* EnemySuicideTracker follows the player until it is shot down,
* hits the palyer, or goes off the screen
*
* @author  Will Taylor
* @version 0.1
* @since   29-07-2017
*/

package src.gameobjects;

import java.util.HashMap;
import java.util.ArrayList;

import src.Hitbox;
import src.animation.*;
import src.ProjectileData;

public class EnemySuicideTracker extends EnemyShip{
	private double speed, turnRate;
	public EnemySuicideTracker(double x, double y, int hp, double speed, double turnRate, Hitbox hitbox, HashMap<String, Animation> animations, ProjectileData projType){
		super(x, y, hp, hitbox, animations, projType);
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
	public ArrayList<Projectile> fire(double playerX, double playerY){
		double dx = playerX - getX();
		double dy = playerY - getY();
		double angle = Math.atan2(dy, dx);

		double targetXvel = speed * Math.cos(angle);
		double targetYvel = speed * Math.sin(angle);

		if(Math.abs(targetXvel - getXvel()) <= turnRate){
			setXvel(targetXvel);
		}
		else{
			if(targetXvel > getXvel()){
				increaseXvel(turnRate);
			}
			else{
				increaseXvel(-turnRate);
			}
		}

		if(Math.abs(targetYvel - getYvel()) <= turnRate){
			setYvel(targetYvel);
		}
		else{
			if(targetYvel > getYvel()){
				increaseYvel(turnRate);
			}
			else{
				increaseYvel(-turnRate);
			}
		}

		return null;
	}
}