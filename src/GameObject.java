/**
* GameObject is the abstract base class which is extended
* to represent all of the objects during gameplay -
* this includes the player, enemies, and projectiles
*
* @author  Will Taylor
* @version 0.1
* @since   17-07-2017
*/

package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Point;
import java.util.HashMap;

import src.animation.*;

public abstract class GameObject{
	private int x, y, xvel, yvel, screenWidth, screenHeight;
	private Hitbox hitbox;

	private String curAnimationName;
	private Animation curAnimation;
	private HashMap<String, Animation> animations;

	public GameObject(int x, int y, int screenWidth, int screenHeight, HashMap<String, Animation> animations){
		moveTo(x, y);
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.animations = animations;

		//All GameObjects must have a "neutral" animation that is defaulted to on contruction
		setCurAnimation("neutral");
		curAnimation.start();
	}

	public void moveTo(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void moveRight(int dist){
		x += dist;
	}

	public void moveDown(int dist){
		y += dist;
	}

	public void move(int x, int y){
		moveRight(x);
		moveDown(y);
	}

	public void moveRight(){
		x += xvel;
	}

	public void moveLeft(){
		x -= xvel;
	}

	public void moveDown(){
		y += yvel;
	}

	public void moveUp(){
		y -= yvel;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public Point getPos(){
		return new Point(x, y);
	}

	public int getXvel(){
		return xvel;
	}

	public int getYvel(){
		return xvel;
	}

	public void setXvel(int xvel){
		this.xvel = xvel;
	}

	public void setYvel(int yvel){
		this.yvel = yvel;
	}

	public void setCurAnimation(String animation){
		curAnimationName = animation;

		try{
			curAnimation = animations.get(curAnimationName);			
		}
		catch(Exception e){
			System.out.println("Tried to load animation which does not exist for this object");
		}
	}

	public String getCurAnimationName(){
		return curAnimationName;
	}

	public Animation getCurAnimation(){
		return curAnimation;
	}

	public BufferedImage getSprite(){
		return curAnimation.getSprite();
	}

	//Return true if whole of object sprite is offscreen, otherwise false
	public boolean isOffScreen(){
		if(x + getSprite().getWidth() < 0){
			return true;
		}

		if(y + getSprite().getHeight() < 0){
			return true;
		}

		if(x > screenWidth){
			return true;
		}

		if(y > screenHeight){
			return true;
		}

		return false;
	}

	public abstract void update();
}