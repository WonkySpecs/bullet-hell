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
		HashMap<String, Animation> floaterAnimations = AnimationMapFactory.getAnimationMap("enemyfloater1");

		if(gameTime == 35){
			ArrayList<EnemyShip> floaters = new ArrayList<>();

			floaters.add(new EnemyStraightFloater(50, -30, 100, 2, 7, floaterAnimations));
			floaters.add(new EnemyStraightFloater(getScreenWidth() - 66, -30, 100, -2, 7, floaterAnimations));
			return floaters;
		}

		if(gameTime > 110 && gameTime < 190){
			if(gameTime % 12 == 0){
				ArrayList<EnemyShip> floaters = new ArrayList<>();
				floaters.add(new EnemyStraightFloater(getScreenWidth()/2, -30, 100, 0, 4, floaterAnimations));
				floaters.add(new EnemyStraightFloater(-20, getScreenHeight()/2, 100, 3, -2, floaterAnimations));
				floaters.add(new EnemyStraightFloater(getScreenWidth() + 20, getScreenHeight()/2, 100, -3, -2, floaterAnimations));

				return floaters;
			}
		}
		return null;
	}
}