import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

	GamePanel panel;
	
	GameFrame(){
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		ImageIcon imageIcon = new ImageIcon("1.png");
        // Image image = imageIcon.getImage().getScaledInstance(800, 300, Image.SCALE_SMOOTH);
		// this.back;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		// this.pack();
		// this.setVisible(true);
		// this.setLocationRelativeTo(null);


		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
			}
		});
	}

	

}
