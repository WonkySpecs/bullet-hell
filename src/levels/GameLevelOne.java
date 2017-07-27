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

import src.*;
import src.gameobjects.*;
import src.animation.*;

public class GameLevelOne extends GameLevel{
	public GameLevelOne(int screnWidth, int screenHeight){
		super(screnWidth, screenHeight);
	}

	@Override
	public ArrayList<EnemyShip> getEnemySpawns(long gameTime){
		HashMap<String, Animation> floaterAnimations = AnimationMapFactory.getAnimationMap("enemyfloater1");
		int floaterRadius = 15;
		int fStartX, fStartY;

		if(gameTime == 35){
			ArrayList<EnemyShip> floaters = new ArrayList<>();

			fStartX = 50;
			fStartY = -25;
			HitboxCircle fh = new HitboxCircle(fStartX, fStartY, floaterRadius);
			floaters.add(new EnemyStraightFloater(fStartX, fStartY, 100, 2, 7, fh, floaterAnimations));

			fStartX = getScreenWidth() - 65;
			fStartY = -25;
			fh = new HitboxCircle(fStartX, fStartY, floaterRadius);
			floaters.add(new EnemyStraightFloater(fStartX, fStartY, 100, -2, 7, fh, floaterAnimations));
			return floaters;
		}

		if(gameTime > 110 && gameTime < 190){
			if(gameTime % 12 == 0){
				ArrayList<EnemyShip> floaters = new ArrayList<>();

				fStartX = getScreenWidth() / 2 - 16;
				fStartY = -25;
				HitboxCircle fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				floaters.add(new EnemyStraightFloater(fStartX, fStartY, 100, 0, 4, fh, floaterAnimations));

				fStartX = -20;
				fStartY = getScreenHeight() / 2;
				fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				floaters.add(new EnemyStraightFloater(fStartX, fStartY, 100, 3, -2, fh, floaterAnimations));

				fStartX = getScreenWidth() + 20;
				fStartY = getScreenHeight() / 2;
				fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				floaters.add(new EnemyStraightFloater(fStartX, fStartY, 100, -3, -2, fh, floaterAnimations));

				return floaters;
			}
		}

		if(gameTime > 200){
			if(gameTime % 30 == 0){
				ArrayList<EnemyShip> floaters = new ArrayList<>();

				fStartX = (int)(Math.random() * (double)getScreenWidth());
				fStartY = -25;
				HitboxCircle fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				floaters.add(new EnemyStraightFloater(fStartX, fStartY, 100, 0, 4, fh, floaterAnimations));
				return floaters;
			}
		}
		return null;
	}
}