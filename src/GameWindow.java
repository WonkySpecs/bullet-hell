/**
* The GameWindow class is a JFrame extension that acts as
* the container for all other components of the program.
* GameWindow handles switching between menu and gameplay
* contexts
*
* @author  Will Taylor
* @version 0.1
* @since   17-07-2017
*/

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import src.levels.*;

public class GameWindow extends JFrame implements ActionListener{
	private Container content;
	private static JButton startButton = new JButton("Start");

	public static final int WINDOW_SCREEN_WIDTH = GamePlay.GAME_SCREEN_WIDTH;
	public static final int WINDOW_SCREEN_HEIGHT = GamePlay.GAME_SCREEN_HEIGHT + 120;

	public GameWindow(){
		super("As yet unnamed bullet hell game");
		setSize(WINDOW_SCREEN_WIDTH, WINDOW_SCREEN_HEIGHT);
		setLocation(40, 40);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		content = getContentPane();
		content.setLayout(new CardLayout());

		JPanel jp = new JPanel();
		jp.setSize(WINDOW_SCREEN_WIDTH, WINDOW_SCREEN_HEIGHT);
		startButton.addActionListener(this);
		jp.add(startButton);

		content.add(jp);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Object actionSource = e.getSource();

		if(actionSource == startButton){
			newGamePlay(1);
		}
	}

	public void newGamePlay(int levelID){
		GamePlay gp = null;
		switch(levelID){
			case 1: 
				gp = new GamePlay(new GameLevelOne(getWidth(), getHeight()));
				break;
			default:
				gp = new GamePlay(new GameLevelOne(getWidth(), getHeight()));
				break;
		}

		gp.setFocusable(true);
		changeContentPane((JPanel) gp);
	}

	public void changeContentPane(JPanel newPanel){
		//Is casting like this bad practice? REVIEW
		JPanel jp = (JPanel) content;
		jp.removeAll();
		jp.add(newPanel);
		jp.revalidate();
		//jp.repaint();		Not sure if needed - seems to work without
	}

	public static void main(String[] args){
		GameWindow window = new GameWindow();
	}
}