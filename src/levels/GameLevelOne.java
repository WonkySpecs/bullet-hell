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
	private static final int SCORE_RED_FLOATER = 100;
	private static final int SCORE_FLOATER_SHOOTER = 200;
	private static final int SCORE_BIG_FLOATER = 1000;
	
	HashMap<String, Animation> floaterAnimations;

	public GameLevelOne(int screnWidth, int screenHeight){
		super(screnWidth, screenHeight);
		floaterAnimations = AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_FLOATER_RED);
	}

	@Override
	public ArrayList<EnemyShip> getEnemySpawns(long gameTime){
		double floaterRadius = 15;
		double startX, startY;

		ArrayList<EnemyShip> newEnemies = new ArrayList<>();

		if(gameTime == 35){
			startX = 50;
			startY = -25;
			HitboxCircle fh = new HitboxCircle(floaterRadius);
			newEnemies.add(new EnemyStraightFloater(startX, startY, 100, 2, 7, SCORE_RED_FLOATER, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, null));

			startX = getScreenWidth() - 65;
			startY = -25;
			fh = new HitboxCircle(floaterRadius);
			newEnemies.add(new EnemyStraightFloater(startX, startY, 100, -2, 7, SCORE_RED_FLOATER, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, null));

			for(int i = -100;i < 101;i += 100){
				startX = getScreenWidth() / 2 + i;
				startY = 200;
				ItemDrop item = new ItemDrop(startX, startY, 65, new HitboxCircle(10), AnimationMapFactory.getAnimationMap(AnimationMapFactory.ITEM_UPGRADE));
				fh = new HitboxCircle(floaterRadius);
				newEnemies.add(new EnemyStraightFloater(startX, startY, 100, 0, 0, SCORE_RED_FLOATER, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, item));
			}

			return newEnemies;
		}

		if(gameTime > 110 && gameTime < 190){
			if(gameTime % 12 == 0){
				ArrayList<EnemyShip> floaters = new ArrayList<>();

				startX = getScreenWidth() / 2 - floaterRadius;
				startY = -25;
				HitboxCircle fh = new HitboxCircle(floaterRadius);
				newEnemies.add(new EnemyStraightFloater(startX, startY, 100, 0, 4, SCORE_RED_FLOATER, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, null));

				startX = -20;
				startY = getScreenHeight() / 2;
				fh = new HitboxCircle(floaterRadius);
				newEnemies.add(new EnemyStraightFloater(startX, startY, 100, 3, -2, SCORE_RED_FLOATER, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, null));

				startX = getScreenWidth() + 20;
				startY = getScreenHeight() / 2;
				fh = new HitboxCircle(floaterRadius);
				newEnemies.add(new EnemyStraightFloater(startX, startY, 100, -3, -2, SCORE_RED_FLOATER, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), null, null));

				return newEnemies;
			}
		}

		if(gameTime > 220 && gameTime < 260){
			if(gameTime % 5 == 0){
				startX = (int)((((float)(gameTime - 220) / 40) * (getScreenWidth() - 1)) - floaterRadius);
				startY = -25;
				HitboxCircle fh = new HitboxCircle(floaterRadius);
				HashMap<String, Animation> projAnimations = AnimationMapFactory.getAnimationMap(AnimationMapFactory.PROJ_TINY_BLUE);
				ProjectileData shooterProj = new ProjectileData(ProjectileData.ProjType.STRAIGHT, 0, 9, 1, Particle.ExplosionType.SMALL_BLUE, new HitboxCircle(2), projAnimations);
				newEnemies.add(new EnemyStraightFloaterShooter(startX, startY, 1, 0, 4, 15, SCORE_FLOATER_SHOOTER, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), shooterProj, null));
				return newEnemies;
			}
		}

		if(gameTime == 330){
			for(double i = 0;i < getScreenWidth();){
				startX = i;
				startY = -10;
				HitboxCircle fh = new HitboxCircle(8);
				newEnemies.add(new EnemySuicideTracker(startX, startY, 1, 2, 0.05, 50, fh, AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_SUICIDE_SMALL), null, null));				
				i += 30;
			}
			return newEnemies;
		}

		if(gameTime == 420){
			startY = -50;
			startX = getScreenWidth() / 2 - 32;
			newEnemies.add(new EnemyStraightFloater(startX, startY, 10000, 0, 0.2, SCORE_BIG_FLOATER,
													new HitboxCircle(33), 
													AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_FLOATER_BIG_BLUE),
													null,
													null));
			return newEnemies;
		}
		return null;
	}
}
