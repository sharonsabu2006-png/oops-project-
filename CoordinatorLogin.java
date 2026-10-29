import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CoordinatorLogin {

    public CoordinatorLogin() {
        JFrame window = new JFrame("Club Activity Manager - Coordinator Login");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 280);
        window.setLayout(null);
        window.setResizable(false);

        JLabel titleLabel = new JLabel("Coordinator Access");
        titleLabel.setBounds(130, 20, 150, 25);

        JLabel idLabel = new JLabel("Faculty/Coord ID:");
        idLabel.setBounds(40, 70, 120, 25);
        JTextField idField = new JTextField();
        idField.setBounds(160, 70, 180, 25);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(40, 115, 120, 25);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(160, 115, 180, 25);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(80, 170, 100, 30);

        JButton backButton = new JButton("Back");
        backButton.setBounds(210, 170, 100, 30);

        // ------------------ LOGIN ACTION LISTENER ------------------
        loginButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String pass = new String(passField.getPassword());

            if (id.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please enter ID and Password.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "SELECT coordinator_name, club_name FROM coordinators WHERE coordinator_id = ? AND password = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, id);
                stmt.setString(2, pass);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String coordName = rs.getString("coordinator_name");
                    String clubName = rs.getString("club_name");

                    JOptionPane.showMessageDialog(window, 
                        "Welcome, " + coordName + "!\nClub: " + clubName, 
                        "Login Successful", 
                        JOptionPane.INFORMATION_MESSAGE);

                    CoordinatorDashboard.main(null);
                    window.dispose();
                } else {
                    JOptionPane.showMessageDialog(window, 
                        "Invalid Coordinator ID or Password.", 
                        "Access Denied", 
                        JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(window, 
                    "Database Error: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
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
        window.add(passLabel);
        window.add(passField);
        window.add(loginButton);
        window.add(backButton);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        new CoordinatorLogin();
    }
}