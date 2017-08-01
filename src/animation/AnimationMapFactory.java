/**
* AnimationMapFactory provides the getAnimationMap function
* which loads the appropriate animation set for an object
* given the objects name. Designed to simplify definitions
* of GameObjects in other places (ie level definitions)
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

	public static final int ENEMY_SUICIDE_SMALL = 10;

	public static final int PROJ_PLAYER = 100;
	public static final int PROJ_TINY_BLUE = 101;
	public static final int PROJ_SMALL_BLUE = 102;
	public static final int PROJ_BLUE = 103;
	public static final int PROJ_BIG_BLUE = 104;


	public static HashMap<String, Animation> getAnimationMap(int objectType){
		HashMap<String, Animation> animationMap = new HashMap<>();

		BufferedImage[] spriteArray;

		switch(objectType){
			case PLAYER:
				spriteArray = new BufferedImage[] {Sprite.getSprite(0, 0, "player", 32), Sprite.getSprite(1, 0, "player", 32)};
				animationMap.put("neutral", new Animation(spriteArray, 6));
				break;



			case ENEMY_FLOATER_RED:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "enemyfloaterred", 32),
													Sprite.getSprite(1, 0, "enemyfloaterred", 32),
													Sprite.getSprite(2, 0, "enemyfloaterred", 32) };
				animationMap.put("neutral", new Animation(spriteArray, 6));
				break;

			case ENEMY_SUICIDE_SMALL:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "enemyyellowspinner", 16),
													Sprite.getSprite(1, 0, "enemyyellowspinner", 16),
													Sprite.getSprite(2, 0, "enemyyellowspinner", 16) };
				animationMap.put("neutral", new Animation(spriteArray, 3));
				break;



			case PROJ_PLAYER:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "playerproj", 8),
													Sprite.getSprite(1, 0, "playerproj", 8),
													Sprite.getSprite(2, 0, "playerproj", 8) };
				animationMap.put("neutral", new Animation(spriteArray, 4));
				break;

			case PROJ_TINY_BLUE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "projtinyblue", 4) };
				animationMap.put("neutral", new Animation(spriteArray, 1000));
				break;
		}

		//Should make this a thrown exception but error handling QQ
		if(animationMap.get("neutral") == null){
			System.out.println("No neutral animation specified when mapping animations");
		}

		return animationMap;
	}
}