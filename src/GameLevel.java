/**
* The GameLevel class provides a wrapper for all of the
* data required to define a 'level'
*
* @author  Will Taylor
* @version 0.1
* @since   17-07-2017
*/

package src;

import java.util.ArrayList;

import src.gameobjects.EnemyShip;

public abstract class GameLevel{
	public int screenWidth, screenHeight;

	public GameLevel(int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	public abstract ArrayList<EnemyShip> getEnemySpawns(long gameTime);
}