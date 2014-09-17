package teamWorkAsignment;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class GameWindow extends JFrame{

	public GameWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake");
		setResizable(false);
		init();
	}
	public void init(){
		setLayout(new GridLayout(1, 1, 0, 0));
		Screen s = new Screen();
		add(s);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new GameWindow();
	}

}
