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
import java.awt.geom.*;

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
			HitboxRectangle squareHitbox = new HitboxRectangle(startX, startY, 16, 16);
			newEnemies.add(new EnemyStraightFloater(startX, startY, 100, 0.4, 2, SCORE_RED_FLOATER,
													squareHitbox,
													AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_HYPNOSQUARE),
													null, null));

			startX = getScreenWidth() - 65;
			startY = -25;
			squareHitbox = new HitboxRectangle(startX, startY, 16, 16);
			newEnemies.add(new EnemyStraightFloater(startX, startY, 100, -0.4, 2, SCORE_RED_FLOATER,
													squareHitbox,
													AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_HYPNOSQUARE),
													null, null));

			for(int i = -100;i < 101;i += 100){
				startX = getScreenWidth() / 2 + i;
				startY = 200;
				ItemDrop item = new ItemDrop(startX, startY, 65, new HitboxCircle(10), AnimationMapFactory.getAnimationMap(AnimationMapFactory.ITEM_UPGRADE));
				HitboxCircle fh = new HitboxCircle(floaterRadius);
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

		if(gameTime > 260 && gameTime < 300){
			if(gameTime % 5 == 0){
				startX = (int)((((float)(gameTime - 260) / 40) * (getScreenWidth() - 1)) - floaterRadius);
				startY = -25;
				HitboxCircle fh = new HitboxCircle(floaterRadius);
				HashMap<String, Animation> projAnimations = AnimationMapFactory.getAnimationMap(AnimationMapFactory.PROJ_TINY_BLUE);
				ProjectileData shooterProj = new ProjectileData(ProjectileData.ProjType.STRAIGHT, 8, Math.PI / 2, 1, Particle.ExplosionType.SMALL_BLUE, Particle.ExplosionDirection.UP, new HitboxCircle(2), projAnimations);
				newEnemies.add(new EnemyStraightFloaterShooter(startX, startY, 1, 0, 4, 15, SCORE_FLOATER_SHOOTER, fh, AnimationMapFactory.copyAnimationMap(floaterAnimations), shooterProj, null));
				return newEnemies;
			}
		}

		if(gameTime == 400){
			for(double i = 0;i < getScreenWidth();){
				startX = i;
				startY = -10;
				HitboxCircle fh = new HitboxCircle(8);
				newEnemies.add(new EnemySuicideTracker(startX, startY, 1, 2, 0.05, 50, fh, AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_SUICIDE_SMALL), null, null));				
				i += 30;
			}
			return newEnemies;
		}

		if(gameTime == 500){
			startY = -50;
			startX = getScreenWidth() / 2 - 32;
			newEnemies.add(new EnemyStraightFloater(startX, startY, 10000, 0, 0.2, SCORE_BIG_FLOATER,
													new HitboxCircle(33), 
													AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_FLOATER_BIG_BLUE),
													null,
													null));
			return newEnemies;
		}

		if(gameTime >= 580 && gameTime <= 680 && (gameTime - 580) % 25 == 0){
			ArrayList<Point2D.Double> path = new ArrayList<>();
			path.add(new Point2D.Double(getScreenWidth() / 2, -20));
			path.add(new Point2D.Double(getScreenWidth() / 2, 100));
			path.add(new Point2D.Double(getScreenWidth() / 2 - 50, 150));
			path.add(new Point2D.Double(getScreenWidth() / 2 + 50, 250));
			path.add(new Point2D.Double(getScreenWidth() / 2, getScreenHeight() + 50));

			HitboxCircle fh = new HitboxCircle(floaterRadius);
			newEnemies.add(new EnemyStraightPathFollower(250,
										path, Math.random() * 2 + 2,
										500, 
										fh,
										AnimationMapFactory.copyAnimationMap(floaterAnimations),
										null, null));

			HashMap<String, Animation> projAnimations = AnimationMapFactory.getAnimationMap(AnimationMapFactory.PROJ_TINY_BLUE);
			newEnemies.add(new EnemyBomb(getScreenWidth() / 2 - 100, -30, 30,
											0, 2, 500,
											185 - ((int)gameTime - 550), 80,
											new HitboxCircle(floaterRadius),
											AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_BOMB),
											new ProjectileData(ProjectileData.ProjType.STRAIGHT,
																3, Math.PI / 2,
																1, Particle.ExplosionType.SMALL_BLUE, Particle.ExplosionDirection.UP, new HitboxCircle(2), projAnimations)));

			newEnemies.add(new EnemyBomb(getScreenWidth()/2  + 100, -30, 30,
											0, 2, 500,
											185 - ((int)gameTime - 550), 80,
											new HitboxCircle(floaterRadius),
											AnimationMapFactory.getAnimationMap(AnimationMapFactory.ENEMY_BOMB),
											new ProjectileData(ProjectileData.ProjType.STRAIGHT,
																3, Math.PI / 2,
																1, Particle.ExplosionType.SMALL_BLUE, Particle.ExplosionDirection.UP, new HitboxCircle(2), projAnimations)));
			return newEnemies;
		}
		return null;
	}
}
