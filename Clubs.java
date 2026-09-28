import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Clubs {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Club Details Manager");
        frame.setSize(420, 450);
        frame.setLayout(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("College Club Information");
        titleLabel.setBounds(20, 20, 200, 25);
        frame.add(titleLabel);

        JLabel nameLabel = new JLabel("Search Club:");
        nameLabel.setBounds(20, 60, 100, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField("IEEE Student Branch");
        nameField.setBounds(120, 60, 160, 25);
        frame.add(nameField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(290, 60, 80, 25);
        frame.add(searchButton);

        JLabel coordLabel = new JLabel("Coordinator:");
        coordLabel.setBounds(20, 105, 100, 25);
        frame.add(coordLabel);

        JTextField coordField = new JTextField("Arun (ECE)");
        coordField.setBounds(120, 105, 250, 25);
        frame.add(coordField);

        JLabel descLabel = new JLabel("Club Details:");
        descLabel.setBounds(20, 145, 100, 25);
        frame.add(descLabel);

        JTextArea descArea = new JTextArea(
            "Club Name: IEEE Student Branch\n" +
            "Category: Professional / Technical\n" +
            "Members: 120\n\n" +
            "IEEE focuses on advancing technology,\n" +
            "technical paper presentations, and hardware projects.\n\n" +
            "Upcoming: Tech Symposium 2026"
        );
        descArea.setBounds(20, 175, 350, 150);
        frame.add(descArea);
        
        JButton joinButton = new JButton("Register / Join");
        joinButton.setBounds(130, 345, 150, 30);
        frame.add(joinButton);

        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}testing
