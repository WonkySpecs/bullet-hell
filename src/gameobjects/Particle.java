/**
* Particles are simple GameObjects which move linearly whilst
* fading away. Particles are creted by the static method
* explosion() and are the lowest depth in drawing.
*
* @author  Will Taylor
* @version 0.1
* @since   07-08-2017
*/

package src.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;

import src.animation.*;
import src.GameObject;

public class Particle extends GameObject{
	private float alpha;
	private int originalLifetime, lifetime;

	public Particle(double x, double y, double xvel, double yvel, int lifetime, HashMap<String, Animation> animations){
		super(x, y, xvel, yvel, null, animations);
		alpha = (float)1.0;
		this.lifetime = lifetime;
		originalLifetime = lifetime;
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		moveDown();
		moveRight();
		lifetime -= 1;
		alpha -= (float)(1.0 / originalLifetime);
	}

	public float getAlpha(){
		return Math.max(alpha, 0);
	}

	public boolean isDead(){
		return (lifetime <= 0);
	}

	public enum ExplosionType{
		SMALL_GREEN, GREEN, BIG_GREEN,
		SMALL_BLUE, BLUE, BIG_BLUE,
		SMALL_RED, RED, BIG_RED,
		SMALL_RANDOM, RANDOM, BIG_RANDOM,
		PLAYER;
	}

	public enum ExplosionDirection{
		DOWN, NEUTRAL, UP;
	}

	public static ArrayList<Particle> explosion(ExplosionType explosionType, ExplosionDirection dir, double x, double y){
		ArrayList<Particle> explosionParticles = new ArrayList<>();
		int numParticles, fadeTime;
		HashMap<String, Animation> animationMap = null;

		if(explosionType == ExplosionType.SMALL_GREEN || explosionType == ExplosionType.SMALL_BLUE || explosionType == ExplosionType.SMALL_RED || explosionType == ExplosionType.SMALL_RANDOM){
			numParticles = (int)Math.round(Math.random() * 5 + 5);
			fadeTime = 20;
		}
		else{
			numParticles = (int)Math.round(Math.random() * 8 + 8);
			fadeTime = 30;
		}

		int animationMapType = 1;

		switch(explosionType){
			case SMALL_GREEN:
				animationMapType = AnimationMapFactory.PARTICLE_SMALL_GREEN;
				break;

			case GREEN:
				animationMapType = AnimationMapFactory.PARTICLE_GREEN;
				break;

			case BIG_GREEN:
				animationMapType = AnimationMapFactory.PARTICLE_BIG_GREEN;
				break;

			case SMALL_BLUE:
				animationMapType = AnimationMapFactory.PARTICLE_SMALL_BLUE;
				break;

			case BLUE:
				animationMapType = AnimationMapFactory.PARTICLE_BLUE;
				break;

			case BIG_BLUE:
				animationMapType = AnimationMapFactory.PARTICLE_BIG_BLUE;
				break;

			case SMALL_RED:
				animationMapType = AnimationMapFactory.PARTICLE_SMALL_RED;
				break;

			case RED:
				animationMapType = AnimationMapFactory.PARTICLE_RED;
				break;

			case BIG_RED:
				animationMapType = AnimationMapFactory.PARTICLE_BIG_RED;
				break;

			case PLAYER:
				animationMapType = AnimationMapFactory.PARTICLE_WHITE;
				break;

			default:
				System.out.println("Nonexistent explosion type specified in Particle.explosion()");
				explosionParticles = null;
				break;
		}
		animationMap = AnimationMapFactory.getAnimationMap(animationMapType);

		for(int i = 0; i < numParticles; i++){
			//NOTE: Do not need to use AnimationMapFactory.copy as animations are all static for particles -
			//change this if no longer true
			double xvel;
			double yvel;
			if(dir == ExplosionDirection.DOWN){
				xvel = 4 * Math.random() - 2;
				yvel = 2 * Math.random();
			}
			else if(dir == ExplosionDirection.NEUTRAL){
				xvel = 6 * Math.random() - 3;
				yvel = 6 * Math.random() - 3;
			}
			else if(dir == ExplosionDirection.UP){
				xvel = 4 * Math.random() - 2;
				yvel = - 2 * Math.random();
			}
			else{
				System.out.println("Invalid direction specified in Particle.explosion()");
				xvel = 0;
				yvel = 0;
			}

			explosionParticles.add(
				new Particle(x, y, xvel, yvel, fadeTime, animationMap));
		}

		return explosionParticles;
	}
}
