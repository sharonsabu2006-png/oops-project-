import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        // Toggle Password Visibility
        showPasswordCheck.addActionListener(e -> {
            if (showPasswordCheck.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        // ------------------ LOGIN BUTTON (DATABASE READ) ------------------
        loginButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String pass = new String(passwordField.getPassword());

            if (id.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please enter Member ID and Password.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // SQL Query to verify matching credentials
            String sql = "SELECT * FROM members WHERE member_id = ? AND password = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                // Bind parameters to ?
                stmt.setString(1, id);
                stmt.setString(2, pass);

                // Run query and evaluate result
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String loggedInName = rs.getString("full_name");
                    JOptionPane.showMessageDialog(window, "Welcome, " + loggedInName + "!");
                    
                    // Passing memberId and full_name to the Dashboard
                    new DashboardFrame(id, loggedInName);
                    window.dispose();
                } else {
                    JOptionPane.showMessageDialog(window, "Invalid Member ID or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(window, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // ------------------ SIGN UP BUTTON (DATABASE INSERT) ------------------
        signupButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String sem = (String) semesterBox.getSelectedItem();
            String pass = new String(passwordField.getPassword());

            if (id.isEmpty() || name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please fill Member ID, Full Name, and Password to Sign Up.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // SQL Query to insert new member record
            String sql = "INSERT INTO members (member_id, full_name, semester, password) VALUES (?, ?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                // Bind parameters to ?
                stmt.setString(1, id);
                stmt.setString(2, name);
                stmt.setString(3, sem);
                stmt.setString(4, pass);

                // Execute the insert
                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(window, "Sign Up Successful! Redirecting to Dashboard...");
                    
                    // Passing newly created memberId and name to Dashboard
                    new DashboardFrame(id, name);
                    window.dispose();
                }

            } catch (SQLException ex) {
                // Duplicate entry handling (primary key conflict)
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(window, "Member ID '" + id + "' already exists! Try logging in.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(window, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                ex.printStackTrace();
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