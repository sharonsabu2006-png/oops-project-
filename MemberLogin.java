import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MemberLogin {

    public MemberLogin() {
        JFrame window = new JFrame("Club Activity Manager - Member Login");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(480, 410);
        window.setLayout(null);
        window.setResizable(false);

        JLabel titleLabel = new JLabel("Member Login & Sign Up");
        titleLabel.setBounds(110, 25, 300, 30);

        JLabel idLabel = new JLabel("Member ID:");
        idLabel.setBounds(60, 85, 100, 25);
        JTextField idField = new JTextField();
        idField.setBounds(170, 85, 220, 28);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(60, 130, 100, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(170, 130, 220, 28);

        JLabel semLabel = new JLabel("Semester:");
        semLabel.setBounds(60, 175, 100, 25);
        String[] semesters = {"S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8"};
        JComboBox<String> semesterBox = new JComboBox<>(semesters);
        semesterBox.setBounds(170, 175, 220, 28);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(60, 220, 100, 25);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(170, 220, 220, 28);

        JCheckBox showPasswordCheck = new JCheckBox("Show Password");
        showPasswordCheck.setBounds(166, 255, 150, 25);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(60, 305, 110, 35);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(185, 305, 110, 35);

        JButton backButton = new JButton("Back");
        backButton.setBounds(310, 305, 90, 35);

        showPasswordCheck.addActionListener(e -> {
            if (showPasswordCheck.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        loginButton.addActionListener(e -> {
            String id = idField.getText();
            String pass = new String(passwordField.getPassword());

            if (id.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please enter Member ID and Password.");
            } else {
                JOptionPane.showMessageDialog(window, "Login Successful!");
            }
        });

        signupButton.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String sem = (String) semesterBox.getSelectedItem();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please fill all fields to Sign Up.");
            } else {
                JOptionPane.showMessageDialog(window, "Member Registered: " + name + " (" + sem + ")");
            }
        });

        backButton.addActionListener(e -> {
            new LoginScreen();
            window.dispose();
        });

        window.add(titleLabel);
        window.add(idLabel);
        window.add(idField);
        window.add(nameLabel);
        window.add(nameField);
        window.add(semLabel);
        window.add(semesterBox);
        window.add(passLabel);
        window.add(passwordField);
        window.add(showPasswordCheck);
        window.add(loginButton);
        window.add(signupButton);
        window.add(backButton);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        new MemberLogin();
    }
}