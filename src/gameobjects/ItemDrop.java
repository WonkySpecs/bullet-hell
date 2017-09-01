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
	private static double MAX_DROP_SPEED = 4.5;
	private static double INITIAL_DROP_SPEED = -1.75;
	private static double DEFAULT_TRACKING_RANGE = 30;
	private boolean tracking;
	private double trackingRange;
	private ItemType type;

	public enum ItemType{
		UPGRADE, EXTRA_LIFE, POINTS, EXTRA_SPECIAL;
	}

	public ItemDrop(double x, double y, double trackingRange, Hitbox hitbox, ItemType type, HashMap<String, Animation> animations){
		super(x, y, hitbox, animations);
		setXvel(0);
		setYvel(INITIAL_DROP_SPEED);
		tracking = false;
		this.type = type;
		this.trackingRange = trackingRange;
	}

	public ItemDrop(double x, double y, double trackingRange, Hitbox hitbox, ItemType type){
		this(x, y, trackingRange, hitbox, type, getDefaultAnimations(type));
	}

	public ItemDrop(double x, double y, Hitbox hitbox, ItemType type, HashMap<String, Animation> animations){
		this(x, y, DEFAULT_TRACKING_RANGE, hitbox, type, animations);
	}

	public ItemDrop(ItemDrop original){
		this(original.getX(), original.getX(),
				original.getTrackingRange(), 
				Hitbox.copy(original.getHitbox()), 
				original.getItemType(),
				AnimationMapFactory.copyAnimationMap(original.getAnimationMap()));
	}

	//Before getting close to the player, move straight down
	//Once tracking range has been entered, move towards player
	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		if(getYvel() < MAX_DROP_SPEED){
			setYvel(getYvel() + 0.12);
		}
		moveDown();
		moveRight();
	}

	public void track(HitboxCircle playerHitbox){
		if(!tracking){
			checkIfTracking(playerHitbox);
		}

		if(tracking){
			double dx = playerHitbox.getCenter().getX() - getHitbox().getCenter().getX();
			double dy =  playerHitbox.getCenter().getY() - getHitbox().getCenter().getY();
			double targetAngle = Math.atan2(dy, dx);

			setXvel(3 * MAX_DROP_SPEED * Math.cos(targetAngle));
			setYvel(3 * MAX_DROP_SPEED * Math.sin(targetAngle));
		}
	}

	public void checkIfTracking(HitboxCircle playerHitbox){
		if(playerHitbox.getCenter().distance(getHitbox().getCenter()) < trackingRange){
			tracking = true;
		}
	}

	public double getTrackingRange(){
		return trackingRange;
	}

	public ItemType getItemType(){
		return type;
	}

	public int getPoints(){
		if(type == ItemType.POINTS){
			return 500;
		}
		else{
			System.out.println("Tried to getPoints from non-points ItemDrop");
			return 0;
		}
	}

	private static HashMap<String, Animation> getDefaultAnimations(ItemType type){
		int objectType = 0;
		switch(type){
			case UPGRADE:
				objectType  = AnimationMapFactory.ITEM_UPGRADE;
				break;

			//Split this into size classes based on amount of points?
			case POINTS:
				objectType  = AnimationMapFactory.ITEM_POINTS;
				break;

			default:
				System.out.println("Invalid type passed to ItemDrop.getDefaultAnimations()");
		}

		return AnimationMapFactory.getAnimationMap(objectType);
	}
}
