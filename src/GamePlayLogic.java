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

import src.gameobjects.*;

import java.util.HashMap;
import java.util.ArrayList;

import java.awt.Point;
import java.awt.geom.*;

public class GamePlayLogic{
	private GameLevel level;
	private Player player;
	private ArrayList<Ship> enemyShipList;
	private ArrayList<Projectile> projectileObjectList;
	//private ArrayList<Particle> particleList;

	private static final int DEPTH_PLAYER = 0;
	private static final int DEPTH_ENEMY = 1;
	private static final int DEPTH_PLAYER_PROJECTILE = 2;
	private static final int DEPTH_ENEMY_PROJECTILE = 3;
	private static final int PARTICLE = 4;

	public GamePlayLogic(GameLevel level){
		this.level = level;
		enemyShipList = new ArrayList<Ship>();
		player = new Player(300, 200);
	}

	public void update(long gameTime, HashMap<String, Boolean> inputs){
		if(inputs.get(GamePlay.DIR_UP) == true){
			player.moveUp();
		}
		if(inputs.get(GamePlay.DIR_DOWN) == true){
			player.moveDown();
		}
		if(inputs.get(GamePlay.DIR_LEFT) == true){
			player.moveLeft();
		}
		if(inputs.get(GamePlay.DIR_RIGHT) == true){
			player.moveRight();
		}
		if(inputs.get(GamePlay.PRESS_FIRE_PRIM) == true){
			System.out.println("fire prim");
		}
		if(inputs.get(GamePlay.PRESS_FIRE_SEC) == true){
			System.out.println("fire sec");
		}
		if(inputs.get(GamePlay.PRESS_FIRE_SPECIAL) == true){
			System.out.println("fire special");
		}
	}

	public ArrayList<SpriteData> getSpriteList(){
		ArrayList<SpriteData> spriteDataList = new ArrayList<>();
		for(Ship enemy : enemyShipList){
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