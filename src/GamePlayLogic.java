/**
* GamePlayLogic handles all of the gameplay processing based on
* the current gametime, the level being playeds and the user's
* input.
* A GamePlayLogic instance is created by the games main GamePlay
* instance, which handles rendering of the results from this class.
* Hit detection is handled by this class.
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
		HitboxCircle playerHitbox = new HitboxCircle(playerStartX, playerStartY, 6);
		player = new Player(playerStartX, playerStartY, playerHitbox, AnimationMapFactory.getAnimationMap("player"));
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
		//Update all enemies and projectiles, deleting if offscreen
		for (Iterator<EnemyShip> iterator = enemyShipList.iterator(); iterator.hasNext();) {
			EnemyShip enemy = iterator.next();
			enemy.update(screenWidth, screenHeight);
			if(enemy.isRemovable() && enemy.isOffScreen(screenWidth, screenHeight)){
				enemy = null;
				iterator.remove();
			}
			else{
				if(Hitbox.collisionBetween(player.getHitbox(), enemy.getHitbox()) == true){
					System.out.println("ASDFASDFASDF");
				}
			}
		}

		for (Iterator<Projectile> iterator = playerProjectileList.iterator(); iterator.hasNext();) {
			Projectile proj = iterator.next();
			proj.update(screenWidth, screenHeight);
			if(proj.isRemovable() && proj.isOffScreen(screenWidth, screenHeight)){
				proj = null;
				iterator.remove();
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

		spriteDataList.add(new SpriteData(player.getSprite(), player.getPos(), DEPTH_PLAYER));

		return spriteDataList;
	}
}