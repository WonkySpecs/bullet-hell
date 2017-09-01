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
	public static final int ENEMY_SUICIDE_SMALL = 20;
	public static final int ENEMY_HYPNOSQUARE = 30;
	public static final int ENEMY_BOMB = 31;
	public static final int ENEMY_BOSS_DROID = 50;

	public static final int PROJ_PLAYER_PRIM_1 = 100;
	public static final int PROJ_PLAYER_PRIM_2 = 101;
	public static final int PROJ_PLAYER_PRIM_3 = 102;
	public static final int PROJ_TINY_BLUE = 111;
	public static final int PROJ_SMALL_BLUE = 112;
	public static final int PROJ_BLUE = 113;
	public static final int PROJ_BIG_BLUE = 114;

	public static final int ITEM_UPGRADE = 200;
	public static final int ITEM_POINTS_SMALL = 201;
	public static final int ITEM_POINTS_BIG = 202;

	public static final int PARTICLE_SMALL_GREEN = 301;
	public static final int PARTICLE_GREEN = 302;
	public static final int PARTICLE_BIG_GREEN = 303;
	public static final int PARTICLE_SMALL_BLUE = 304;
	public static final int PARTICLE_BLUE = 305;
	public static final int PARTICLE_BIG_BLUE = 306;
	public static final int PARTICLE_SMALL_RED = 307;
	public static final int PARTICLE_RED = 308;
	public static final int PARTICLE_BIG_RED = 309;
	public static final int PARTICLE_SMALL_WHITE = 310;
	public static final int PARTICLE_WHITE = 311;
	public static final int PARTICLE_BIG_WHITE = 312;

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

			case ENEMY_HYPNOSQUARE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "enemyhypnosquare", 16),
													Sprite.getSprite(1, 0, "enemyhypnosquare", 16),
													Sprite.getSprite(2, 0, "enemyhypnosquare", 16),
													Sprite.getSprite(3, 0, "enemyhypnosquare", 16),
													Sprite.getSprite(4, 0, "enemyhypnosquare", 16),
													Sprite.getSprite(5, 0, "enemyhypnosquare", 16),
													Sprite.getSprite(6, 0, "enemyhypnosquare", 16),
													Sprite.getSprite(7, 0, "enemyhypnosquare", 16),
													Sprite.getSprite(8, 0, "enemyhypnosquare", 16),
													Sprite.getSprite(9, 0, "enemyhypnosquare", 16)};
				animationMap.put("neutral", new Animation(spriteArray, 10));
				break;

			case ENEMY_BOMB:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "enemybomb", 9),
													Sprite.getSprite(1, 0, "enemybomb", 9)};
				animationMap.put("neutral", new Animation(spriteArray, 40));

				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 1, "enemybomb", 9),
													Sprite.getSprite(1, 1, "enemybomb", 9)};
				animationMap.put("frenzy", new Animation(spriteArray, 4));
				break;



			case ENEMY_BOSS_DROID:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "enemyboss1", 300, 160),
													Sprite.getSprite(1, 0, "enemyboss1", 300, 160)};
				animationMap.put("neutral", new Animation(spriteArray, 10));

				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 1, "enemyboss1", 300, 160),
													Sprite.getSprite(1, 1, "enemyboss1", 300, 160)};
				animationMap.put("frenzy", new Animation(spriteArray, 10));
				break;

				

			case PROJ_PLAYER_PRIM_1:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "projplayerprim1", 4)};
				animationMap.put("neutral", new Animation(spriteArray, 20));
				break;

			case PROJ_PLAYER_PRIM_2:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "projplayerprim2", 8)};
				animationMap.put("neutral", new Animation(spriteArray, 20));
				break;

			case PROJ_PLAYER_PRIM_3:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "projplayerprim3", 8)};
				animationMap.put("neutral", new Animation(spriteArray, 20));
				break;

			case PROJ_TINY_BLUE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "projtinyblue", 4) };
				animationMap.put("neutral", new Animation(spriteArray, 1000));
				break;

			case PROJ_BLUE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "projblue", 8) };
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



			case PARTICLE_SMALL_GREEN:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particlegreen", 1) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_GREEN:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particlegreen", 2) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_BIG_GREEN:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particlegreen", 4) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_SMALL_BLUE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particleblue", 1) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_BLUE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particleblue", 2) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_BIG_BLUE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particleblue", 4) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_SMALL_RED:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particlered", 1) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_RED:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particlered", 2) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_BIG_RED:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particlered", 4) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_SMALL_WHITE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particlewhite", 1) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_WHITE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particlewhite", 2) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
				break;

			case PARTICLE_BIG_WHITE:
				spriteArray = new BufferedImage[] { Sprite.getSprite(0, 0, "particlewhite", 4) };
				animationMap.put("neutral", new Animation(spriteArray, 500));
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
