package teamWorkAsignment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {

	private static final long serialversionUID = 1L;
	public static final int WIDTH = 600, HEIGHT = 600;

	JLabel label1 = new JLabel("Test");

	private Thread thread;
	private boolean running = false;

	private boolean isSnake1Alive = true;
	private boolean isSnake2Alive = true;

	private SnakeBody b;
	private SnakeBody b2;
	private ArrayList<SnakeBody> snake;
	private ArrayList<SnakeBody> snake2;
	private Apple apple;
	private ArrayList<Apple> apples;

	private Random r;

	private int x = 10, y = 10;
	private int x2 = 30, y2 = 30;
	private int size = 5;
	private int size2 =5;

	private boolean right = true, left = false, up = false, down = false;
	private boolean right2 = false, left2 = true, up2 = false, down2 = false;

	private int ticks = 0;
	private Key key;

	public void Message(String title, String msg) {
		JOptionPane.showMessageDialog(null, msg, title,
				JOptionPane.ERROR_MESSAGE);
	}

	public Screen() {
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		r = new Random();

		snake = new ArrayList<SnakeBody>();
		snake2 = new ArrayList<SnakeBody>();
		apples = new ArrayList<Apple>();

		start();
	}

	public void tick() {
		if ((snake.size() == 0) && isSnake1Alive) {
			b = new SnakeBody(x, y, 12);
			snake.add(b);
		}

		if ((snake2.size() == 0) && isSnake2Alive){
			b2 = new SnakeBody(x2, y2, 12);
			snake2.add(b2);
		}

		if (apples.size() == 0) {
			int x = r.nextInt(49);
			int y = r.nextInt(49);

			apple = new Apple(x, y, 12);
			apples.add(apple);
		}
		for (int i = 0; i < apples.size(); i++) {
			if (x == apples.get(i).getX() && y == apples.get(i).getY()) {
				size++;
				apples.remove(i);
				i--;
			}
			if (x2 == apples.get(i).getX() && y2 == apples.get(i).getY()) {
				size2++;
				apples.remove(i);
				i--;
			}
		}
		if(isSnake1Alive){
			for (int i = 0; i < snake.size(); i++) {
				if (x == snake.get(i).getX() && y == snake.get(i).getY()) {
					if (i != snake.size() - 1) {
						stop();
					}
				}
			}
		}
		if (isSnake2Alive) {
			for (int i = 0; i < snake2.size(); i++) {
				if (x2 == snake2.get(i).getX() && y2 == snake2.get(i).getY()) {
					if (i != snake2.size() - 1) {
						stop();
					}
				}
			}
		}
		if(x < 0 || x > 49 || y < 0 || y > 49){
			snake.clear();
			if(isSnake1Alive)
				Message("Game Over","Snake 1 Died!");
			isSnake1Alive=false;
		}
			if(x2 < 0 || x2 > 49 || y2 < 0 || y2 > 49){
			snake2.clear();
			if(isSnake2Alive)
				Message("Game Over","Snake 2 Died!");
			isSnake2Alive=false;
		}
		if (!isSnake1Alive && !isSnake2Alive){
				Message("Game Over","Succers!");
				stop();
			
		}

		ticks++;

		if (ticks > 1500000) {
			if (right)
				x++;
			if(right2)
				x2++;
			if (left)
				x--;
			if(left2)
				x2--;
			if (up)
				y--;
			if(up2)
				y2--;
			if (down)
				y++;
			if(down2)
				y2++;

			ticks = 0;
			if (isSnake1Alive) {
				b = new SnakeBody(x, y, 12);
				snake.add(b);
			}

			if (isSnake2Alive) {
				b2 = new SnakeBody(x2, y2, 12);
				snake2.add(b2);
			}

			if (snake.size() > size) {
				snake.remove(0);
			}
			if (snake2.size() > size2) {
				snake2.remove(0);
			}
		}
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g, Color.GREEN);
		}
		for (int j = 0; j < snake2.size(); j++) {
			snake2.get(j).draw(g, Color.RED);
		}
		for (int k = 0; k < apples.size(); k++) {
			apples.get(k).draw(g);
		}
	}

	public void start() {
		running = true;
		thread = new Thread(this, "Game Loop");
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		while (running) {
			tick();
			repaint();
		}
	}

	private class Key implements KeyListener {

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_RIGHT && !left) {
				right = true;
				up = false;
				down = false;
			}
			if (key == KeyEvent.VK_LEFT && !right) {
				left = true;
				up = false;
				down = false;
			}
			if (key == KeyEvent.VK_UP && !down) {
				right = false;
				left = false;
				up = true;
			}
			if (key == KeyEvent.VK_DOWN && !up) {
				right = false;
				left = false;
				down = true;
			}
			
			if (key == KeyEvent.VK_D && !left2) {
				right2 = true;
				up2 = false;
				down2 = false;
			}
			if (key == KeyEvent.VK_A && !right2) {
				left2 = true;
				up2 = false;
				down2 = false;
			}
			if (key == KeyEvent.VK_W && !down2) {
				right2 = false;
				left2 = false;
				up2 = true;
			}
			if (key == KeyEvent.VK_S && !up2) {
				right2 = false;
				left2 = false;
				down2 = true;
			}

		}

		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}
	}
}
