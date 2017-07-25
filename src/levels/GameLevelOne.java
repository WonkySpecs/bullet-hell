/**
* GameLevelOne, likely to become a demo level
* 
*
* @author  Will Taylor
* @version 0.1
* @since   17-07-2017
*/

package src.levels;

import java.util.ArrayList;

import src.GameLevel;
import src.gameobjects.*;

public class GameLevelOne extends GameLevel{
	public GameLevelOne(int screnWidth, int screenHeight){
		super(screnWidth, screenHeight);
	}

	@Override
	public ArrayList<EnemyShip> getEnemySpawns(long gameTime){
		if(gameTime == 100){
			ArrayList<EnemyShip> floaters = new ArrayList<>();

			floaters.add(new EnemyStraightFloater(20, -30, screenWidth, screenHeight, 100, 2, 7, null));
			floaters.add(new EnemyStraightFloater(600, -30, screenWidth, screenHeight, 100, -2, 7, null));
			return floaters;
		}
		return null;
	}
}