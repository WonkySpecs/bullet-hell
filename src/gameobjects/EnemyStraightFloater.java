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

import src.Hitbox;
import src.animation.*;

public class EnemyStraightFloater extends EnemyShip{
	public EnemyStraightFloater(int x, int y, int hp, int xvel, int yvel, Hitbox hitbox, HashMap<String, Animation> animations){
		super(x, y, hp, hitbox, animations);
		setXvel(xvel);
		setYvel(yvel);
	}

	//moves down by yvel and right by xvel - these values can be negative to move left/up
	//Once the floater is on screen, it will be removed next time it goes off screen
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		moveDown();
		moveRight();

		if(!isRemovable() && !isOffScreen(screenWidth, screenHeight)){
			setRemovable(true);
		}
	}
}