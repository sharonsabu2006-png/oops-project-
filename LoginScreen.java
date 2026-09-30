import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginScreen {

    public LoginScreen() {
        JFrame window = new JFrame("College Club Activity Manager");
        window.setSize(680, 430);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(16, 42, 86));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(22, 30, 22, 30));

        JLabel titleLabel = new JLabel("College Club Activity Manager");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel subTitleLabel = new JLabel("B.Tech OOP Project | Campus Club & Event Management Portal");
        subTitleLabel.setForeground(new Color(190, 210, 240));
        subTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subTitleLabel);

        window.add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 15));
        centerPanel.setBorder(new EmptyBorder(40, 150, 40, 150));
        centerPanel.setBackground(new Color(244, 247, 251));

        JButton memberButton = new JButton("Open Member Portal");
        memberButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        memberButton.setBackground(new Color(52, 152, 219));
        memberButton.setForeground(Color.WHITE);
        memberButton.setFocusPainted(false);

        JButton coordButton = new JButton("Coordinator Login");
        coordButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        coordButton.setBackground(new Color(16, 42, 86));
        coordButton.setForeground(Color.WHITE);
        coordButton.setFocusPainted(false);

        centerPanel.add(memberButton);
        centerPanel.add(coordButton);
        
        window.add(centerPanel, BorderLayout.CENTER);

        memberButton.addActionListener(e -> {
            new MemberLogin(); 
            window.dispose();  
        });

        coordButton.addActionListener(e -> {
            new CoordinatorLogin(); 
            window.dispose();
        });

        window.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginScreen(); 
    }
}