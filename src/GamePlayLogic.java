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

import src.gameobjects.*;
import src.animation.*;

public class GamePlayLogic{
	private GameLevel level;
	private Player player;
	private ArrayList<EnemyShip> enemyShipList;
	private ArrayList<Projectile> enemyProjectileList, playerProjectileList;
	private ArrayList<ItemDrop> itemList;
	private int screenWidth, screenHeight;
	private boolean paused;
	private long pauseTime, score;
	private ArrayList<Particle> particleList;

	private static final int DEPTH_PLAYER = 0;
	private static final int DEPTH_ENEMY = 1;
	private static final int DEPTH_PLAYER_PROJECTILE = 2;
	private static final int DEPTH_ENEMY_PROJECTILE = 3;
	private static final int DEPTH_ITEM = 4;
	private static final int DEPTH_PARTICLE = 5;

	public GamePlayLogic(GameLevel level, int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.level = level;
		enemyShipList = new ArrayList<EnemyShip>();
		playerProjectileList = new ArrayList<Projectile>();
		enemyProjectileList = new ArrayList<Projectile>();
		itemList = new ArrayList<ItemDrop>();
		particleList = new ArrayList<Particle>();
		pauseTime = System.nanoTime();
		paused = false;

		HitboxCircle playerHitbox = new HitboxCircle(0, 0, 2, 16, 16);
		player = new Player(0, 0, playerHitbox, AnimationMapFactory.getAnimationMap(AnimationMapFactory.PLAYER));
		player.moveTo(screenWidth / 2 - (player.getSprite().getWidth() / 2), screenHeight / 1.5);
	}

	public void update(long gameTime, HashMap<String, Boolean> inputs){
		//Add any newly spawning enemies, as definied in the current level
		ArrayList<EnemyShip> newEnemies = level.getEnemySpawns(gameTime);

		if(newEnemies != null){
			enemyShipList.addAll(newEnemies);			
		}

		updatePlayer(inputs);
		updateEnemies();
		updateProjectiles();		
		updateItems();
		updateParticles();
	}

	//Move and fire based on inputs
	//New projectiles are added to playerProjectileList
	private void updatePlayer(HashMap<String, Boolean> inputs){
		if(inputs.get(GamePlay.ACT_UP)){
			player.moveUp();
		}
		if(inputs.get(GamePlay.ACT_DOWN)){
			player.moveDown();
		}
		if(inputs.get(GamePlay.ACT_LEFT)){
			player.moveLeft();
		}
		if(inputs.get(GamePlay.ACT_RIGHT)){
			player.moveRight();
		}

		player.update(screenWidth, screenHeight);
		ArrayList<Projectile> newPlayerProjectiles = null;

		//Firing special overrides firing primary overrides firing secondary
		if(inputs.get(GamePlay.ACT_FIRE_SPECIAL)){
			newPlayerProjectiles = player.fire(GamePlay.ACT_FIRE_SPECIAL);
		}
		else if(inputs.get(GamePlay.ACT_FIRE_PRIM)){
			newPlayerProjectiles = player.fire(GamePlay.ACT_FIRE_PRIM);
		}
		else if(inputs.get(GamePlay.ACT_FIRE_SEC)){
			newPlayerProjectiles = player.fire(GamePlay.ACT_FIRE_SEC);
		}

		if(newPlayerProjectiles != null){
			playerProjectileList.addAll(newPlayerProjectiles);
		}
	}

	//Move all enemies, check if they should be removed, and check for collision with player
	private void updateEnemies(){
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
				
			if(Hitbox.collisionBetween(player.getHitbox(), enemy.getHitbox())){
				dead = true;
				System.out.println("player got hit boom");
			}

			if(dead){
				enemy = null;
				enemyIterator.remove();
			}
		}
	}

	//Move player projectiles and check for enemy hits
	//Move enemy projectiles and check for player hits
	private void updateProjectiles(){
		for (Iterator<Projectile> projIterator = playerProjectileList.iterator(); projIterator.hasNext();){
			boolean expired = false;
			Projectile proj = projIterator.next();
			proj.update(screenWidth, screenHeight);
			if(proj.isRemovable() && proj.isOffScreen(screenWidth, screenHeight)){
				expired = true;
			}
			else{
				for (Iterator<EnemyShip> enemyIterator = enemyShipList.iterator(); enemyIterator.hasNext();){
					EnemyShip enemy = enemyIterator.next();
					if(Hitbox.collisionBetween(proj.getHitbox(), enemy.getHitbox())){
						enemy.reduceHitpoints(proj.getDamage());
						if(enemy.getHitPoints() <= 0){
							ItemDrop newItem = enemy.getItemDrop();
							if(newItem != null){
								newItem.moveTo(enemy.getX(), enemy.getY());
								itemList.add(newItem);
							}
							enemy = null;
							enemyIterator.remove();					
						}
						ArrayList<Particle> newParticles = Particle.explosion(proj.getExplosionType(), proj.getHitbox().getCenter().getX(), proj.getHitbox().getCenter().getY());
						if(newParticles != null){
							particleList.addAll(newParticles);
						}
						expired = true;
					}
				}
			}

			if(expired){
				proj = null;
				projIterator.remove();
			}
		}

		for (Iterator<Projectile> projIterator = enemyProjectileList.iterator(); projIterator.hasNext();){
			boolean expired = false;
			Projectile proj = projIterator.next();
			proj.update(screenWidth, screenHeight);
			if(proj.isRemovable() && proj.isOffScreen(screenWidth, screenHeight)){
				expired = true;
			}
			else{
				if(Hitbox.collisionBetween(proj.getHitbox(), player.getHitbox())){
					System.out.println("Shot down");
					expired = true;
				}
			}

			if(expired){
				proj = null;
				projIterator.remove();
			}
		}
	}

	private void updateItems(){
		for (Iterator<ItemDrop> itemIterator = itemList.iterator(); itemIterator.hasNext();){
			boolean dead = false;
			ItemDrop item = itemIterator.next();
			item.track((HitboxCircle)player.getHitbox());
			item.update(screenWidth, screenHeight);
			if(item.isOffScreen(screenWidth, screenHeight)){
				dead = true;
			}
			else{
				if(Hitbox.collisionBetween(item.getHitbox(), player.getHitbox())){
					dead = true;
				}
			}

			if(dead){
				item = null;
				itemIterator.remove();
			}
		}
	}

	private void updateParticles(){
		for (Iterator<Particle> particleIterator = particleList.iterator(); particleIterator.hasNext();){
			boolean dead = false;
			Particle particle = particleIterator.next();
			particle.update(screenWidth, screenHeight);

			if(particle.isOffScreen(screenWidth, screenHeight) || particle.isDead()){
				dead = true;
			}

			if(dead){
				particle = null;
				particleIterator.remove();
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

		for(ItemDrop item : itemList){
			spriteDataList.add(new SpriteData(item.getSprite(), item.getPos(), DEPTH_ITEM));	
		}

		for(Particle particle : particleList){
			spriteDataList.add(new SpriteData(particle.getSprite(), particle.getPos(), DEPTH_PARTICLE, particle.getAlpha()));
		}

		spriteDataList.add(new SpriteData(player.getSprite(), player.getPos(), DEPTH_PLAYER));

		return spriteDataList;
	}
}
