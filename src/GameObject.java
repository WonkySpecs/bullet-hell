package src;

public abstract class GameObject{
	private int x, y;
	//some hitbox thing
	//some graphics thing

	public void moveTo(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void moveRight(int dist){
		this.x += dist;
	}

	public void moveUp(int dist){
		this.y -= dist;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}