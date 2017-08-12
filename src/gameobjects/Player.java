/**
* The Player class is a GameObject which represents the
* players ship.
*
* @author  Will Taylor
* @version 0.1
* @since   18-07-2017
*/


//TODO: Implement upgrades, either directly in player class or as a separate
//class which the Player class will own.
//Many currently hard coded values (speed, weapon details etc) will be encapsulated by upgrades

package src.gameobjects;

import src.*;
import src.animation.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends GameObject{
	private PlayerWeapon primWeapon, secWeapon;
	private double speed;

	public Player(double x, double y, Hitbox hitbox, HashMap<String, Animation> animations){
		super(x, y, hitbox, animations);
		speed = 6.5;
		setXvel(speed);
		setYvel(speed);

		primWeapon = new PlayerWeapon(PlayerWeapon.WeaponType.PRIM);
		secWeapon = new PlayerWeapon(PlayerWeapon.WeaponType.SEC);
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		setXvel(speed);
		setYvel(speed);

		//Stop player moving off screen
		if(getX() < 0){
			moveRight(-getX());
		}
		if(getX() + getSprite().getWidth() > screenWidth){
			moveRight(screenWidth - (getX() + getSprite().getWidth()));
		}
		if(getY() < 0){
			moveDown(-getY());
		}
		if(getY() + getSprite().getHeight() > screenHeight){
			moveDown(screenHeight - (getY() + getSprite().getHeight()));
		}

		primWeapon.incrementTimer();
		secWeapon.incrementTimer();
	}

	public ArrayList<Projectile> fire(PlayerWeapon.WeaponType weapon){
		if(weapon == PlayerWeapon.WeaponType.PRIM){
			setXvel(speed * 0.6);
			setYvel(speed * 0.6);
			return primWeapon.fire(getX(), getY());
		}
		else if(weapon == PlayerWeapon.WeaponType.SEC){
			setXvel(speed * 0.6);
			setYvel(speed * 0.6);
			return secWeapon.fire(getX(), getY());
		}
		else{
			System.out.println("Invalid weapon passed to Player.fire()");
			return null;
		}
	}

	public void upgradeWeapon(){
		primWeapon.upgrade();
	}
}
