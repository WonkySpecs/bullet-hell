/**
* GameLevelOne currently used for testing
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
	HashMap<String, Animation> floaterAnimations;

	public GameLevelOne(int screnWidth, int screenHeight){
		super(screnWidth, screenHeight);
		floaterAnimations = AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_FLOATER_RED);
	}

	@Override
	public ArrayList<EnemyShip> getEnemySpawns(long gameTime){
		
		double floaterRadius = 15;
		double fStartX, fStartY;

		ArrayList<EnemyShip> newEnemies = new ArrayList<>();

		if(gameTime == 35){
			fStartX = 50;
			fStartY = -25;
			HitboxCircle fh = new HitboxCircle(fStartX, fStartY, floaterRadius);
			newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, 2, 7, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, null));

			fStartX = getScreenWidth() - 65;
			fStartY = -25;
			fh = new HitboxCircle(fStartX, fStartY, floaterRadius);
			newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, -2, 7, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, null));

			for(int i = -100;i < 101;i += 100){
				fStartX = getScreenWidth() / 2 + i;
				fStartY = 200;
				fh = new HitboxCircle(fStartX, fStartY, floaterRadius);
				newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, 0, 0, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, 
					new ItemDrop(fStartX, fStartY, 65, fh, AnimationMapFactory.getAnimationMap(AnimationMapFactory.ITEM_UPGRADE))));
			}

			return newEnemies;
		}

		if(gameTime > 110 && gameTime < 190){
			if(gameTime % 12 == 0){
				ArrayList<EnemyShip> floaters = new ArrayList<>();

				fStartX = getScreenWidth() / 2 - 16;
				fStartY = -25;
				HitboxCircle fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, 0, 4, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, null));

				fStartX = -20;
				fStartY = getScreenHeight() / 2;
				fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, 3, -2, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, null));

				fStartX = getScreenWidth() + 20;
				fStartY = getScreenHeight() / 2;
				fh = new HitboxCircle(fStartX, fStartY,  floaterRadius);
				newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 100, -3, -2, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, null));

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
				newEnemies.add(new EnemyStraightFloaterShooter(fStartX, fStartY, 1, 0, 4, 15, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), shooterProj, null));
				return newEnemies;
			}
		}

		if(gameTime == 330){
			for(double i = 0;i < getScreenWidth();){
				fStartX = i;
				fStartY = -10;
				HitboxCircle fh = new HitboxCircle(fStartX, fStartY, 8);
				newEnemies.add(new EnemySuicideTracker(fStartX, fStartY, 1, 2, 0.05, fh, AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_SUICIDE_SMALL), null, null));				
				i += 30;
			}
			return newEnemies;
		}

		if(gameTime == 380){
			fStartY = -50;
			fStartX = getScreenWidth() / 2 - 32;
			newEnemies.add(new EnemyStraightFloater(fStartX, fStartY, 5000, 0, 0.2,
													new HitboxCircle(32), 
													AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_FLOATER_BLUE),
													null,
													null));
			return newEnemies;
		}
		return null;
	}
}
