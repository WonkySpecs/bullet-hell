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
	private ArrayList<GameObject> gameObjectList;

	public GamePlayLogic(GameLevel level){
		this.level = level;
		gameObjectList = new ArrayList<GameObject>();
		player = new Player(300, 200);
		gameObjectList.add(player);
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
		for(GameObject go : gameObjectList){
			spriteDataList.add(new SpriteData(go.getSprite(), go.getPos(), 0));
		}

		return spriteDataList;
	}
}