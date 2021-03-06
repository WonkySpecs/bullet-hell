/**
* Animation defines which sprite should be shown
* at any given time for an object. The animation
* must be told to start and update by the object
* which uses it.
*
* @author  Will Taylor
* @version 0.1
* @since   25-07-2017
*/

package src.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation{
	private int frameCount, frameDelay, currentFrame, totalFrames;
	private boolean stopped;
	private ArrayList<Frame> frames;

	public Animation(BufferedImage[] frameImages, int frameDelay){
		this.frameDelay = frameDelay;
		this.stopped = true;
		this.frameCount = 0;
		this.currentFrame = 0;
		frames = new ArrayList<Frame>();

		for (int i = 0; i < frameImages.length; i++){
			addFrame(frameImages[i], frameDelay);
		}

		this.totalFrames = this.frames.size();
	}

	public Animation(Animation original){
		frameCount = 0;
		currentFrame = 0;
		stopped = true;
		frameDelay = original.getFrameDelay();
		frames = original.getFrames();
		totalFrames = frames.size();
	}

	public void start(){
		if (!stopped){
			return;
		}

		if (frames.size() == 0){
			return;
		}

		stopped = false;
	}

	public void stop(){
		if (frames.size() == 0){
			return;
		}

		stopped = true;
	}

	public void restart(){
		if (frames.size() == 0){
			return;
		}

		stopped = false;
		currentFrame = 0;
	}

	public void reset(){
		this.stopped = true;
		this.frameCount = 0;
		this.currentFrame = 0;
	}

	private void addFrame(BufferedImage frameImage, int duration){
		if (duration <= 0){
			System.err.println("Invalid duration: " + duration);
			throw new RuntimeException("Invalid duration: " + duration);
		}

		frames.add(new Frame(frameImage, duration));
		currentFrame = 0;
	}

	public BufferedImage getSprite(){
		return frames.get(currentFrame).getFrame();
	}

	public void update(){
		if (!stopped){
			frameCount++;

			if (frameCount > frameDelay){
				frameCount = 0;
				currentFrame += 1;

				if (currentFrame > totalFrames - 1){
					currentFrame = 0;
				}
			}
		}
	}

	public int getFrameDelay(){
		return frameDelay;
	}

	//NOTE: When used in copy constructor, I believe this returns the same Frame
	//instances as the original uses, technically making the copy a shallow copy.
	//This shouldn't be a problem as the frames of the animations do not change
	public ArrayList<Frame> getFrames(){
		return frames;
	}
}
