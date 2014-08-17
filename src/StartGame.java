import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;


public class StartGame extends Applet implements Runnable, KeyListener{
	static int WIDTH = 800;
	static int HEIGHT = 600;
	
	public int width = 800, height = 600,
			   x=50 , y=50, dx, dy ;
	public double angle= 0.0 ;
	private int score;
	String finalScore=Integer.toString(score);
	///
	private boolean gameOver = false;
	private Image offscreen;
	private Graphics d;		
	private Wall simpleWall;
	//private Bird simpleBirt;
	private BufferedImage background;
	private Bird bird;
	private Wall wall;
	//NEW VARS
	public int scrollX = 0;
	public int Wallspeed = -2;
	
	
///////////////////////
// GETTERS & SETTERS //
///////////////////////
public int getWidth() {
return width;
}
public int getHeight() {
return height;
}
public void setGameOver(boolean gameOver) {
this.gameOver = gameOver;
}
public boolean isGameOver() {
return gameOver;
}
	
	//initialize the Game
	public void init() {
		setFocusable(true);
		setSize(WIDTH, HEIGHT);						
		try {
			background = ImageIO.read(new File("bgSky.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addKeyListener(this);
	}
	
	//start Game
	public void start() {
		simpleWall = new Wall();
		bird = new Bird();
		wall = new Wall();
		Thread mainThread = new Thread(this); 
		mainThread.start();
	}
	
	//run Game
	public void run() {
		while (!gameOver) {
			checkPossition();
			//if GAME OVER is FALSE			
			//score++;
			if (wall.getStartX() < 0-wall.getWallWidth()) {
				wall = null;
				wall = new Wall();
				score++;
			}
			bird.update();
			wall.update();
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	//check bird position if it touch the wall or the ground
		private void checkPossition() {
			//if the bird touch the ground
			if (bird.getInitY() >= height-bird.getBirdHeight()) {
				gameOver = true;
			}
			
			
			if ((bird.getInitX()+bird.getBirdWidth() >=wall.getStartX())&&(bird.getInitX()<=wall.getStartX()+wall.getWallWidth())) {
				//if bird cross axis Y of the upper wall
				if (bird.getInitY()+20<=wall.getUpWallHeight()) {
					gameOver = true;
				}
				//if bird cross axis Y of the down wall
				if (bird.getInitY()+bird.getBirdHeight()>=wall.getDownWallY()) {
					gameOver = true;
					
				}
			}
			
		}
	
	public void stop() {
		
	}

	public void destroy() {
		
	}
	
	//set double buffering
	public void update(Graphics g) {
		if (offscreen==null) {
			offscreen = createImage(this.getSize().width, this.getSize().height);
			d = offscreen.getGraphics();
		}
		d.setColor(getBackground());
		d.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		d.setColor(getForeground());
		paint(d);
		g.drawImage(offscreen, 0, 0, this);
	}
	
	//draw graphics
	public void paint(Graphics g) {
		g.drawImage(background, scrollX, 0,this);
		g.drawImage(background, scrollX + 800, 0, null);
		simpleWall.paint(g);
		bird.paint(g);
		wall.paint(g);
		g.drawString(Integer.toString(score), this.getSize().width-30,10);
		if (gameOver) {
			Font font = new Font("Serif", Font.BOLD,32);
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 300, 300);
			g.drawString("Wall passed: "+Integer.toString(score), this.getSize().width/2-100,this.getSize().height/2+30);

		}
	}
	
	//////////////
	//KEY events
	//////////////
	public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		bird.setFlying(true);
	}		
	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		bird.setMovingFront(true);
	}
	}
	
	public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		bird.setFlying(false);
	}
	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		bird.setMovingFront(false);
	}		
	}
	
	public void keyTyped(KeyEvent e) {
	
	}

}