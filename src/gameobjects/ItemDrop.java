package src.gameobjects;

import java.util.HashMap;

import src.GameObject;
import src.Hitbox;
import src.animation.*;

public class ItemDrop extends GameObject{
	public ItemDrop(double x, double y, Hitbox hitbox, HashMap<String, Animation> animations){
		super(x, y, hitbox, animations);
	}
}