/**
* EnemyBossLateralPatroller is a common type of boss
* which simply moves from side to side at a fixed height,
* reversing direction whenever it reaches leftEnd or
* rightEnd
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

public class EnemyBossLateralPatroller extends EnemyBoss{
	private double leftEnd, rightEnd, y, speed;
	private boolean movingRight;

	public EnemyBossLateralPatroller(EnemyBoss.StartSide startSide, Point2D.Double startPoint,
										double leftEnd, double rightEnd, double y, Double speed,
										int hp, int score,				
										Hitbox hitbox, HashMap<String, Animation> animations, HashMap<String, ProjectileData> projectileDataMap){
		super(startSide, startPoint, hp, score, hitbox, animations, projectileDataMap);
		this.leftEnd = leftEnd;
		this.rightEnd = rightEnd;
		this.y = y;
		this.speed = speed;
		
		switch(startSide){
			case RIGHT:
				movingRight = false;
				break;

			default:
				movingRight = true;
				break;
		}
	}

	public void setSpeed(double speed){
		this.speed = speed;
	}

	public double getSpeed(){
		return speed;
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);

		if(isStarted()){
			if(movingRight){
				moveRight(speed);
			}
			else{
				moveRight(-speed);
			}

			if(getHitbox().getPos().getX() > rightEnd){
				movingRight = false;
			}
			else if(getHitbox().getPos().getX() < leftEnd){
				movingRight = true;
			}

		}
	}

	@Override
	public ArrayList<Projectile> fire(HitboxCircle playerHitbox){
		return null;
	}
}