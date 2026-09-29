import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CoordinatorLogin extends JFrame implements ActionListener {

    JTextField coordIdField;
    JComboBox<String> clubBox;
    JPasswordField passwordField;
    JCheckBox showPasswordCheck;
    JButton loginButton;
    JButton backButton;

    CoordinatorLogin() {
        JLabel titleLabel = new JLabel("Coordinator Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(135, 25, 250, 30);

        JLabel idLabel = new JLabel("Coordinator ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        idLabel.setBounds(50, 85, 120, 25);

        coordIdField = new JTextField();
        coordIdField.setBounds(175, 85, 220, 28);

        JLabel clubLabel = new JLabel("Select Club:");
        clubLabel.setFont(new Font("Arial", Font.BOLD, 14));
        clubLabel.setBounds(50, 135, 120, 25);

        String[] clubs = {
            "Tech Club", 
            "IEEE Student Branch", 
            "Cultural Club", 
            "Sports Club", 
            "Literary Society", 
            "NSS Unit"
        };
        clubBox = new JComboBox<>(clubs);
        clubBox.setBounds(175, 135, 220, 28);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(50, 185, 120, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(175, 185, 220, 28);

        showPasswordCheck = new JCheckBox("Show Password");
        showPasswordCheck.setBounds(171, 220, 150, 25);
        showPasswordCheck.setFocusable(false);
        showPasswordCheck.addActionListener(this);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 270, 120, 35);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(245, 270, 120, 35);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        this.setTitle("ClubConnect - Coordinator Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(460, 370);
        this.setLayout(null);
        this.setResizable(false);

        this.add(titleLabel);
        this.add(idLabel);
        this.add(coordIdField);
        this.add(clubLabel);
        this.add(clubBox);
        this.add(passLabel);
        this.add(passwordField);
        this.add(showPasswordCheck);
        this.add(loginButton);
        this.add(backButton);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showPasswordCheck) {
            if (showPasswordCheck.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }

        if (e.getSource() == loginButton) {
            String id = coordIdField.getText();
            String pass = new String(passwordField.getPassword());
            String club = (String) clubBox.getSelectedItem();

            if (id.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Coordinator ID and Password.");
            } else {
                JOptionPane.showMessageDialog(this, "Welcome Coordinator (" + club + ")!");
            }
        }

        if (e.getSource() == backButton) {
            new login();
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new CoordinatorLogin();
    }
}