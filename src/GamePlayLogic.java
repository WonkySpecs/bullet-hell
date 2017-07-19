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

public class GamePlayLogic{
	private GameLevel level;
	private Player player;

	public GamePlayLogic(GameLevel level){
		this.level = level;
		player = new Player(300, 500);
		System.out.println("level loaded");
	}

	public void update(long gameTime, HashMap<String, Boolean> inputs){
		System.out.println(String.format("TICK %d", gameTime));

		for (String key : inputs.keySet()){
			System.out.println(inputs.get(key));
		}
	}
}