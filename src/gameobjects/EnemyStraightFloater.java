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

import java.awt.image.BufferedImage;
import src.HitboxCircle;

public class EnemyStraightFloater extends ShipEnemy{
	public EnemyStraightFloater(int x, int y, int hp, int xvel, int yvel, BufferedImage sprite){
		super(x, y, hp, sprite);
		setXvel(xvel);
		setYvel(yvel);
		HitboxCircle hitbox = new HitboxCircle(13, 13, 26);
	}

	//moves down by yvel and right by xvel - these values can be negative to move left/up
	public void update(){
		moveDown();
		moveRight();
	}
}