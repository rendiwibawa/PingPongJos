
import java.awt.*;
import java.util.*;

public class Bola extends Rectangle{

	Random random;
	int KecepatanX;
	int KecepatanY;
	int initialSpeed = 2;
	
	Bola(int x, int y, int width, int height){
		super(x,y,width,height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection*initialSpeed);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection*initialSpeed);
		
	}
	
	public void setXDirection(int randomXDirection) {
		KecepatanX = randomXDirection;
	}
	public void setYDirection(int randomYDirection) {
		KecepatanY = randomYDirection;
	}
	public void move() {
		x += KecepatanX;
		y += KecepatanY;
	}
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(x, y, height, width);
	}
}
