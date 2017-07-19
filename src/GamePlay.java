package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.HashMap;

public class GamePlay extends JPanel{
	private GamePlayLogic logic;
	private int paintCount;
	private boolean running;
	private long gameTime;

	private HashMap<String, Boolean> buttonsPressed;

	private static final String PRESS_UP = "press up";
    private static final String PRESS_DOWN = "press down";
    private static final String PRESS_LEFT = "press left";
    private static final String PRESS_RIGHT = "press right";
    private static final String RELEASE_UP = "release up";
    private static final String RELEASE_DOWN = "release down";
    private static final String RELEASE_LEFT = "release left";
    private static final String RELEASE_RIGHT = "release right";

    private static final String DIR_UP = "up";
    private static final String DIR_DOWN = "down";
    private static final String DIR_LEFT = "left";
    private static final String DIR_RIGHT = "right";

	public GamePlay(GameWindow mainWindow, GameLevel level){
		paintCount = 0;
		logic = new GamePlayLogic(level);
		running = true;
		gameTime = 0;
		resetButtonsPressed();

		setBindings();
		startGameLoop();
	}

	public void startGameLoop(){
		System.out.println("start");
		Thread loop = new Thread(){
			public void run(){
			   gameLoop();
			}
		};
		loop.start();
	}

	public void gameLoop(){
		System.out.println("started");
		while(running){
			try{Thread.sleep(500);}catch(Exception e){}
			logic.update(gameTime, buttonsPressed);
			repaint();
			gameTime += 1;
		}
	}

	public void setBindings(){
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), PRESS_UP);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), PRESS_DOWN);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), PRESS_LEFT);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), PRESS_RIGHT);

		this.getActionMap().put(PRESS_UP, new DirPressedAction(DIR_UP));
		this.getActionMap().put(PRESS_DOWN, new DirPressedAction(DIR_DOWN));
		this.getActionMap().put(PRESS_LEFT, new DirPressedAction(DIR_LEFT));
		this.getActionMap().put(PRESS_RIGHT, new DirPressedAction(DIR_RIGHT));

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), RELEASE_UP);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), RELEASE_DOWN);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), RELEASE_LEFT);
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), RELEASE_RIGHT);

		this.getActionMap().put(RELEASE_UP, new DirReleasedAction(DIR_UP));
		this.getActionMap().put(RELEASE_DOWN, new DirReleasedAction(DIR_DOWN));
		this.getActionMap().put(RELEASE_LEFT, new DirReleasedAction(DIR_LEFT));
		this.getActionMap().put(RELEASE_RIGHT, new DirReleasedAction(DIR_RIGHT));
		System.out.println("Bindings set");
	}

	@Override
	public void paintComponent(Graphics g) {
		paintCount += 1;
		System.out.println(paintCount);
		super.paintComponent(g);     // paint parent's background
		setBackground(Color.BLACK);  // set background color for this JPanel
	
		// Your custom painting codes. For example,
		// Drawing primitive shapes
		g.setColor(Color.YELLOW);    // set the drawing color
		g.drawLine(30, 40, 100, 200);
		g.drawOval(150, 180, 10, 10);
		g.drawRect(200, 210, 20, 30);
		g.setColor(Color.RED);       // change the drawing color
		g.fillOval(300, 310, 30, 50);
		g.fillRect(400, 350, 60, 50);
		// Printing texts
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.PLAIN, 12));
		g.drawString("Testing custom drawing ...", 10, 20);
		g.drawString(String.format("%d", paintCount), 10, 40);
	}

	private void resetButtonsPressed(){
		buttonsPressed = new HashMap<String, Boolean>(8);
		buttonsPressed.put("up", false);
		buttonsPressed.put("down", false);
		buttonsPressed.put("left", false);
		buttonsPressed.put("right", false);
		buttonsPressed.put("fire_prim", false);
		buttonsPressed.put("fire_sec", false);
		buttonsPressed.put("special", false);
	}

	private class DirPressedAction extends AbstractAction{
		String direction;

		DirPressedAction(String dir){
			direction = dir;
		}

		public void actionPerformed(ActionEvent e) {
			buttonsPressed.put(direction, true);
		}
	}

	private class DirReleasedAction extends AbstractAction{
		String direction;

		DirReleasedAction(String dir){
			direction = dir;
		}

		public void actionPerformed(ActionEvent e) {
			buttonsPressed.put(direction, false);
		}
	}
}