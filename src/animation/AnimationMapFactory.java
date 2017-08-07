/**
* AnimationMapFactory provides the getAnimationMap function
* which loads the appropriate animation set for an object
* given the objects name. Designed to simplify definitions
* of GameObjects in other places (ie level definitions).
*
* @author  Will Taylor
* @version 0.1
* @since   25-07-2017
*/

package src.animation;

import java.util.HashMap;
import java.awt.image.BufferedImage;

public class AnimationMapFactory{

	public static final int PLAYER = 1;

	public static final int ENEMY_FLOATER_RED = 2;
	public static final int ENEMY_FLOATER_GREEN = 3;
	public static final int ENEMY_FLOATER_BLUE = 4;

	public static final int ENEMY_FLOATER_BIG_RED = 11;
	public static final int ENEMY_FLOATER_BIG_GREEN = 12;
	public static final int ENEMY_FLOATER_BIG_BLUE = 13;

	public static final int ENEMY_SUICIDE_SMALL = 10;

	public static final int PROJ_PLAYER = 100;
	public static final int PROJ_TINY_BLUE = 101;
	public static final int PROJ_SMALL_BLUE = 102;
	public static final int PROJ_BLUE = 103;
	public static final int PROJ_BIG_BLUE = 104;

	public static final int ITEM_UPGRADE = 200;
	public static final int ITEM_POINTS_SMALL = 201;
	public static final int ITEM_POINTS_BIG = 202;

	public static HashMap<String, Animation> getAnimationMap(int objectType){
		HashMap<String, Animation> animationMap = new HashMap<>();

		BufferedImage[] spriteArray;

		switch(objectType){
			case PLAYER:
				spriteArray = new BufferedImage[] {Sprite.getSprite(0, 0, "player", 32), Sprite.getSprite(1, 0, "player", 32)};
				animationMap.put("neutral", new Animation(spriteArray, 12));
				break;



			case ENEMY_FLOATER_RED:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "enemysmallred", 32),
													Sprite.getSprite(1, 0, "enemysmallred", 32),
													Sprite.getSprite(2, 0, "enemysmallred", 32) };
				animationMap.put("neutral", new Animation(spriteArray, 15));
				break;

			case ENEMY_FLOATER_BIG_BLUE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "enemybigblue", 64),
													Sprite.getSprite(1, 0, "enemybigblue", 64),
													Sprite.getSprite(2, 0, "enemybigblue", 64) };

				animationMap.put("neutral", new Animation(spriteArray, 20));

				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 1, "enemybigblue", 64),
													Sprite.getSprite(1, 1, "enemybigblue", 64),
													Sprite.getSprite(2, 1, "enemybigblue", 64) };

				animationMap.put("damaged", new Animation(spriteArray, 8));
				break;

			case ENEMY_SUICIDE_SMALL:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "enemyyellowspinner", 16),
													Sprite.getSprite(1, 0, "enemyyellowspinner", 16),
													Sprite.getSprite(2, 0, "enemyyellowspinner", 16) };
				animationMap.put("neutral", new Animation(spriteArray, 10));
				break;



			case PROJ_PLAYER:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "playerproj", 8),
													Sprite.getSprite(1, 0, "playerproj", 8),
													Sprite.getSprite(2, 0, "playerproj", 8) };
				animationMap.put("neutral", new Animation(spriteArray, 20));
				break;

			case PROJ_TINY_BLUE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "projtinyblue", 4) };
				animationMap.put("neutral", new Animation(spriteArray, 1000));
				break;



			case ITEM_UPGRADE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "itemupgrade", 16),
													Sprite.getSprite(1, 0, "itemupgrade", 16),
													Sprite.getSprite(2, 0, "itemupgrade", 16),
													Sprite.getSprite(3, 0, "itemupgrade", 16),
													Sprite.getSprite(4, 0, "itemupgrade", 16),
													Sprite.getSprite(5, 0, "itemupgrade", 16) };
				animationMap.put("neutral", new Animation(spriteArray, 8));
				break;
		}

		//Should make this a thrown exception but error handling QQ
		if(animationMap.get("neutral") == null){
			System.out.println("No neutral animation specified when mapping animations");
		}

		return animationMap;
	}

	//Creates deep copy (new instances of Animations using the copy constructor) of animation map
	public static HashMap<String, Animation> copyAnimationMap(HashMap<String, Animation> original){
		HashMap<String, Animation> copy = new HashMap<>();

		for(String k : original.keySet()){
			copy.put(k, new Animation(original.get(k)));
		}

		return copy;
	}
}
