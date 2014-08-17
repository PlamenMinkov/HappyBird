import java.awt.Color;
import java.awt.Graphics;


public class Wall {
	private StartGame sGame = new StartGame();
	private int startX = sGame.getWidth();
	private int moveStep = 3;
	private int wallWidth = 50;
	
	private int minSize = 75;
	private int holeSize = 200;
	private int upWallHeight = minSize + (int)(Math.random() * ((sGame.getHeight() - (2*minSize+holeSize)) + 1));
	private int downWallY = upWallHeight+holeSize;
	private int downWallHeight = sGame.getHeight()-downWallY;
	
	///////////////////////
	// GETTERS & SETTERS //
	///////////////////////
	public int getStartX() {
		return startX;
	}
	public int getWallWidth() {
		return wallWidth;
	}
	public int getUpWallHeight() {
		return upWallHeight;
	}
	public int getDownWallY() {
		return downWallY;
	}
	
	
	public void update() {
		startX -= moveStep;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(startX, 0, wallWidth, upWallHeight);
		g.fillRect(startX, downWallY, wallWidth, downWallHeight);
	}
	
}
