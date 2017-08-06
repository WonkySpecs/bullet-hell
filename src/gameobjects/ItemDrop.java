/**
* ItemDrop represents items dropped by EnemyShips on death
* that cna be picked up by the player.
* Each EnemyShip will store the items it should drop on
* death.
* Once the player comes within a certain radius of the item
* it will move towards the player.
* GamePlayLogic should call checkIfTracking(playerHitbox)
* then update (screenWidth, screenHeight)
*
* @author  Will Taylor
* @version 0.1
* @since   07-08-2017
*/

package src.gameobjects;

import java.util.HashMap;

import src.*;
import src.animation.*;

public class ItemDrop extends GameObject{
	private static double DROP_SPEED = 4;
	private static double DEFAULT_TRACKING_RANGE = 20;
	private boolean tracking;
	private double trackingRange;

	public ItemDrop(double x, double y, double trackingRange, Hitbox hitbox, HashMap<String, Animation> animations){
		super(x, y, hitbox, animations);
		setXvel(0);
		setYvel(DROP_SPEED);
		tracking = false;
		this.trackingRange = trackingRange;
	}

	public ItemDrop(double x, double y, Hitbox hitbox, HashMap<String, Animation> animations){
		this(x, y, DEFAULT_TRACKING_RANGE, hitbox, animations);
	}

	/*public ItemDrop(ItemDrop original){
		ItemDrop copy = new ItemDrop(original.getX(), original.getX(), original.getTrackingRange());
		return copy
	}*/

	//Before getting close to the player, move straight down
	//Once tracking range has been entered, move towards player
	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		moveDown();
		moveRight();
	}

	public void track(HitboxCircle playerHitbox){
		//Speed decreases exponentially with respect to distance
		// = DROP_SPEED at DEFAULT_TRACKING_RANGE
		if(!tracking){
			checkIfTracking(playerHitbox);
		}

		if(tracking){
			double dx = playerHitbox.getCenter().getX() - getX();
			double dy =  playerHitbox.getCenter().getY() - getY();
			double targetAngle = Math.atan2(dy, dx);
			double dist = Math.sqrt(dx * dx + dy * dy);

			double speed;
			//If item is very close, don't want to move past. Also solves any
			//possibility of div by 0 errors
			if(dist < 2){
				speed = 1;
			}
			else{
				speed = DROP_SPEED * trackingRange / dist;
			}
			setXvel(speed * Math.cos(targetAngle));
			setYvel(speed * Math.sin(targetAngle));			
		}
	}

	public void checkIfTracking(HitboxCircle playerHitbox){
		if(playerHitbox.getCenter().distance(getHitbox().getCenter()) < trackingRange){
			System.out.println("tracking");
			tracking = true;
		}
	}

	public double getTrackingRange(){
		return trackingRange;
	}
}
