/**
* The GamePlay class is a JPanel extension that acts as the
* main 'controller' class during gameplay. An instance of
* GamePlay stored in a GameWindow instance handles user
* with key bindings, passes these and the time to a
* GamePlayLofic instance, which handles ingame processing,
* before rendering the result.
* 
*
* @author  Will Taylor
* @version 0.1
* @since   19-07-2017
*/

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class GamePlay extends JPanel{
	private GamePlayLogic logic;
	private int paintCount;
	private boolean running;
	private long gameTime;

	private HashMap<String, Boolean> buttonsPressed;

	private ArrayList<SpriteData> spriteList;

	private static final String PRESS_UP = "press up";
	private static final String PRESS_DOWN = "press down";
	private static final String PRESS_LEFT = "press left";
	private static final String PRESS_RIGHT = "press right";
	private static final String RELEASE_UP = "release up";
	private static final String RELEASE_DOWN = "release down";
	private static final String RELEASE_LEFT = "release left";
	private static final String RELEASE_RIGHT = "release right";

	public static final String PRESS_FIRE_PRIM = "press fire_prim";
	public static final String PRESS_FIRE_SEC = "press fire_sec";
	public static final String PRESS_FIRE_SPECIAL = "press fire_special";
	public static final String RELEASE_FIRE_PRIM = "release fire_prim";
	public static final String RELEASE_FIRE_SEC = "release fire_sec";
	public static final String RELEASE_FIRE_SPECIAL = "release fire_special";

	public static final String ACT_UP = "up";
	public static final String ACT_DOWN = "down";
	public static final String ACT_LEFT = "left";
	public static final String ACT_RIGHT = "right";
	public static final String ACT_FIRE_PRIM = "prim";
	public static final String ACT_FIRE_SEC = "sec";
	public static final String ACT_FIRE_SPECIAL = "special";


	public GamePlay(GameWindow mainWindow, GameLevel level){
		paintCount = 0;
		logic = new GamePlayLogic(level);
		running = true;
		gameTime = 0;
		resetAllButtonsPressed();

		setBindings();
		startGameLoop();
	}

	public void startGameLoop(){
		Thread loop = new Thread(){
			public void run(){
			   gameLoop();
			}
		};
		loop.start();
	}

	public void gameLoop(){
		while(running){
			try{Thread.sleep(20);}catch(Exception e){}
			logic.update(gameTime, buttonsPressed);
			spriteList = logic.getSpriteList();
			repaint();
			gameTime += 1;
		}
	}

	public void setBindings(){
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), PRESS_UP);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), PRESS_DOWN);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), PRESS_LEFT);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), PRESS_RIGHT);

		this.getActionMap().put(PRESS_UP, new KeyPressedAction(ACT_UP));
		this.getActionMap().put(PRESS_DOWN, new KeyPressedAction(ACT_DOWN));
		this.getActionMap().put(PRESS_LEFT, new KeyPressedAction(ACT_LEFT));
		this.getActionMap().put(PRESS_RIGHT, new KeyPressedAction(ACT_RIGHT));

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), RELEASE_UP);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), RELEASE_DOWN);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), RELEASE_LEFT);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), RELEASE_RIGHT);

		this.getActionMap().put(RELEASE_UP, new KeyReleasedAction(ACT_UP));
		this.getActionMap().put(RELEASE_DOWN, new KeyReleasedAction(ACT_DOWN));
		this.getActionMap().put(RELEASE_LEFT, new KeyReleasedAction(ACT_LEFT));
		this.getActionMap().put(RELEASE_RIGHT, new KeyReleasedAction(ACT_RIGHT));

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Z"), PRESS_FIRE_PRIM);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("X"), PRESS_FIRE_SEC);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), PRESS_FIRE_SPECIAL);

		this.getActionMap().put(PRESS_FIRE_PRIM, new KeyPressedAction(ACT_FIRE_PRIM));
		this.getActionMap().put(PRESS_FIRE_SEC, new KeyPressedAction(ACT_FIRE_SEC));
		this.getActionMap().put(PRESS_FIRE_SPECIAL, new KeyPressedAction(ACT_FIRE_SPECIAL));

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released Z"), RELEASE_FIRE_PRIM);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released X"), RELEASE_FIRE_SEC);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released SPACE"), RELEASE_FIRE_SPECIAL);

		this.getActionMap().put(RELEASE_FIRE_PRIM, new KeyReleasedAction(ACT_FIRE_PRIM));
		this.getActionMap().put(RELEASE_FIRE_SEC, new KeyReleasedAction(ACT_FIRE_SEC));
		this.getActionMap().put(RELEASE_FIRE_SPECIAL, new KeyReleasedAction(ACT_FIRE_SPECIAL));
		System.out.println("Bindings set");
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);     // paint parent's background
		setBackground(Color.BLACK);  // set background color for this JPanel

		// Your custom painting codes. For example,
		// Drawing primitive shapes
		//EXAMPLE CODE FROM GOOGLE SEARCHING
		/*g2.setColor(Color.YELLOW);    // set the drawing color
		g2.drawLine(30, 40, 100, 200);
		g2.drawOval(150, 180, 10, 10);
		g2.drawRect(200, 210, 20, 30);
		g2.setColor(Color.BLUE);       // change the drawing color
		g2.fillOval(300, 310, 30, 50);
		g2.fillRect(400, 350, 60, 50);
		// Printing texts
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		g2.drawString("Testing custom drawing ...", 10, 20);
		g2.drawString(String.format("%d", paintCount), 10, 40);*/
		
		if(spriteList != null){
			for(SpriteData sd : spriteList){
				BufferedImage img = sd.getSprite();
				int x = (int)sd.getPos().getX();
				int y = (int)sd.getPos().getY();
				g2.drawImage(img, x, y, null);
			}			
		}
	}

	private void resetAllButtonsPressed(){
		buttonsPressed = new HashMap<String, Boolean>(7);
		buttonsPressed.put(ACT_UP, false);
		buttonsPressed.put(ACT_DOWN , false);
		buttonsPressed.put(ACT_LEFT , false);
		buttonsPressed.put(ACT_RIGHT, false);
		buttonsPressed.put(ACT_FIRE_PRIM, false);
		buttonsPressed.put(ACT_FIRE_SEC, false);
		buttonsPressed.put(ACT_FIRE_SPECIAL, false);	
	}

	private class KeyPressedAction extends AbstractAction{
		String action;

		KeyPressedAction(String act){
			action = act;
		}

		public void actionPerformed(ActionEvent e) {
			buttonsPressed.put(action, true);
		}
	}

	private class KeyReleasedAction extends AbstractAction{
		String action;

		KeyReleasedAction(String act){
			action = act;
		}

		public void actionPerformed(ActionEvent e) {
			buttonsPressed.put(action, false);
		}
	}
}