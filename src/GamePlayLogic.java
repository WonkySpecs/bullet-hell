package src;

import src.gameobjects.*;

public class GamePlayLogic{
	private GameLevel level;
	private Player player;

	public GamePlayLogic(GameLevel level){
		this.level = level;
		player = new Player(300, 500);
		System.out.println("level loaded");
	}

	public void update(long gameTime, boolean[] inputs){
		System.out.println(String.format("TICK %d", gameTime));
		for(int i = 0; i < inputs.length; i++){
			System.out.println(inputs[i]);
		}
	}
}