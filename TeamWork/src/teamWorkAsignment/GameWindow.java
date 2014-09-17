package teamWorkAsignment;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
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
		msgScore();
		add(s);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new GameWindow();
	}

	
	private void msgScore() {
		 //  flowLayout, GridLayout, etc...
	    getContentPane().setLayout(new FlowLayout());
	    JLabel label = new JLabel("Text-Only Label");
	    label.setFont(new Font("Serif", Font.PLAIN, 18));
	    label.setBackground(Color.BLUE);
	    getContentPane().add(label);
	    setSize(30, 30);  //  or whatever size you want
	    //  Place Frame in middle of Screen
	    setLocationRelativeTo(null);  
	    setVisible(true);
	}
	
}
