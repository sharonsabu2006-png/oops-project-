import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CoordinatorLogin {

    public CoordinatorLogin() {
        // 1. Create Window
        JFrame window = new JFrame("Club Activity Manager - Coordinator Login");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 280);
        window.setLayout(null); // Absolute positioning: we manually set x, y coordinates
        window.setResizable(false);

        // 2. Create Components
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

        // 3. Functional Logic (Lambdas)
        loginButton.addActionListener(e -> {
            String id = idField.getText();
            String pass = new String(passField.getPassword());

            if (id.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please enter ID and Password.");
            } else {
                JOptionPane.showMessageDialog(window, "Coordinator Authenticated!");
                // new CoordinatorDashboard(); // Uncomment when linking
                // window.dispose();
            }
        });

        backButton.addActionListener(e -> {
            new LoginScreen(); 
            window.dispose();
        });

        // 4. Add to Window and Display
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