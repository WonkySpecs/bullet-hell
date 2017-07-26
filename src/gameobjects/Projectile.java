package src.gameobjects;

import java.util.HashMap;

import src.GameObject;
import src.animation.*;

public abstract class Projectile extends GameObject{
	private boolean removable;

	public Projectile(int x, int y, HashMap<String, Animation> animations){
		super(x, y, animations);
		removable = false;
	}

	public void setRemovable(boolean removable){
		this.removable = removable;
	}

	public boolean isRemovable(){
		return removable;
	}
}