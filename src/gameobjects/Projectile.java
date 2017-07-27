package src.gameobjects;

import java.util.HashMap;

import src.GameObject;
import src.Hitbox;
import src.animation.*;

public abstract class Projectile extends GameObject{
	private boolean removable;

	public Projectile(int x, int y, Hitbox hitbox, HashMap<String, Animation> animations){
		super(x, y, hitbox, animations);
		removable = false;
	}

	public void setRemovable(boolean removable){
		this.removable = removable;
	}

	public boolean isRemovable(){
		return removable;
	}
}