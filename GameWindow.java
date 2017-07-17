import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame{
	private Container content;
	public GameWindow(){
		super("As yet unnamed bullet hell game");
		content = getContentPane();
		content.setLayout(new BorderLayout());
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void newGamePlay(GamePlay gp){
		content.add(gp, BorderLayout.CENTER);
	}

	public static void main(String[] args){
		GameWindow window = new GameWindow();
		GamePlay gp = new GamePlay(window, new GameLevel());
		gp.setFocusable(true);
		window.newGamePlay(gp);
	}	
}