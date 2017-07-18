package src.gameobjects;

public class Player extends Ship{
	public Player(int x, int y){
		moveTo(x, y);
		System.out.println(String.format("Player pos: %d %d", getX(), getY()));
	}
}