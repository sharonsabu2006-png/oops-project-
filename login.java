import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class login extends JFrame implements ActionListener {

    JButton memberButton;
    JButton coordinatorButton;
    JButton exitButton;

    login() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(20, 36, 60));
        headerPanel.setBounds(0, 0, 600, 110);
        headerPanel.setLayout(null);

        JLabel titleLabel = new JLabel("College Club Activity Manager");
        titleLabel.setForeground(new Color(0, 180, 216));
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBounds(115, 20, 400, 35);

        JLabel subTitleLabel = new JLabel("B.Tech OOP Project | Campus Activity Portal");
        subTitleLabel.setForeground(Color.LIGHT_GRAY);
        subTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subTitleLabel.setBounds(155, 60, 320, 25);

        headerPanel.add(titleLabel);
        headerPanel.add(subTitleLabel);

        JLabel promptLabel = new JLabel("Select your role to continue:");
        promptLabel.setForeground(Color.WHITE);
        promptLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        promptLabel.setBounds(190, 135, 250, 30);

        Border buttonBorder = BorderFactory.createLineBorder(new Color(0, 180, 216), 2);

        memberButton = new JButton("Member Portal (View & Join Clubs)");
        memberButton.setBounds(150, 185, 300, 45);
        memberButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        memberButton.setBackground(new Color(0, 150, 199));
        memberButton.setForeground(Color.WHITE);
        memberButton.setBorder(buttonBorder);
        memberButton.setFocusable(false);
        memberButton.addActionListener(this);

        coordinatorButton = new JButton("Coordinator Login (Manage Events)");
        coordinatorButton.setBounds(150, 250, 300, 45);
        coordinatorButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        coordinatorButton.setBackground(new Color(20, 36, 60));
        coordinatorButton.setForeground(new Color(0, 180, 216));
        coordinatorButton.setBorder(buttonBorder);
        coordinatorButton.setFocusable(false);
        coordinatorButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setBounds(240, 320, 120, 35);
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        exitButton.setBackground(new Color(180, 40, 40));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        this.setTitle("College Club Activity Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 420);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(10, 18, 32));

        this.add(headerPanel);
        this.add(promptLabel);
        this.add(memberButton);
        this.add(coordinatorButton);
        this.add(exitButton);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == memberButton) {
            System.out.println("Opening Member Portal...");
        } 
        else if (e.getSource() == coordinatorButton) {
            System.out.println("Opening Coordinator Portal...");
        } 
        else if (e.getSource() == exitButton) {
            System.out.println("Closing application...");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new login();
    }
}