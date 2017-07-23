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

import src.gameobjects.ShipEnemy;

public abstract class GameLevel{
	public GameLevel(){
		System.out.println("blah");
	}

	public abstract ArrayList<ShipEnemy> getEnemySpawns(long gameTime);
}