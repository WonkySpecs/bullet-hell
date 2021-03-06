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

	private static final Integer[] animationsToLoad = new Integer[] { 
																AnimationMapFactory.ENEMY_FLOATER_RED,
																AnimationMapFactory.ENEMY_HYPNOSQUARE,
																AnimationMapFactory.ENEMY_SUICIDE_SMALL,
																AnimationMapFactory.ENEMY_BOMB,
																AnimationMapFactory.ENEMY_FLOATER_BIG_BLUE,
																AnimationMapFactory.ENEMY_BOSS_DROID,
																AnimationMapFactory.PROJ_TINY_BLUE,
																AnimationMapFactory.PROJ_BLUE,
																AnimationMapFactory.ITEM_UPGRADE};

	public GameLevelOne(int screnWidth, int screenHeight){
		super(screnWidth, screenHeight, animationsToLoad);
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
													getPreloadedAnimationMap(AnimationMapFactory.ENEMY_HYPNOSQUARE),
													null, null));

			startX = getScreenWidth() - 65;
			startY = -25;
			squareHitbox = new HitboxRectangle(startX, startY, 16, 16);
			newEnemies.add(new EnemyStraightFloater(startX, startY, 100, -0.4, 2, SCORE_RED_FLOATER,
													squareHitbox,
													getPreloadedAnimationMap(AnimationMapFactory.ENEMY_HYPNOSQUARE),
													null, null));

			for(int i = -100;i < 101;i += 100){
				startX = getScreenWidth() / 2 + i;
				startY = 200;
				ItemDrop item = new ItemDrop(startX, startY,
												65, new HitboxCircle(10),
												ItemDrop.ItemType.UPGRADE,
												getPreloadedAnimationMap(AnimationMapFactory.ITEM_UPGRADE));
				HitboxCircle fh = new HitboxCircle(floaterRadius);
				newEnemies.add(new EnemyStraightFloater(startX, startY,
														100, 0, 0, SCORE_RED_FLOATER,
														fh,
														getPreloadedAnimationMap(AnimationMapFactory.ENEMY_FLOATER_RED),
														null, item));
			}

			return newEnemies;
		}

		if(gameTime > 110 && gameTime < 190){
			if(gameTime % 12 == 0){
				ArrayList<EnemyShip> floaters = new ArrayList<>();

				startX = getScreenWidth() / 2 - floaterRadius;
				startY = -25;
				HitboxCircle fh = new HitboxCircle(floaterRadius);
				newEnemies.add(new EnemyStraightFloater(startX, startY,
														100, 0, 4, SCORE_RED_FLOATER,
														fh,
														getPreloadedAnimationMap(AnimationMapFactory.ENEMY_FLOATER_RED),
														null, null));

				startX = -20;
				startY = getScreenHeight() / 2;
				fh = new HitboxCircle(floaterRadius);
				newEnemies.add(new EnemyStraightFloater(startX, startY,
														100, 3, -2, SCORE_RED_FLOATER,
														fh,
														getPreloadedAnimationMap(AnimationMapFactory.ENEMY_FLOATER_RED),
														null, null));

				startX = getScreenWidth() + 20;
				startY = getScreenHeight() / 2;
				fh = new HitboxCircle(floaterRadius);
				newEnemies.add(new EnemyStraightFloater(startX, startY,
														100, -3, -2, SCORE_RED_FLOATER,
														fh,
														getPreloadedAnimationMap(AnimationMapFactory.ENEMY_FLOATER_RED),
														null, null));

				return newEnemies;
			}
		}

		if(gameTime > 260 && gameTime < 300){
			if(gameTime % 5 == 0){
				startX = (int)((((float)(gameTime - 260) / 40) * (getScreenWidth() - 1)) - floaterRadius);
				startY = -25;
				HitboxCircle fh = new HitboxCircle(floaterRadius);
				HashMap<String, Animation> projAnimations = getPreloadedAnimationMap(AnimationMapFactory.PROJ_TINY_BLUE);
				HashMap<String, ProjectileData> projDataMap = new HashMap<>();
				ProjectileData shooterProj = new ProjectileData(ProjectileData.ProjType.STRAIGHT, 8, Math.PI / 2, 1, Particle.ExplosionType.SMALL_BLUE, Particle.ExplosionDirection.UP, new HitboxCircle(2), projAnimations);
				projDataMap.put("main", shooterProj);
				newEnemies.add(new EnemyStraightFloaterShooter(startX, startY,
																1, 0, 4, 15, SCORE_FLOATER_SHOOTER, 
																fh, getPreloadedAnimationMap(AnimationMapFactory.ENEMY_FLOATER_RED), projDataMap, null));
				return newEnemies;
			}
		}

		if(gameTime == 400){
			for(double i = 0;i < getScreenWidth();){
				startX = i;
				startY = -10;
				HitboxCircle fh = new HitboxCircle(8);
				newEnemies.add(new EnemySuicideTracker(startX, startY, 1, 2, 0.05, 50, fh, getPreloadedAnimationMap(AnimationMapFactory.ENEMY_SUICIDE_SMALL), null, null));				
				i += 30;
			}
			return newEnemies;
		}

		if(gameTime == 500){
			startY = -50;
			startX = getScreenWidth() / 2 - 32;
			newEnemies.add(new EnemyStraightFloater(startX, startY, 10000, 0, 0.2, SCORE_BIG_FLOATER,
													new HitboxCircle(33), 
													getPreloadedAnimationMap(AnimationMapFactory.ENEMY_FLOATER_BIG_BLUE),
													null,
													null));
			return newEnemies;
		}

		final long BOMB_START_TIME = 600;

		if(gameTime >= BOMB_START_TIME && gameTime <= BOMB_START_TIME + 150 && (gameTime - BOMB_START_TIME) % 50 == 0){
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
										getPreloadedAnimationMap(AnimationMapFactory.ENEMY_FLOATER_RED),
										null, null));

			HashMap<String, Animation> projAnimations = getPreloadedAnimationMap(AnimationMapFactory.PROJ_BLUE);
			HashMap<String, ProjectileData> projDataMap = new HashMap<>();
			projDataMap.put("main", new ProjectileData(ProjectileData.ProjType.STRAIGHT,
								2, Math.PI / 2,
								1, Particle.ExplosionType.SMALL_BLUE, Particle.ExplosionDirection.UP,
								new HitboxCircle(1.5), projAnimations));
			newEnemies.add(new EnemyBomb(getScreenWidth() / 2 - 100, -30, 30,
											0, 1.2, 100,
											270 - (int)(gameTime - BOMB_START_TIME), 30,
											new HitboxCircle(floaterRadius),
											getPreloadedAnimationMap(AnimationMapFactory.ENEMY_BOMB),
											projDataMap));

			projDataMap = new HashMap<>();
			projDataMap.put("main", new ProjectileData(ProjectileData.ProjType.STRAIGHT,
								2, Math.PI / 2,
								1, Particle.ExplosionType.SMALL_BLUE, Particle.ExplosionDirection.UP,
								new HitboxCircle(1.5), projAnimations));
			newEnemies.add(new EnemyBomb(getScreenWidth()/2  + 100, -30, 30,
											0, 1.2, 100,
											270 - (int)(gameTime - BOMB_START_TIME), 30,
											new HitboxCircle(floaterRadius),
											getPreloadedAnimationMap(AnimationMapFactory.ENEMY_BOMB),
											projDataMap));
			return newEnemies;
		}

		final long BOSS_SPAWN_TIME = 1000;

		if(gameTime == BOSS_SPAWN_TIME){
			HashMap<String, Animation> targetProjAnimations = getPreloadedAnimationMap(AnimationMapFactory.PROJ_BLUE);

			HashMap<String, ProjectileData> projDataMap = new HashMap<>();
			projDataMap.put("targetted", new ProjectileData(ProjectileData.ProjType.STRAIGHT,
								2, Math.PI / 2,
								1, Particle.ExplosionType.SMALL_BLUE, Particle.ExplosionDirection.UP,
								new HitboxCircle(1.5), targetProjAnimations));
			projDataMap.put("main", new ProjectileData(ProjectileData.ProjType.STRAIGHT,
								2, Math.PI / 2,
								1, Particle.ExplosionType.SMALL_BLUE, Particle.ExplosionDirection.UP,
								new HitboxCircle(1.5), targetProjAnimations));

			newEnemies.add(new EnemyBossDroid(
										EnemyBoss.StartSide.TOP, new Point2D.Double(300, 100),
										50, getScreenWidth() - 300, 80, 0.8,
										10000, 10000, 
										new HitboxRectangle(5, 0, 295, 145, 5, 10), 
										getPreloadedAnimationMap(AnimationMapFactory.ENEMY_BOSS_DROID),
										projDataMap));
			setBossSpawned(true);
			return newEnemies;
		}

		return null;
	}
}