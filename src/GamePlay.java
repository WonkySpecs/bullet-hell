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
import javax.swing.Box;
import java.awt.*;
import java.awt.event.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class GamePlay extends JPanel implements ActionListener{
	private GamePlayLogic logic;
	private GameWindow window;
	private int fps;
	private long gameTime;
	
	//Must be volatile to ensure the game thread and the drawing thread
	//see the same value.
	private volatile boolean running, paused, pauseMenuDrawn;

	private HashMap<String, Boolean> buttonsPressed;

	private ArrayList<SpriteData> spriteList;

	private static JButton resumeButton = new JButton("Resume");
	private static JButton mainMenuButton = new JButton("Main Menu");

	private static final int MENU_X_BORDER = 120;
	private static final int MENU_Y_BORDER = 150;

	private static final String PRESS_UP = "press up";
	private static final String PRESS_DOWN = "press down";
	private static final String PRESS_LEFT = "press left";
	private static final String PRESS_RIGHT = "press right";
	private static final String RELEASE_UP = "release up";
	private static final String RELEASE_DOWN = "release down";
	private static final String RELEASE_LEFT = "release left";
	private static final String RELEASE_RIGHT = "release right";

	private static final String PRESS_ESC = "press esc";
	private static final String RELEASE_ESC = "release esc";

	private static final String PRESS_FIRE_PRIM = "press fire_prim";
	private static final String PRESS_FIRE_SEC = "press fire_sec";
	private static final String PRESS_FIRE_SPECIAL = "press fire_special";
	private static final String RELEASE_FIRE_PRIM = "release fire_prim";
	private static final String RELEASE_FIRE_SEC = "release fire_sec";
	private static final String RELEASE_FIRE_SPECIAL = "release fire_special";

	public static final String ACT_UP = "up";
	public static final String ACT_DOWN = "down";
	public static final String ACT_LEFT = "left";
	public static final String ACT_RIGHT = "right";
	public static final String ACT_FIRE_PRIM = "prim";
	public static final String ACT_FIRE_SEC = "sec";
	public static final String ACT_FIRE_SPECIAL = "special";
	public static final String ACT_PAUSE = "pause";

	public static final int GAME_SCREEN_WIDTH = 600;
	public static final int GAME_SCREEN_HEIGHT = 640;

	public GamePlay(GameWindow window, GameLevel level){
		this.window = window;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		logic = new GamePlayLogic(level, GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
		setUpMenu();
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
		//NOTE: 1000000000 is the number of nanoseconds in a second
		final int TARGET_FPS = 60;
		final long TARGET_FRAME_TIME = 1000000000 / TARGET_FPS;
		long lastUpdateTime = System.nanoTime();
		long lastFpsCountTime = System.nanoTime();
		long pauseTime = System.nanoTime();
		int framesSinceLastCount = 0;
		paused = false;
		pauseMenuDrawn = false;

		while(running){
			if(buttonsPressed.get(ACT_PAUSE) && (System.nanoTime() - pauseTime) > 300000000){
				if(paused){
					unpause();
				}
				else{
					pause();
				}
				pauseTime = System.nanoTime();
			}
			if(!paused){
				try{
					//If update has taken less time than the target time, wait the appropriate time.
					Thread.sleep((TARGET_FRAME_TIME - (System.nanoTime() - lastUpdateTime)) / 1000000);
				}catch(Exception e){}

				logic.update(gameTime, buttonsPressed);
				spriteList = logic.getSpriteDataList();
				repaint();

				lastUpdateTime = System.nanoTime();
				gameTime += 1;
				framesSinceLastCount += 1;

				if((System.nanoTime() - lastFpsCountTime) >= 1000000000){
					fps = framesSinceLastCount;
					framesSinceLastCount = 0;
					lastFpsCountTime = System.nanoTime();
				}
			}
			else{
				if(!pauseMenuDrawn){
					//Not sure if this should be here or in paintComponent
					//This gets ran multiple times if set in paintComponent but would be guaranteed
					repaint();
				}
			}
		}

		window.displayMainMenu();
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

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), PRESS_ESC);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released ESCAPE"), RELEASE_ESC);

		this.getActionMap().put(PRESS_ESC, new KeyPressedAction(ACT_PAUSE));
		this.getActionMap().put(RELEASE_ESC, new KeyReleasedAction(ACT_PAUSE));

		System.out.println("Bindings set");
	}

	private void setUpMenu(){
		final int BUTTON_SPACING = 25;
		this.add(Box.createRigidArea(new Dimension(0, MENU_Y_BORDER + 50)));
		resumeButton.addActionListener(this);
		//resumeButton.setLocation(GAME_SCREEN_WIDTH / 2 - resumeButton.getWidth() / 2, 200);
		resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(resumeButton);
		resumeButton.setVisible(false);

		this.add(Box.createRigidArea(new Dimension(0, BUTTON_SPACING)));

		mainMenuButton.addActionListener(this);
		//mainMenuButton.setLocation(GAME_SCREEN_WIDTH / 2 - mainMenuButton.getWidth() / 2, 300);
		mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(mainMenuButton);
		mainMenuButton.setVisible(false);

		this.add(Box.createRigidArea(new Dimension(0, MENU_Y_BORDER + 50)));
	}

	private void pause(){
		paused = true;
		resumeButton.setVisible(true);
		mainMenuButton.setVisible(true);
	}

	private void unpause(){
		paused = false;
		resumeButton.setVisible(false);
		mainMenuButton.setVisible(false);
	}

	private void endGame(){
		running = false;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);     // paint parent's background
		setBackground(Color.BLACK);  // set background color for this JPanel

		if(spriteList != null){
			int minDepth = 10;
			int maxDepth = -1;
			for(SpriteData sd : spriteList){
				if(sd.getDepth() < minDepth){
					minDepth = sd.getDepth();
				}

				if(sd.getDepth() > maxDepth){
					maxDepth = sd.getDepth();
				}
			}
			
			for(int curDepth = maxDepth; curDepth >= minDepth; curDepth--){
				for(SpriteData sd : spriteList){
					if(sd.getDepth() == curDepth){
						BufferedImage img = sd.getSprite();
						int x = (int)Math.round(sd.getPos().getX());
						int y = (int)Math.round(sd.getPos().getY());
						g2.setComposite(AlphaComposite.SrcOver.derive(sd.getAlpha()));
						g2.drawImage(img, x, y, null);						
					}
				}				
			}
		}

		paintGUIElements(g2);

		if(paused){
			g2.setColor(Color.BLACK);
			g2.fillRect(MENU_X_BORDER, MENU_Y_BORDER, GAME_SCREEN_WIDTH - MENU_X_BORDER * 2,  GAME_SCREEN_HEIGHT - MENU_Y_BORDER * 2);
			g2.setColor(Color.RED);
			g2.drawRect(MENU_X_BORDER, MENU_Y_BORDER, GAME_SCREEN_WIDTH - MENU_X_BORDER * 2,  GAME_SCREEN_HEIGHT - MENU_Y_BORDER * 2);
			pauseMenuDrawn = true;
		}
	}

	public void paintGUIElements(Graphics2D g2){
		//Bottom GUI panel
		g2.setColor(Color.GRAY);
		g2.fillRect(0, GAME_SCREEN_HEIGHT, GameWindow.WINDOW_SCREEN_WIDTH, GameWindow.WINDOW_SCREEN_HEIGHT);

		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Monospaced", Font.PLAIN, 14));

		g2.drawString(String.format("SCORE: %d", logic.getScore()), 50, GAME_SCREEN_HEIGHT + 30);
		g2.drawString(String.format("SCORE MULTIPLIER: %.1f", logic.getScoreMult()), 50, GAME_SCREEN_HEIGHT + 60);
		

		//FPS counter
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		g2.drawString(String.format("%d fps", fps), GAME_SCREEN_WIDTH - 50, 10);		
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Object actionSource = e.getSource();

		//Can't switch on an Object
		if(actionSource == resumeButton){
			unpause();
		}
		else if(actionSource == mainMenuButton){
			//TODO: Put confirmation message?
			endGame();
		}
		else{
			System.out.println("Action performed in GamePlay by unhandled object:");
			System.out.println(actionSource);
		}
	}

	private void resetAllButtonsPressed(){
		buttonsPressed = new HashMap<String, Boolean>(8);
		buttonsPressed.put(ACT_UP, false);
		buttonsPressed.put(ACT_DOWN , false);
		buttonsPressed.put(ACT_LEFT , false);
		buttonsPressed.put(ACT_RIGHT, false);
		buttonsPressed.put(ACT_FIRE_PRIM, false);
		buttonsPressed.put(ACT_FIRE_SEC, false);
		buttonsPressed.put(ACT_FIRE_SPECIAL, false);
		buttonsPressed.put(ACT_PAUSE, false);
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
