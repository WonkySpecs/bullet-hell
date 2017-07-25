/**
* GameLevelOne, likely to become a demo level
* 
*
* @author  Will Taylor
* @version 0.1
* @since   17-07-2017
*/

package src.levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import src.GameLevel;
import src.gameobjects.*;
import src.animation.*;

public class GameLevelOne extends GameLevel{
	public GameLevelOne(int screnWidth, int screenHeight){
		super(screnWidth, screenHeight);
	}

	@Override
	public ArrayList<EnemyShip> getEnemySpawns(long gameTime){
		if(gameTime == 50){
			ArrayList<EnemyShip> floaters = new ArrayList<>();

			HashMap<String, Animation> floaterAnimations = AnimationMapFactory.getAnimationMap("enemyfloater1");
			floaters.add(new EnemyStraightFloater(20, -30, screenWidth, screenHeight, 100, 2, 7, floaterAnimations));
			floaters.add(new EnemyStraightFloater(600, -30, screenWidth, screenHeight, 100, -2, 7, floaterAnimations));
			return floaters;
		}
		return null;
	}
}