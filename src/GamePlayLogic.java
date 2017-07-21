/**
* GamePlayLogic handles all of the gameplay processing based on
* the current gametime, the level being playedm and the user's
* input.
* A GamePlayLogic instance is created by the games main GamePlay
* instance, which handles rendering of the results from this class.
*
* @author  Will Taylor
* @version 0.1
* @since   17-07-2017
*/

package src;

import src.gameobjects.*;

import java.util.HashMap;
import java.util.ArrayList;

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
		if(inputs.get("up") == true){
			player.moveDown(-2);
		}
		if(inputs.get("down") == true){
			player.moveDown(2);
		}
		if(inputs.get("left") == true){
			player.moveRight(-2);
		}
		if(inputs.get("right") == true){
			player.moveRight(2);
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
}