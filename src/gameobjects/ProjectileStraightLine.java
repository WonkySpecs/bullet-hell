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

import src.HitboxCircle;
import src.animation.*;

public class ProjectileStraightLine extends Projectile{
	public ProjectileStraightLine(int x, int y, int screenWidth, int screenHeight, int xvel, int yvel, HashMap<String, Animation> animations){
		super(x, y, screenWidth, screenHeight, animations);
		setXvel(xvel);
		setYvel(yvel);
		HitboxCircle hitbox = new HitboxCircle(2, 2, 1);
	}

	//moves down by yvel and right by xvel - these values can be negative to move left/up
	//Once the floater is on screen, it will be removed next time it goes off screen
	public void update(){
		moveDown();
		moveRight();

		if(!isRemovable() && !isOffScreen()){
			setRemovable(true);
		}
	}
}