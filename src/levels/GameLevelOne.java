package src.levels;

import java.util.ArrayList;

import src.GameLevel;
import src.gameobjects.*;

public class GameLevelOne extends GameLevel{
	public GameLevelOne(){
		super();
	}

	@Override
	public ArrayList<ShipEnemy> getEnemySpawns(long gameTime){
		if(gameTime == 100){
			ArrayList<ShipEnemy> floaters = new ArrayList<>();

			floaters.add(new EnemyStraightFloater(0, -30, 100, 2, 8, null));
			floaters.add(new EnemyStraightFloater(400, -30, 100, -2, 8, null));
			return floaters;
		}
		return null;
	}
}