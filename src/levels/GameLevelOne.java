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
		HashMap<String, Animation> floaterAnimations = AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_FLOATER_RED);
		HashMap<String, Animation> yellowSpinnerAnimations = AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_SUICIDE_SMALL);
		double floaterRadius = 15;
		double fStartX, fStartY;

		ArrayList<EnemyShip> newEnemies = new ArrayList<>();

		if(gameTime == 35){
			fStartX = 50;
			fStartY = -25;
			HitboxCircle fh = new HitboxCircle(fStartX, fStartY, floaterRadius);
			newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, 2, 7, fh, floaterAnimations, null));

			fStartX = getScreenWidth() - 65;
			fStartY = -25;
			fh = new HitboxCircle(fStartX, fStartY, floaterRadius);
			newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, -2, 7, fh, floaterAnimations, null));

			fStartX = getScreenWidth()/2;
			fStartY = 200;
			fh = new HitboxCircle(fStartX, fStartY, floaterRadius);
			newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, 0, 0, fh, floaterAnimations, null));

			fStartX = getScreenWidth()/2 + 100;
			fStartY = 200;
			fh = new HitboxCircle(fStartX, fStartY, floaterRadius);
			newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, 0, 0, fh, floaterAnimations, null));

			fStartX = getScreenWidth()/2 - 100;
			fStartY = 200;
			fh = new HitboxCircle(fStartX, fStartY, floaterRadius);
			newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, 0, 0, fh, floaterAnimations, null));

			return newEnemies;
		}

		if(gameTime > 110 && gameTime < 190){
			if(gameTime % 12 == 0){
				ArrayList<EnemyShip> floaters = new ArrayList<>();

				fStartX = getScreenWidth() / 2 - 16;
				fStartY = -25;
				HitboxCircle fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, 0, 4, fh, floaterAnimations, null));

				fStartX = -20;
				fStartY = getScreenHeight() / 2;
				fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, 3, -2, fh, floaterAnimations, null));

				fStartX = getScreenWidth() + 20;
				fStartY = getScreenHeight() / 2;
				fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, -3, -2, fh, floaterAnimations, null));

				return newEnemies;
			}
		}

		if(gameTime > 220 && gameTime < 260){
			if(gameTime % 5 == 0){
				fStartX = (int)((((float)(gameTime - 220) / 40) * (getScreenWidth() - 1)) - 16);
				fStartY = -25;
				HitboxCircle fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				HashMap<String, Animation> projAnimations = AnimationMapFactory.getAnimationMap(AnimationMapFactory.PROJ_TINY_BLUE);
				ProjectileData shooterProj = new ProjectileData(ProjectileData.PROJ_STRAIGHT, 0, 9, 1, new HitboxCircle(fStartX, fStartY, 2), projAnimations);
				newEnemies.add(new EnemyStraightFloaterShooter(fStartX, fStartY, 1, 0, 4, fh, floaterAnimations, shooterProj, 15));
				return newEnemies;
			}
		}

		if(gameTime == 330){
			for(double i = 0;i < getScreenWidth();){
				fStartX = i;
				fStartY = -10;
				HitboxCircle fh = new HitboxCircle(fStartX, fStartY, 4);
				newEnemies.add(new EnemySuicideTracker(fStartX, fStartY, 1, 2, 0.05, fh, yellowSpinnerAnimations, null));				
				i += 30;
			}
			return newEnemies;
		}
		return null;
	}
}