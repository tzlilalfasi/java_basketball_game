import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;

public class Ball {

	private int x;
	private int y;
	private Color bColor;
	Random generator = new Random();
	public static final int SIZE=25;
	
	public Ball(int width) {
		this.y=0;
		int randomValue = generator.nextInt(width+1);
		int r = generator.nextInt(255);
		int g = generator.nextInt(255);
		int b = generator.nextInt(255);
		this.x=Math.abs(randomValue-75);
		bColor=new Color(r, g, b);
		
	}
	public int getX() {return x;}
	public int getY() {return y;}
	public Color getbColor() {return bColor;}
	
	public void moveHorizontal(int distance){
			x+=distance;
	}
	public void moveVertical(int distance) {
			y+=distance;
	}
	public void draw(Graphics g) {
		g.setColor(bColor);
		g.fillOval(x, y, SIZE, SIZE);
	}
}
