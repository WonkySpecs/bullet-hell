import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePlay extends JPanel{
	private GamePlayLogic logic;
	private int paintCount;
	private boolean running;
	private static final String MOVE_UP = "move up";
    private static final String MOVE_DOWN = "move down";
    private static final String MOVE_LEFT = "move left";
    private static final String MOVE_RIGHT = "move right";

    private static final int DIR_UP = 0;
    private static final int DIR_DOWN = 1;
    private static final int DIR_LEFT = 2;
    private static final int DIR_RIGHT = 3;

	public GamePlay(GameWindow mainWindow, GameLevel level){
		paintCount = 0;
		logic = new GamePlayLogic(level);
		running = true;
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
			try{Thread.sleep(1000);}catch(Exception e){}
			System.out.println("asdf");
		}
	}

	public void setBindings(){
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);

		this.getActionMap().put(MOVE_UP, new MoveAction(DIR_UP));
		this.getActionMap().put(MOVE_DOWN, new MoveAction(DIR_DOWN));
		this.getActionMap().put(MOVE_LEFT, new MoveAction(DIR_LEFT));
		this.getActionMap().put(MOVE_RIGHT, new MoveAction(DIR_RIGHT));
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

	private class MoveAction extends AbstractAction{
		int direction;

		MoveAction(int dir){
			direction = dir;
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println(Integer.toString(direction));
		}
	}
}