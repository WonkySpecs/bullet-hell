/**
* ItemDrop represents items dropped by EnemyShips on death
* that cna be picked up by the player.
* Each EnemyShip will store the items it should drop on
* death.
* Once the player comes within a certain radius of the item
* it will move towards the player.
*
* @author  Will Taylor
* @version 0.1
* @since   07-08-2017
*/

package src.gameobjects;

import java.util.HashMap;

import src.GameObject;
import src.Hitbox;
import src.animation.*;

public class ItemDrop extends GameObject{
	private static double DROP_SPEED = 4;

	public ItemDrop(double x, double y, Hitbox hitbox, HashMap<String, Animation> animations){
		super(x, y, hitbox, animations);
		setXvel(DROP_SPEED);
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		moveDown();

		//if(!isRemovable() && !isOffScreen(screenWidth, screenHeight)){
		//	setRemovable(true);
		//}
	}
}