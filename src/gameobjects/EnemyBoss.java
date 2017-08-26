/**
* EnemyBoss is the abstract base class for all of the
* boss enemies in the game. It defines the starting
* movement (sliding into the screen from some side).
*
* @author  Will Taylor
* @version 0.1
* @since   24-08-2017
*/

package src.gameobjects;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.geom.*;

import src.*;
import src.animation.*;

//In future, will likely create an EnemyBoss super class once
//it's clear what functionality needs to be shared between bosses
public abstract class EnemyBoss extends EnemyShip{
	private Point2D.Double startPoint;
	private boolean started;

	public enum StartSide{
		TOP, BOTTOM, RIGHT, LEFT;
	}

	public EnemyBoss(StartSide startSide, Point2D.Double startPoint, int hp, int score,
						Hitbox hitbox, HashMap<String, Animation> animations, HashMap<String, ProjectileData> projectileDataMap){
		super(0, 0, hp, score, hitbox, animations, projectileDataMap, null);
		started = false;
		this.startPoint = startPoint;
		switch(startSide){
			case TOP:
				//This magic 300 is half the screenWidth - no good way to get this here legitimately
				moveTo(300 - getSprite().getWidth() / 2, -getSprite().getHeight() - 10);
				break;

			case BOTTOM:
				//The magic 650 is the screenHeight
				moveTo(300 - getSprite().getWidth() / 2, 650);
				break;

			case LEFT:
				moveTo(-getSprite().getWidth() - 10, startPoint.getY());
				break;

			case RIGHT:
				moveTo(610, startPoint.getY());
				break;
		}
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);

		if(!started){
			moveToStart(screenWidth, screenHeight);
		}
	}

	public boolean isStarted(){
		return started;
	}

	public void moveToStart(int screenWidth, int screenHeight){
		final double FLOAT_SPEED = 0.5;
		if(getHitbox().getCenter().distance(startPoint) <= FLOAT_SPEED){
			//Once we reach the startPoint, let specific code take over
			setXvel(0);
			setYvel(0);
			started = true;
		}
		else{
			if(getHitbox().getCenter().getY() > startPoint.getY()){
				move(0, -FLOAT_SPEED);
			}
			else if(getHitbox().getCenter().getY() < startPoint.getY()){
				move(0, FLOAT_SPEED);
			}
			else if(getHitbox().getCenter().getX() > startPoint.getX()){
				move(-FLOAT_SPEED, 0);
			}
			else if(getHitbox().getCenter().getX() < startPoint.getX()){
				move(FLOAT_SPEED, 0);
			}
		}
	}
}