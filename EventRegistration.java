import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EventRegistration {

    public EventRegistration() {
        JFrame window = new JFrame("Event Registration - Student Portal");
        window.setSize(440, 360);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(null);
        window.setResizable(false);

        // Header
        JLabel titleLabel = new JLabel("Campus Event Registration");
        titleLabel.setBounds(130, 20, 220, 30);

        // Student Name
        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(40, 75, 110, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 75, 230, 28);

        // Student ID / Roll Number
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(40, 120, 110, 25);
        JTextField idField = new JTextField();
        idField.setBounds(150, 120, 230, 28);

        // Phone Number
        JLabel phoneLabel = new JLabel("Phone No:");
        phoneLabel.setBounds(40, 165, 110, 25);
        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 165, 230, 28);

        // Department Dropdown
        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(40, 210, 110, 25);
        String[] departments = {"CSE", "ECE", "EEE", "ME", "CE", "IT"};
        JComboBox<String> deptBox = new JComboBox<>(departments);
        deptBox.setBounds(150, 210, 230, 28);

        // Buttons
        JButton submitBtn = new JButton("Confirm Registration");
        submitBtn.setBounds(70, 270, 160, 35);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(245, 270, 110, 35);

        // Actions
        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || id.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please fill in all required fields.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(window, "Registration submitted successfully!", "Registration Confirmed", JOptionPane.INFORMATION_MESSAGE);
                upcomingeventss.main(null);
                window.dispose();
            }
        });

        cancelBtn.addActionListener(e -> {  upcomingeventss.main(null);window.dispose(); });

        // Add components to window
        window.add(titleLabel);
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
        new EventRegistration();
    }
}