import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class login extends JFrame implements ActionListener {

    JButton memberButton;
    JButton coordinatorButton;
    JButton exitButton;

    login() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(20, 36, 60));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel titleLabel = new JLabel("College Club Activity Manager");
        titleLabel.setForeground(new Color(0, 180, 216));
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel subTitleLabel = new JLabel("B.Tech OOP Project | Campus Activity Portal");
        subTitleLabel.setForeground(Color.LIGHT_GRAY);
        subTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subTitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        headerPanel.add(subTitleLabel);

        JLabel promptLabel = new JLabel("Select your role to continue:");
        promptLabel.setForeground(Color.WHITE);
        promptLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        Border buttonBorder = BorderFactory.createLineBorder(new Color(0, 180, 216), 2);

        memberButton = new JButton("Member Portal (View & Join Clubs)");
        memberButton.setPreferredSize(new Dimension(320, 45));
        memberButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        memberButton.setBackground(new Color(0, 150, 199));
        memberButton.setForeground(Color.WHITE);
        memberButton.setBorder(buttonBorder);
        memberButton.setFocusable(false);
        memberButton.addActionListener(this);

        coordinatorButton = new JButton("Coordinator Login (Manage Events)");
        coordinatorButton.setPreferredSize(new Dimension(320, 45));
        coordinatorButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        coordinatorButton.setBackground(new Color(20, 36, 60));
        coordinatorButton.setForeground(new Color(0, 180, 216));
        coordinatorButton.setBorder(buttonBorder);
        coordinatorButton.setFocusable(false);
        coordinatorButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(130, 35));
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        exitButton.setBackground(new Color(180, 40, 40));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        this.setTitle("College Club Activity Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(600, 450);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(10, 18, 32));

        this.add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 15, 0);
        centerPanel.add(promptLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 12, 0);
        centerPanel.add(memberButton, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        centerPanel.add(coordinatorButton, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        centerPanel.add(exitButton, gbc);

        this.add(centerPanel, BorderLayout.CENTER);

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