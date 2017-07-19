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