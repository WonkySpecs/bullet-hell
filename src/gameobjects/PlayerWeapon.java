package src.gameobjects;

import java.util.ArrayList;

import src.ProjectileData;
import src.animation.AnimationMapFactory;
import src.HitboxCircle;

public class PlayerWeapon{
	public enum WeaponType{
		PRIM, SEC;
	}

	private int level, maxLevel, fireDelay, framesSinceFired;
	private ProjectileData projData;
	private WeaponType weaponType;

	public PlayerWeapon(WeaponType weaponType){
		framesSinceFired = 0;
		level = 1;
		this.weaponType = weaponType;
		if(weaponType == WeaponType.PRIM){
			maxLevel = 3;
			projData = new ProjectileData(ProjectileData.ProjType.STRAIGHT,
											0, -8, 20, Particle.ExplosionType.SMALL_GREEN,
											new HitboxCircle(2),
											AnimationMapFactory.getAnimationMap(AnimationMapFactory.PROJ_PLAYER_PRIM_1));
			fireDelay = 10;
		}
		else if (weaponType == WeaponType.SEC){
			maxLevel = 2;
		}
		else{
			System.out.println("Invalid WeaponType specified for PlayerWeapon");
		}
	}

	public void incrementTimer(){
		//Arbitrary bound to stop framesSinceFired getting too big
		if(framesSinceFired < 100){
			framesSinceFired++;
		}
	}

	//The parameters x and y represent the top left of the players sprite
	public ArrayList<Projectile> fire(double x, double y){
		ArrayList<Projectile> shotsFired = new ArrayList<>();
		if(framesSinceFired > fireDelay){
			if(weaponType == WeaponType.PRIM){
				//All of these magic numbers are to put the bullets in the
				//right place relative to the palyer sprite

				if(level > 1){
					shotsFired.add(projData.newProjectile(x + 4, y));
					shotsFired.add(projData.newProjectile(x + 14, y));
					shotsFired.add(projData.newProjectile(x + 24, y));
				}
				else{
					shotsFired.add(projData.newProjectile(x + 5, y));
					shotsFired.add(projData.newProjectile(x + 23, y));
				}
			}
			framesSinceFired = 0;
		}
		if(shotsFired.size() > 0){
			return shotsFired;			
		}
		else{
			return null;
		}
	}

	public void upgrade(){
		if(level < maxLevel){
			level += 1;

			if(weaponType == WeaponType.PRIM){
				switch(level){
					case 2:
						projData = new ProjectileData(ProjectileData.ProjType.STRAIGHT,
														0, -11, 50, Particle.ExplosionType.GREEN,
														new HitboxCircle(3),
														AnimationMapFactory.getAnimationMap(AnimationMapFactory.PROJ_PLAYER_PRIM_2));
						fireDelay = 8;
						break;

					case 3:
						projData = new ProjectileData(ProjectileData.ProjType.STRAIGHT,
														0, -12, 100, Particle.ExplosionType.RED,
														new HitboxCircle(3),
														AnimationMapFactory.getAnimationMap(AnimationMapFactory.PROJ_PLAYER_PRIM_3));
						fireDelay = 4;
						break;
				}
			}
			else if(weaponType == WeaponType.SEC){
				switch(level){
					case 2:
						//qwe
						break;
				}

			}

			//Allow immediate shot, doesn't interrupt 'flow' of game
			framesSinceFired = fireDelay;
		}
	}
}