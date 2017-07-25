/**
* Frame is the basic data structure used to build
* Animation objects. Stores a BufferedImage and
* the duration the image should be shown for
* 
*
* @author  Will Taylor
* @version 0.1
* @since   25-07-2017
*/


package src.animation;

import java.awt.image.BufferedImage;

public class Frame{
	private BufferedImage frame;
	private int duration;

	public Frame(BufferedImage frame, int duration){
		this.frame = frame;
		this.duration = duration;
	}

	public BufferedImage getFrame(){
		return frame;
	}

	public void setFrame(BufferedImage frame){
		this.frame = frame;
	}

	public int getDuration(){
		return duration;
	}

	public void setDuration(int duration){
		this.duration = duration;
	}
}