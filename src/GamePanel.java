
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 50;
	static final int PADDLE_WIDTH = 15;
	static final int PADDLE_HEIGHT = 150;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Balok balok1;
	Balok balok;
	Bola bola;
	Score score;

	GamePanel() {
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);

		gameThread = new Thread(this);
		gameThread.start();

		checkGameOver();
	}

	public void newBall() {
		random = new Random();
		bola = new Bola((GAME_WIDTH / 3) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER),
				BALL_DIAMETER, BALL_DIAMETER);
	}

	public void newPaddles() {
		balok1 = new Balok(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		balok = new Balok(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void draw(Graphics g) {
		balok1.draw(g);
		balok.draw(g);
		bola.draw(g);
		score.draw(g);
		Toolkit.getDefaultToolkit().sync();

	}

	public void move() {
		balok1.move();
		balok.move();
		bola.move();
	}

	public void checkCollision() {

		if (bola.y <= 0) {
			bola.setYDirection(-bola.KecepatanY);
		}
		if (bola.y >= GAME_HEIGHT - BALL_DIAMETER) {
			bola.setYDirection(-bola.KecepatanY);
		}

		if (bola.intersects(balok1)) {
			bola.KecepatanX = Math.abs(bola.KecepatanX);
			bola.KecepatanX++;
			if (bola.KecepatanY > 0)
				bola.KecepatanY++;
			else
				bola.KecepatanY--;
			bola.setXDirection(bola.KecepatanX);
			bola.setYDirection(bola.KecepatanY);
		}
		if (bola.intersects(balok)) {
			bola.KecepatanX = Math.abs(bola.KecepatanX);
			bola.KecepatanX += 0.5;
			if (bola.KecepatanY > 0)
				bola.KecepatanY += 0.5;
			else
				bola.KecepatanY--;
			bola.setXDirection(-bola.KecepatanX);
			bola.setYDirection(bola.KecepatanY);
		}

		if (balok1.y <= 0)
			balok1.y = 0;
		if (balok1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			balok1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		if (balok.y <= 0)
			balok.y = 0;
		if (balok.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			balok.y = GAME_HEIGHT - PADDLE_HEIGHT;

		if (bola.x <= 0) {
			score.player2 += 10;
			newPaddles();
			newBall();
		}
		if (bola.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1 += 10;
			newPaddles();
			newBall();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollision();
				repaint();
				checkGameOver();
				delta--;
			}
		}
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			balok1.keyPressed(e);
			balok.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			balok1.keyReleased(e);
			balok.keyReleased(e);
		}
	}

	public void checkGameOver() {
		gameThread.interrupt(); // menghentikan permainan
		if (score.player1 >= 10) {
			JOptionPane.showMessageDialog(this, "Player 1 wins!", "Game Over",
					JOptionPane.INFORMATION_MESSAGE);
					
			int result = JOptionPane.showConfirmDialog(this,
					"Game over! Do you want to restart the game?", "Game Over",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) { // jika memilih "Yes", restart permainan
				score.player1 = 0;
				score.player2 = 0;
				newPaddles();
				newBall();
				gameThread = new Thread(this);
				gameThread.start();
			} else { // jika memilih "No", keluar dari permainan
				System.exit(0);
			}

		} else if (score.player2 >= 10) {
			JOptionPane.showMessageDialog(this, "Player 2 wins!", "Game Over",
					JOptionPane.INFORMATION_MESSAGE);

			int result = JOptionPane.showConfirmDialog(this,
					"Game over! Do you want to restart the game?", "Game Over",
					JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.YES_OPTION) { // jika memilih "Yes", restart permainan

				score.player1 = 0;
				score.player2 = 0;
				newPaddles();
				newBall();
				gameThread = new Thread(this);
				gameThread.start();
			} else { // jika memilih "No", keluar dari permainan
				System.exit(0);
			}
		}
		// if (score.player1 == 10 || score.player2 == 10) {

		// }
	}

	// public void checkGameOver() {
	// if (score.player1 == 10 || score.player2 == 10) {
	// int option = JOptionPane.showOptionDialog(
	// null,
	// "Game over! Do you want to restart the game or exit?",
	// "Game Over",
	// JOptionPane.YES_NO_OPTION,
	// JOptionPane.INFORMATION_MESSAGE,
	// null,
	// new String[] { "Restart", "Exit" },
	// null);
	// if (option == JOptionPane.YES_OPTION) {
	// score.player1 = 0;
	// score.player2 = 0;
	// newPaddles();
	// newBall();
	// } else {
	// System.exit(0);
	// }
	// }
	// }

}
