package teamWorkAsignment;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeBody {
	private int x, y, width, height;
	public SnakeBody(int x, int y, int tileSize){
		this.x = x;
		this.y = y;
		width = tileSize;
		height = tileSize;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void tick(){
		
	} 
	public void draw(Graphics g, Color c){
		g.setColor(c);
		g.fillRect(x * width, y * height, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x * width, y * height, width, height);
	}
}
