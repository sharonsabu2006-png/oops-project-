import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class login extends JFrame implements ActionListener {

    JButton memberButton;
    JButton coordinatorButton;
    JButton exitButton;

    public login() {
        this.setTitle("College Club Activity Manager");
        this.setSize(680, 430);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        Color navy = new Color(16, 42, 86);
        Color blue = new Color(52, 152, 219);
        Color bgLight = new Color(244, 247, 251);
        Color textDark = new Color(23, 32, 51);
        Color textGray = new Color(110, 120, 135);
        Color borderColor = new Color(220, 226, 235);

        this.getContentPane().setBackground(bgLight);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(navy);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(22, 30, 22, 30));

        JLabel titleLabel = new JLabel("College Club Activity Manager");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel subTitleLabel = new JLabel("B.Tech OOP Project  |  Campus Club & Event Management Portal");
        subTitleLabel.setForeground(new Color(190, 210, 240));
        subTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subTitleLabel);

        this.add(headerPanel, BorderLayout.NORTH);

        JPanel centerContainer = new JPanel(new BorderLayout(0, 12));
        centerContainer.setOpaque(false);
        centerContainer.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel promptLabel = new JLabel("Select a portal to continue:");
        promptLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        promptLabel.setForeground(textDark);
        centerContainer.add(promptLabel, BorderLayout.NORTH);

        JPanel cardsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        cardsPanel.setOpaque(false);

        JPanel memberCard = new JPanel(new BorderLayout(0, 12));
        memberCard.setBackground(Color.WHITE);
        memberCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JPanel memberTextPanel = new JPanel();
        memberTextPanel.setOpaque(false);
        memberTextPanel.setLayout(new BoxLayout(memberTextPanel, BoxLayout.Y_AXIS));

        JLabel memberTitle = new JLabel("Student Member Portal");
        memberTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
        memberTitle.setForeground(textDark);

        JLabel memberDesc = new JLabel("<html>Explore active campus clubs, join new communities, and register for upcoming events.</html>");
        memberDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        memberDesc.setForeground(textGray);

        memberTextPanel.add(memberTitle);
        memberTextPanel.add(Box.createVerticalStrut(8));
        memberTextPanel.add(memberDesc);

        memberButton = new JButton("Open Member Portal");
        memberButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        memberButton.setBackground(blue);
        memberButton.setForeground(Color.WHITE);
        memberButton.setOpaque(true);
        memberButton.setBorderPainted(false);
        memberButton.setFocusPainted(false);
        memberButton.setPreferredSize(new Dimension(0, 38));
        memberButton.addActionListener(this);

        memberCard.add(memberTextPanel, BorderLayout.CENTER);
        memberCard.add(memberButton, BorderLayout.SOUTH);

        JPanel coordCard = new JPanel(new BorderLayout(0, 12));
        coordCard.setBackground(Color.WHITE);
        coordCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JPanel coordTextPanel = new JPanel();
        coordTextPanel.setOpaque(false);
        coordTextPanel.setLayout(new BoxLayout(coordTextPanel, BoxLayout.Y_AXIS));

        JLabel coordTitle = new JLabel("Coordinator Portal");
        coordTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
        coordTitle.setForeground(textDark);

        JLabel coordDesc = new JLabel("<html>Manage club memberships, schedule new campus activities, and track event participation.</html>");
        coordDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        coordDesc.setForeground(textGray);

        coordTextPanel.add(coordTitle);
        coordTextPanel.add(Box.createVerticalStrut(8));
        coordTextPanel.add(coordDesc);

        coordinatorButton = new JButton("Coordinator Login");
        coordinatorButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        coordinatorButton.setBackground(navy);
        coordinatorButton.setForeground(Color.WHITE);
        coordinatorButton.setOpaque(true);
        coordinatorButton.setBorderPainted(false);
        coordinatorButton.setFocusPainted(false);
        coordinatorButton.setPreferredSize(new Dimension(0, 38));
        coordinatorButton.addActionListener(this);

        coordCard.add(coordTextPanel, BorderLayout.CENTER);
        coordCard.add(coordinatorButton, BorderLayout.SOUTH);

        cardsPanel.add(memberCard);
        cardsPanel.add(coordCard);

        centerContainer.add(cardsPanel, BorderLayout.CENTER);
        this.add(centerContainer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 10));
        bottomPanel.setBackground(new Color(235, 240, 247));
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, borderColor));

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        exitButton.setPreferredSize(new Dimension(90, 30));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(this);

        bottomPanel.add(exitButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == memberButton) {
            System.out.println("Opening Member Portal...");
        } else if (e.getSource() == coordinatorButton) {
            System.out.println("Opening Coordinator Portal...");
        } else if (e.getSource() == exitButton) {
            System.out.println("Closing application...");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new login();
    }
}