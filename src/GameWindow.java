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

public class GameWindow extends JFrame implements ActionListener{
	private Container content;
	private static JButton startButton = new JButton("Start");

	public GameWindow(){
		super("As yet unnamed bullet hell game");
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		content = getContentPane();
		content.setLayout(new BorderLayout());

		JPanel jp = new JPanel();
		startButton.addActionListener(this);
		jp.add(startButton);

		content.add(jp, BorderLayout.CENTER);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Object actionSource = e.getSource();

		if(actionSource == startButton){
			newGamePlay();
		}
	}

	public void newGamePlay(){
		GamePlay gp = new GamePlay(this, new GameLevel());
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