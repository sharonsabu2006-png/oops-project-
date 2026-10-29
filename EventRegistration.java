import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EventRegistration {

    private String targetEventId = "E101";
    private String targetEventName = "Hackathon 2026";

    public EventRegistration() {
        this("E101", "Hackathon 2026");
    }

    public EventRegistration(String eventId, String eventName) {
        if (eventId != null && !eventId.isEmpty()) this.targetEventId = eventId;
        if (eventName != null && !eventName.isEmpty()) this.targetEventName = eventName;

        JFrame window = new JFrame("Event Registration - Student Portal");
        window.setSize(440, 420);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(null);
        window.setResizable(false);

        // Header
        JLabel titleLabel = new JLabel("Campus Event Registration");
        titleLabel.setBounds(130, 15, 220, 30);

        // Event Display Label
        JLabel eventLabel = new JLabel("Event: " + targetEventName + " (" + targetEventId + ")");
        eventLabel.setBounds(40, 50, 340, 25);

        // Student Name
        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(40, 90, 110, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 90, 230, 28);

        // Student ID / Member ID
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(40, 135, 110, 25);
        JTextField idField = new JTextField();
        idField.setBounds(150, 135, 230, 28);

        // Phone Number
        JLabel phoneLabel = new JLabel("Phone No:");
        phoneLabel.setBounds(40, 180, 110, 25);
        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 180, 230, 28);

        // Department Dropdown
        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(40, 225, 110, 25);
        String[] departments = {"CSE", "ECE", "EEE", "ME", "CE", "IT"};
        JComboBox<String> deptBox = new JComboBox<>(departments);
        deptBox.setBounds(150, 225, 230, 28);

        // Buttons
        JButton submitBtn = new JButton("Confirm Registration");
        submitBtn.setBounds(60, 290, 170, 35);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(245, 290, 110, 35);

        // Submit action inserting into registrations
        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String memberId = idField.getText().trim();
            String phone = phoneField.getText().trim();
            String dept = (String) deptBox.getSelectedItem();

            if (name.isEmpty() || memberId.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please fill in all required fields.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String sql = "INSERT INTO registrations (member_id, event_id, student_name, phone, department, status) " +
                         "VALUES (?, ?, ?, ?, ?, 'Confirmed')";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, memberId);
                stmt.setString(2, targetEventId);
                stmt.setString(3, name);
                stmt.setString(4, phone);
                stmt.setString(5, dept);

                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(window, 
                        "Registered successfully for " + targetEventName + "!", 
                        "Registration Confirmed", 
                        JOptionPane.INFORMATION_MESSAGE);

                    // Redirect to RegistrationFrame with this member's ID
                    RegistrationFrame.main(new String[]{memberId});
                    window.dispose();
                }

            } catch (SQLException ex) {
                // Duplicate registration attempt (unique key violation)
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(window, 
                        "You have already registered for this event!", 
                        "Duplicate Registration", 
                        JOptionPane.WARNING_MESSAGE);
                } else if (ex.getErrorCode() == 1452) {
                    // Foreign key constraint failure (member_id not found in members table)
                    JOptionPane.showMessageDialog(window, 
                        "Member ID '" + memberId + "' does not exist. Please use a valid registered Member ID.", 
                        "Invalid Member ID", 
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(window, 
                        "Database Error: " + ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        cancelBtn.addActionListener(e -> {
            upcomingeventss.main(null);
            window.dispose();
        });

        // Add components to window
        window.add(titleLabel);
        window.add(eventLabel);
        window.add(nameLabel);
        window.add(nameField);
        window.add(idLabel);
        window.add(idField);
        window.add(phoneLabel);
        window.add(phoneField);
        window.add(deptLabel);
        window.add(deptBox);
        window.add(submitBtn);
        window.add(cancelBtn);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        if (args != null && args.length >= 2) {
            new EventRegistration(args[0], args[1]);
        } else {
            new EventRegistration();
        }
    }
}