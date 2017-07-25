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

import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.geom.*;

import src.gameobjects.*;
import src.animation.*;

public class GamePlayLogic{
	private GameLevel level;
	private Player player;
	private ArrayList<EnemyShip> enemyShipList;
	private ArrayList<Projectile> projectileList;
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
		projectileList = new ArrayList<Projectile>();

		player = new Player(300, 400, screenWidth, screenHeight, AnimationMapFactory.getAnimationMap("player"));
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
		if(inputs.get(GamePlay.ACT_FIRE_PRIM) == true){
			player.fire(GamePlay.ACT_FIRE_PRIM);
		}
		if(inputs.get(GamePlay.ACT_FIRE_SEC) == true){
			player.fire(GamePlay.ACT_FIRE_SEC);
		}
		if(inputs.get(GamePlay.ACT_FIRE_SPECIAL) == true){
			player.fire(GamePlay.ACT_FIRE_SPECIAL);
		}

		player.update();

		//Update all enemies and projectiles, deleting if offscreen
		for (Iterator<EnemyShip> iterator = enemyShipList.iterator(); iterator.hasNext();) {
			EnemyShip enemy = iterator.next();
			enemy.update();
			if(enemy.isRemovable() && enemy.isOffScreen()){
				enemy = null;
				iterator.remove();
			}
		}

		for(Projectile proj : projectileList){
			proj.update();
		}
	}

	public ArrayList<SpriteData> getSpriteList(){
		ArrayList<SpriteData> spriteDataList = new ArrayList<>();
		for(EnemyShip enemy : enemyShipList){
			spriteDataList.add(new SpriteData(enemy.getSprite(), enemy.getPos(), DEPTH_ENEMY));
		}

		spriteDataList.add(new SpriteData(player.getSprite(), player.getPos(), DEPTH_PLAYER));

		return spriteDataList;
	}

	//Hit detection functions. May move these to a separate class/into the hitbox classes themselves
	public boolean collisionBetween(HitboxCircle h1, HitboxCircle h2){
		double dist = h1.getCenter().distance(h2.getCenter());

		if(dist <= h1.getRadius() + h2.getRadius()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean collisionBetween(HitboxCircle h1, HitboxPolygon h2){
		return true;
	}

	public boolean collisionBetween(HitboxPolygon h1, HitboxCircle h2){
		return collisionBetween(h2, h1);
	}

	//Brute force checks for intersection between every edge of each polygon
	//TODO: If performance is an issue, find a way to optimise this
	public boolean collisionBetween(HitboxPolygon h1, HitboxPolygon h2){
		ArrayList<Line2D.Float> edgeList1 = h1.getPolygonEdgesGlobalSpace();
		ArrayList<Line2D.Float> edgeList2 = h2.getPolygonEdgesGlobalSpace();

		for(Line2D.Float edge1 : edgeList1){
			for(Line2D.Float edge2 : edgeList2){
				if(edge1.intersectsLine(edge2)){
					return true;
				}
			}
		}

		return false;
	}
}