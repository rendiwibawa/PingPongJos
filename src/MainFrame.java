
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Main frame ini menu utama

public class MainFrame extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new MainFrame();
    }

    private JButton playButton, tutorialButton, exitButton;
    private JPanel buttonPanel, imagePanel;

    public MainFrame() {
        super("Menu Utama");

        setSize(800, 500);

        ImageIcon icon = new ImageIcon("3.png");
        setIconImage(icon.getImage());

        setLayout(new BorderLayout());

        imagePanel = new JPanel(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("3.png");
        Image image = imageIcon.getImage().getScaledInstance(800, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel);


        buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        playButton = new JButton("Play");
        tutorialButton = new JButton("Tutorial");
        exitButton = new JButton("Exit");
        playButton.setPreferredSize(new Dimension(200, 50));
        tutorialButton.setPreferredSize(new Dimension(200, 50));
        exitButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(playButton);
        buttonPanel.add(tutorialButton);
        buttonPanel.add(exitButton);


        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


        playButton.addActionListener(this);
        tutorialButton.addActionListener(this);
        exitButton.addActionListener(this);


        setLocationRelativeTo(null);

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        setVisible(true);

        
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            GameFrame game = new GameFrame();
             this.setVisible(false);
        } else if (e.getSource() == tutorialButton) {
            Tutorial game = new Tutorial();
            // this.setVisible(false);

        } else if (e.getSource() == exitButton) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public class Tutorial extends JFrame implements ActionListener {
        private JPanel mainPanel;
        private JButton backButton;
    
        public Tutorial() {
            super("Tutorial");
    
            // Set window properties
            setSize(800, 500);
            setLocationRelativeTo(null);
            // setSize(800, 500);
            // setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            // Create main panel
            mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
            // Add image to label
            ImageIcon icon = new ImageIcon("2.png");
            JLabel imageLabel = new JLabel(icon);
            mainPanel.add(imageLabel, BorderLayout.CENTER);
    
            // Add back button
            JPanel bottomPanel = new JPanel();
            backButton = new JButton("Back");
            bottomPanel.add(backButton);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    
            // Add action listener for back button
            backButton.addActionListener(this);
    
            // Set window icon
            Image iconImage = Toolkit.getDefaultToolkit().getImage("icon.png");
            setIconImage(iconImage);
    
            // Set content pane
            setContentPane(mainPanel);
            setVisible(true);
        }
    
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                dispose();
                
            }
        }
    }

    
}