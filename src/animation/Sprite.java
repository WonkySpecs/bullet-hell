/**
* Sprite is used as a static class to load 
* BufferedImages from sprite sheets.
* 
* @author  Will Taylor
* @version 0.1
* @since   25-07-2017
*/


package src.animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.File;

public class Sprite{
	private static final String SPRITE_FOLDER_PATH = System.getProperty("user.dir")
														+ File.separator + "bin"
														+ File.separator + "src"
														+ File.separator + "sprites";

	public static BufferedImage loadSprite(String file){
		BufferedImage sprite = null;

		try {
			sprite = ImageIO.read(new File(SPRITE_FOLDER_PATH + File.separator + file + ".png"));
		}
		catch (IOException e){
			e.printStackTrace();
		}

		return sprite;
	}

	public static BufferedImage getSprite(int xGrid, int yGrid, String spriteSheetFile, int tileWidth, int tileHeight){
		BufferedImage spriteSheet = loadSprite(spriteSheetFile);
	
		return spriteSheet.getSubimage(xGrid * tileWidth, yGrid * tileHeight, tileWidth, tileHeight);
	}

	public static BufferedImage getSprite(int xGrid, int yGrid, String spriteSheetFile, int tileSize){
		return getSprite(xGrid, yGrid, spriteSheetFile, tileSize, tileSize);
	}
}