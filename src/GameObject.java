/**
* GameObject is the abstract base class which is extended
* to represent all of the objects during gameplay -
* this includes the player, enemies, and projectiles
*
* All fundamental methods such as moving, getters and
* setters for hitboxes and animations etc are defined here.
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
import java.util.ArrayList;
import java.awt.geom.*;

import src.animation.*;

public abstract class GameObject{
	private double x, y, xvel, yvel;	
	private String curAnimationName;
	private Animation curAnimation;
	private HashMap<String, Animation> animations;
	private Hitbox hitbox;

	public GameObject(double x, double y, double xvel, double yvel, Hitbox hitbox, HashMap<String, Animation> animations){
		moveTo(x, y);
		this.hitbox = hitbox;
		this.animations = animations;
		this.xvel = xvel;
		this.yvel = yvel;

		//All GameObjects must have a "neutral" animation that is defaulted to on contruction
		setCurAnimation("neutral");
		curAnimation.start();
	}

	public GameObject(double x, double y, Hitbox hitbox, HashMap<String, Animation> animations){
		this(x, y, 0, 0, hitbox, animations);
	}

	public void moveTo(double x, double y){
		this.x = x;
		this.y = y;
	}

	public void moveTo(Point p){
		moveTo(p.getX(), p.getY());
	}

	public void moveRight(double dist){
		x += dist;
	}

	public void moveDown(double dist){
		y += dist;
	}

	public void move(double x, double y){
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

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public Point2D.Double getPos(){
		return new Point2D.Double(x, y);
	}

	public double getXvel(){
		return xvel;
	}

	public double getYvel(){
		return yvel;
	}

	public void setXvel(double newxvel){
		xvel = newxvel;
	}

	public void setYvel(double newyvel){
		yvel = newyvel;
	}

	public void increaseXvel(double increase){
		xvel += increase;
	}

	public void increaseYvel(double increase){
		yvel += increase;
	}

	public Hitbox getHitbox(){
		return hitbox;
	}

	public void setCurAnimation(String animationName){
		if(hasAnimation(animationName)){
			curAnimationName = animationName;
			curAnimation = animations.get(curAnimationName);
			curAnimation.start();						
		}
		else{
			System.out.println("Tried to load " + animationName + " animation which does not exist");
		}
		
	}

	public String getCurAnimationName(){
		return curAnimationName;
	}

	public Animation getCurAnimation(){
		return curAnimation;
	}

	public ArrayList<String> getAnimationNames(){
		ArrayList<String>names = new ArrayList<>();
		for(String k : animations.keySet()){
			names.add(k);
		}
		return names;
	}

	public boolean hasAnimation(String animationName){
		if(getAnimationNames().contains(animationName)){
			return true;
		}
		else{
			return false;
		}
	}

	public HashMap<String, Animation> getAnimationMap(){
		return animations;
	}

	public BufferedImage getSprite(){
		return curAnimation.getSprite();
	}

	//Return true if whole of object sprite is offscreen, otherwise false
	public boolean isOffScreen(int screenWidth, int screenHeight){
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

	public void update(int screenWidth, int screenHeight){
		curAnimation.update();
		if(hitbox != null){
			hitbox.moveTo(x, y);			
		}
	}
}
