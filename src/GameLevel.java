/**
* The GameLevel class provides a wrapper for all of the
* data required to define a 'level' ie. where when and
* what type of enemies spawn.
*
* @author  Will Taylor
* @version 0.1
* @since   17-07-2017
*/

package src;

import java.util.ArrayList;
import java.util.HashMap;

import src.gameobjects.EnemyShip;
import src.animation.*;

public abstract class GameLevel{
	private int screenWidth, screenHeight;
	private boolean bossSpawned;
	private HashMap<Integer, HashMap<String, Animation>> animations;

	public GameLevel(int screenWidth, int screenHeight, Integer[] animationsToLoad){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		bossSpawned = false;
		animations = preloadAnimations(animationsToLoad);
	}

	public int getScreenWidth(){
		return screenWidth;
	}

	public int getScreenHeight(){
		return screenHeight;
	}

	public boolean isBossSpawned(){
		return bossSpawned;
	}

	public void setBossSpawned(boolean value){
		bossSpawned = value;
	}

	public abstract ArrayList<EnemyShip> getEnemySpawns(long gameTime);

	public HashMap<Integer, HashMap<String, Animation>> preloadAnimations(Integer[] animationsToLoad){
		HashMap<Integer, HashMap<String, Animation>> animationMapMap = new HashMap<>();
		for(Integer animation : animationsToLoad){
			animationMapMap.put(animation, AnimationMapFactory.getAnimationMap(animation));
		}
		return animationMapMap;
	}

	public HashMap<String, Animation> getPreloadedAnimationMap(Integer animation){
		return AnimationMapFactory.copyAnimationMap(animations.get(animation));
	}
}