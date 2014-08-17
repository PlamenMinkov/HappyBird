import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Bird extends StartGame{
	private StartGame sGame = new StartGame();
	
	private BufferedImage bird;
	private double angle= 0.0 ;
	
	//initial X,Y for the bird
	private int initX = 30;
	private int initY = 150;

	//set size of the bird
	private int birdWidth;
	private int birdHeight;
	
	//current status of the bird
	private boolean isFlying = false;
	private boolean isMovingFront = false;
	
	//movement steps
	private int stepUp = 8;
	private int stepFront = 3;
	private int fallDown = 2;
	private int returnBack = 1;
	
	//is the bird is dead
	private boolean isDead = false;
	
	public Bird() {
		try {
			bird = ImageIO.read(new File("bird.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		birdWidth =  bird.getWidth(this)-35;
		birdHeight = bird.getHeight(this)-10;
	}
	
	///////////////////////
	// GETTERS & SETTERS //
	///////////////////////
	public int getInitX() {
		return initX;
	}
	public void setInitX(int initX) {
		this.initX = initX;
	}
	public int getInitY() {
		return initY;
	}
	public void setInitY(int initY) {
		this.initY = initY;
	}	
	public void setFlying(boolean isFlying) {
		this.isFlying = isFlying;
	}
	public void setMovingFront(boolean isMovingFront) {
		this.isMovingFront = isMovingFront;
	}
	public int getBirdWidth() {
		return birdWidth;
	}
	public int getBirdHeight() {
		return birdHeight;
	}

	public void update() {	
		//If the space is pressed
		if (isFlying) {
			initY -= stepUp;
			angle=-30.0;
		} else {
			initY += fallDown;
			angle=5.0;
		}
		
		//if the right key is pressed
		if (isMovingFront) {
			initX += stepFront;
		} else {
			initX -= returnBack;
		}
		
		//restricts the flying area of the bird
		if (initY <= 1) {
			initY = 2;
		} else if (initY >= sGame.getHeight()-birdHeight) {
			angle=-200.0;
			initY = sGame.getHeight()-birdHeight;
		}		
		if (initX >= sGame.getWidth()/2) {
			initX = sGame.getWidth()/2;
		} else if (initX <= 20) {
			initX = 20;
		}	
	}
	
	public void paint(Graphics g) {		
		//paint bird
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform turn = new AffineTransform();
		turn.translate(initX, initY);
		turn.rotate(Math.toRadians(angle), (birdWidth/2), (birdHeight/2) );
		g2.drawImage(bird, turn, this);
	}
}
