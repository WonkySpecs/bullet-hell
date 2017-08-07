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
		SMALL_GREEN, BIG_GREEN,
		SMALL_BLUE, BIG_BLUE;
	}

	public static ArrayList<Particle> explosion(ExplosionType explosionType, double x, double y){
		ArrayList<Particle> explosionParticles = new ArrayList<>();
		int numParticles;

		switch(explosionType){
			case SMALL_GREEN:
				numParticles = (int)Math.round(Math.random() * 5 + 5);
				for(int i = 0; i < numParticles; i++){
					explosionParticles.add(
						new Particle(x, y, 4 * Math.random() - 2, 2 * Math.random(), 20, AnimationMapFactory.getAnimationMap(AnimationMapFactory.PARTICLE_SMALL_GREEN)));
				}
				break;

			case BIG_GREEN:
				numParticles = (int)Math.round(Math.random() * 8 + 10);
				for(int i = 0; i < numParticles; i++){
					explosionParticles.add(
						new Particle(x, y, 4 * Math.random() - 2, 2 * Math.random(), 20, AnimationMapFactory.getAnimationMap(AnimationMapFactory.PARTICLE_GREEN)));
				}
				break;

			default:
				System.out.println("Nonexistent explosion type specified in Particle.explosion()");
				explosionParticles = null;
				break;
		}

		return explosionParticles;
	}
}
