/**
* GamePlayLogic handles all of the gameplay processing based on
* the current gametime, the level being played and the user's
* input.
* A GamePlayLogic instance is created by the games main GamePlay
* instance, which handles rendering of the results from this class.
* 
*
* @author  Will Taylor
* @version 0.1
* @since   17-07-2017
*/

package src;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Point;

import src.gameobjects.*;
import src.animation.*;

public class GamePlayLogic{
	private GameLevel level;
	private Player player;
	private ArrayList<EnemyShip> enemyShipList;
	private ArrayList<Projectile> enemyProjectileList, playerProjectileList;
	private int screenWidth, screenHeight;
	//private ArrayList<Particle> particleList;

	private static final int DEPTH_PLAYER = 0;
	private static final int DEPTH_ENEMY = 1;
	private static final int DEPTH_PLAYER_PROJECTILE = 2;
	private static final int DEPTH_ENEMY_PROJECTILE = 3;
	private static final int PARTICLE = 4;

	public GamePlayLogic(GameLevel level, int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.level = level;
		enemyShipList = new ArrayList<EnemyShip>();
		playerProjectileList = new ArrayList<Projectile>();
		enemyProjectileList = new ArrayList<Projectile>();

		int playerStartX = screenWidth / 2 - 16;
		int playerStartY = screenHeight / 2 - 16;
		HitboxCircle playerHitbox = new HitboxCircle(playerStartX, playerStartY, 2, 16, 16);
		player = new Player(playerStartX, playerStartY, playerHitbox, AnimationMapFactory.getAnimationMap(AnimationMapFactory.PLAYER));
	}

	public void update(long gameTime, HashMap<String, Boolean> inputs){
		//Add any newly spawning enemies, as definied in the current level
		ArrayList<EnemyShip> newEnemies = level.getEnemySpawns(gameTime);

		if(newEnemies != null){
			enemyShipList.addAll(newEnemies);			
		}

		//Execute player functions based on inputs recieved
		if(inputs.get(GamePlay.ACT_UP) == true){
			player.moveUp();
		}
		if(inputs.get(GamePlay.ACT_DOWN) == true){
			player.moveDown();
		}
		if(inputs.get(GamePlay.ACT_LEFT) == true){
			player.moveLeft();
		}
		if(inputs.get(GamePlay.ACT_RIGHT) == true){
			player.moveRight();
		}

		player.update(screenWidth, screenHeight);
		ArrayList<Projectile> newPlayerProjectiles = null;

		//Firing special overrides firing primary overrides firing secondary
		if(inputs.get(GamePlay.ACT_FIRE_SPECIAL) == true){
			newPlayerProjectiles = player.fire(GamePlay.ACT_FIRE_SPECIAL);
		}
		else if(inputs.get(GamePlay.ACT_FIRE_PRIM) == true){
			newPlayerProjectiles = player.fire(GamePlay.ACT_FIRE_PRIM);
		}
		else if(inputs.get(GamePlay.ACT_FIRE_SEC) == true){
			newPlayerProjectiles = player.fire(GamePlay.ACT_FIRE_SEC);
		}

		if(newPlayerProjectiles != null){
			playerProjectileList.addAll(newPlayerProjectiles);
		}

		//Update all enemies and projectiles, deleting if offscreen or collided with player
		for (Iterator<EnemyShip> enemyIterator = enemyShipList.iterator(); enemyIterator.hasNext();){
			boolean dead = false;
			EnemyShip enemy = enemyIterator.next();

			enemy.update(screenWidth, screenHeight);
			ArrayList<Projectile> newEnemyProj = enemy.fire((HitboxCircle)player.getHitbox());
			if(newEnemyProj != null){
				enemyProjectileList.addAll(newEnemyProj);
			}

			if(enemy.isRemovable() && enemy.isOffScreen(screenWidth, screenHeight)){
				dead = true;
			}
				
			if(Hitbox.collisionBetween(player.getHitbox(), enemy.getHitbox()) == true){
				dead = true;
				System.out.println("player got hit boom");
			}

			if(dead == true){
				enemy = null;
				enemyIterator.remove();
			}
		}

		//Update all player projectiles, dealing damage if enemy is hit.
		for (Iterator<Projectile> projIterator = playerProjectileList.iterator(); projIterator.hasNext();){
			boolean dead = false;
			Projectile proj = projIterator.next();
			proj.update(screenWidth, screenHeight);
			if(proj.isRemovable() && proj.isOffScreen(screenWidth, screenHeight)){
				dead = true;
			}
			else{
				for (Iterator<EnemyShip> enemyIterator = enemyShipList.iterator(); enemyIterator.hasNext();){
					EnemyShip enemy = enemyIterator.next();
					if(Hitbox.collisionBetween(proj.getHitbox(), enemy.getHitbox()) == true){
						enemy.reduceHitpoints(proj.getDamage());
						if(enemy.getHitPoints() <= 0){
							enemy = null;
							enemyIterator.remove();							
						}
						dead = true;
					}
				}
			}

			if(dead == true){
				proj = null;
				projIterator.remove();
			}
		}

		for (Iterator<Projectile> projIterator = enemyProjectileList.iterator(); projIterator.hasNext();){
			boolean dead = false;
			Projectile proj = projIterator.next();
			proj.update(screenWidth, screenHeight);
			if(proj.isRemovable() && proj.isOffScreen(screenWidth, screenHeight)){
				dead = true;
			}
			else{
				if(Hitbox.collisionBetween(proj.getHitbox(), player.getHitbox()) == true){
					System.out.println("Shot down");
					dead = true;
				}
			}

			if(dead == true){
				proj = null;
				projIterator.remove();
			}
		}
	}

	public ArrayList<SpriteData> getSpriteDataList(){
		ArrayList<SpriteData> spriteDataList = new ArrayList<>();
		for(EnemyShip enemy : enemyShipList){
			spriteDataList.add(new SpriteData(enemy.getSprite(), enemy.getPos(), DEPTH_ENEMY));
		}

		for(Projectile proj : playerProjectileList){
			spriteDataList.add(new SpriteData(proj.getSprite(), proj.getPos(), DEPTH_PLAYER_PROJECTILE));
		}

		for(Projectile proj : enemyProjectileList){
			spriteDataList.add(new SpriteData(proj.getSprite(), proj.getPos(), DEPTH_ENEMY_PROJECTILE));
		}

		spriteDataList.add(new SpriteData(player.getSprite(), player.getPos(), DEPTH_PLAYER));

		return spriteDataList;
	}
}