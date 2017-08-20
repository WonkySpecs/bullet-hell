/**
* The GameLevel class provides a wrapper for all of the
* data required to define a 'level' ie. where when and
* what type of enemies spawn.
*
* @author  Will Taylor
* @version 0.1
* @since   17-07-2017
*/

package src;

import java.util.ArrayList;

import src.gameobjects.EnemyShip;

public abstract class GameLevel{
	private int screenWidth, screenHeight;
	private boolean bossSpawned;

	public GameLevel(int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		bossSpawned = false;
	}

	public int getScreenWidth(){
		return screenWidth;
	}

	public int getScreenHeight(){
		return screenHeight;
	}

	public boolean isBossSpawned(){
		return bossSpawned;
	}

	public void setBossSpawned(boolean value){
		bossSpawned = value;
	}

	public abstract ArrayList<EnemyShip> getEnemySpawns(long gameTime);
}