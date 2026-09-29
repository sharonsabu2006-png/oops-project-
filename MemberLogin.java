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

public class MemberLogin extends JFrame implements ActionListener {

    JTextField idField;
    JTextField nameField;
    JComboBox<String> semesterBox;
    JPasswordField passwordField;
    JCheckBox showPasswordCheck;
    JButton loginButton;
    JButton signupButton;
    JButton backButton;

    MemberLogin() {
        JLabel titleLabel = new JLabel("Member Login & Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(110, 25, 300, 30);

        JLabel idLabel = new JLabel("Member ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        idLabel.setBounds(60, 85, 100, 25);

        idField = new JTextField();
        idField.setBounds(170, 85, 220, 28);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setBounds(60, 130, 100, 25);

        nameField = new JTextField();
        nameField.setBounds(170, 130, 220, 28);

        JLabel semLabel = new JLabel("Semester:");
        semLabel.setFont(new Font("Arial", Font.BOLD, 14));
        semLabel.setBounds(60, 175, 100, 25);

        String[] semesters = {"S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8"};
        semesterBox = new JComboBox<>(semesters);
        semesterBox.setBounds(170, 175, 220, 28);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(60, 220, 100, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 220, 220, 28);

        showPasswordCheck = new JCheckBox("Show Password");
        showPasswordCheck.setBounds(166, 255, 150, 25);
        showPasswordCheck.setFocusable(false);
        showPasswordCheck.addActionListener(this);

        loginButton = new JButton("Login");
        loginButton.setBounds(60, 305, 110, 35);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(185, 305, 110, 35);
        signupButton.setFocusable(false);
        signupButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(310, 305, 90, 35);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        this.setTitle("Club Activity Manager - Member Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(480, 410);
        this.setLayout(null);
        this.setResizable(false);

        this.add(titleLabel);
        this.add(idLabel);
        this.add(idField);
        this.add(nameLabel);
        this.add(nameField);
        this.add(semLabel);
        this.add(semesterBox);
        this.add(passLabel);
        this.add(passwordField);
        this.add(showPasswordCheck);
        this.add(loginButton);
        this.add(signupButton);
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
            String id = idField.getText();
            String pass = new String(passwordField.getPassword());

            if (id.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Member ID and Password.");
            } else {
                JOptionPane.showMessageDialog(this, "Login Successful!");
            }
        }

        if (e.getSource() == signupButton) {
            String id = idField.getText();
            String name = nameField.getText();
            String sem = (String) semesterBox.getSelectedItem();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields to Sign Up.");
            } else {
                JOptionPane.showMessageDialog(this, "Member Registered: " + name + " (" + sem + ")");
            }
        }

        if (e.getSource() == backButton) {
            new login();
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new MemberLogin();
    }
}