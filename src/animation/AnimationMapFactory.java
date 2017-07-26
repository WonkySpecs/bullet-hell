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
	public static HashMap<String, Animation> getAnimationMap(String objectName){
		HashMap<String, Animation> animationMap = new HashMap<>();

		BufferedImage[] spriteArray;

		switch(objectName){
			case "player":
				spriteArray = new BufferedImage[] {Sprite.getSprite(0, 0, "player", 32), Sprite.getSprite(1, 0, "player", 32)};
				animationMap.put("neutral", new Animation(spriteArray, 6));
				break;

			case "enemyfloater1":
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "enemyfloater1", 32),
													Sprite.getSprite(1, 0, "enemyfloater1", 32),
													Sprite.getSprite(2, 0, "enemyfloater1", 32) };
				animationMap.put("neutral", new Animation(spriteArray, 6));
				break;

			case "playerprojectile":
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "playerproj", 8),
													Sprite.getSprite(1, 0, "playerproj", 8),
													Sprite.getSprite(2, 0, "playerproj", 8) };
				animationMap.put("neutral", new Animation(spriteArray, 4));
				break;
		}

		//Should make this a thrown exception but error handling QQ
		if(animationMap.get("neutral") == null){
			System.out.println("No neutral animation specified when ampping animations");
		}

		return animationMap;
	}
}