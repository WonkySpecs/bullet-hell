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
import src.HitboxCircle;
import src.animation.*;
import src.ProjectileData;

public class EnemyStraightFloater extends EnemyShip{
	public EnemyStraightFloater(double x, double y, int hp, double xvel, double yvel, int score, Hitbox hitbox, HashMap<String, Animation> animations, ProjectileData projType, ItemDrop itemDrop){
		super(x, y, hp, score, hitbox, animations, projType, itemDrop);
		setXvel(xvel);
		setYvel(yvel);
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

	@Override
	public ArrayList<Projectile> fire(HitboxCircle playerHitbox){
		return null;
	}
}
