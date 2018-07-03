import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Basket {

	private int x;
	private int y;
	public static final int WIDTH=70;
	public static final int HEIGHT=30;
	public static final int LINE_WIDTH=5;
	Random generator = new Random();
	
	public Basket(int maxWidth, int maxHeight) {
		int randomValue = generator.nextInt(maxWidth+1);
		this.x=Math.abs(randomValue-75); //75 is the max width. subtracting 75 to prevent it from going outside the screen borders
		this.y=maxHeight-HEIGHT-7; 
	}

	public int getX() {return x;}
	public int getY() {return y;}
	
	public boolean isIn(Ball b) { // checks if the ball is in the basket
		if(b.getX()>x && b.getX()<x+WIDTH-15 && b.getY()>y)
			return true;
		return false;
	}
	
	public void moveHorizontal(int distance){
		x+=distance;
}
	
	public void draw(Graphics g){ //draws the basket 
		g.setColor(Color.BLACK);
		g.fillRect(x, y-HEIGHT, LINE_WIDTH, HEIGHT);  //left rectangle
		g.fillRect(x+WIDTH, y-HEIGHT, LINE_WIDTH, HEIGHT); // middle rectangle
		g.fillRect(x, y-LINE_WIDTH, WIDTH, LINE_WIDTH); //right rectangle
	}
	
}
