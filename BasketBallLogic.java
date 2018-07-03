import java.util.Random;
import java.awt.Graphics;
public class BasketBallLogic {

	private Ball fallingBall;
	private Basket movingBasket;
	private int pointCounter;
	private int width;
	private int height;
	Random generator = new Random();

	public static final int HORIZONTAL=5;
	public static final int VERTICAL=5;
	public static final int WIN=1;  //ball in the basket
	public static final int LOOSE=2; // ball exception
	public static final int GO_ON=3; // ball needs to go further down

	public BasketBallLogic(int width, int height) {
		fallingBall=new Ball(width);
		movingBasket=new Basket(width, height);
		this.width=width;
		this.height=height;
		pointCounter=0;
	}

	public int step() {
		fallingBall.moveVertical(VERTICAL);
		if(movingBasket.isIn(fallingBall) == true){
			pointCounter++;
			fallingBall=new Ball(width);
			return WIN;
		}
		else if(fallingBall.getY() > height-35){
			System.out.println(height-35);
			return LOOSE;}
		else{
			int randomValue = generator.nextInt(10); 
			//randomLocation- generates positive and negative locations for the basket
			int randomLocation = (generator.nextInt(2*width))-width; 
			// a condition that prevents the basket from going outside the screen borders 
			if(movingBasket.getX()+randomLocation < width-75 && movingBasket.getX()+randomLocation> 0){ 
				if (randomValue < 3 )
					movingBasket.moveHorizontal(randomLocation);
			}
			return GO_ON;
		}
	}
	public int getPoints() {
		return pointCounter;
	}
	public void moveLeft(){ //moves the ball left
		if(fallingBall.getX()-HORIZONTAL < 0)
			return;
		fallingBall.moveHorizontal(-1*HORIZONTAL);
	}
	public void moveRight(){ // moves the ball right
		if(fallingBall.getX()+HORIZONTAL <= width)
			fallingBall.moveHorizontal(HORIZONTAL);
	}
	public void draw(Graphics g) {
		fallingBall.draw(g);
		movingBasket.draw(g);
	}
}
