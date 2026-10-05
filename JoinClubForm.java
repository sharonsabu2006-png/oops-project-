import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JoinClubForm {
    public JoinClubForm(String clubName) {
        JFrame window = new JFrame("Application - " + clubName);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        window.setSize(400, 400);
        window.setLayout(null);
        window.setResizable(false);

        JLabel titleLabel = new JLabel("Join " + clubName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(120, 20, 250, 30);

        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(40, 80, 100, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 80, 180, 25);

        JLabel idLabel = new JLabel("Member ID:");
        idLabel.setBounds(40, 125, 100, 25);
        JTextField idField = new JTextField();
        idField.setBounds(150, 125, 180, 25);

        JLabel phoneLabel = new JLabel("Phone No:");
        phoneLabel.setBounds(40, 170, 100, 25);
        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 170, 180, 25);

        JLabel semLabel = new JLabel("Semester:");
        semLabel.setBounds(40, 215, 100, 25);
        String[] semesters = {"S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8"};
        JComboBox<String> semBox = new JComboBox<>(semesters);
        semBox.setBounds(150, 215, 180, 25);

        JButton submitButton = new JButton("Submit Application");
        submitButton.setBounds(60, 280, 150, 35);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(230, 280, 100, 35);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String phone = phoneField.getText();
            String sem = (String) semBox.getSelectedItem();

            if (name.isEmpty() || id.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please fill out all student details.");
            } else {
                JOptionPane.showMessageDialog(window, "Application submitted to " + clubName + "!");
                window.dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            window.dispose(); 
        });

        window.add(titleLabel);
        window.add(nameLabel);
        window.add(nameField);
        window.add(idLabel);
        window.add(idField);
        window.add(phoneLabel);
        window.add(phoneField);
        window.add(semLabel);
        window.add(semBox);
        window.add(submitButton);
        window.add(cancelButton);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

       public static void main(String[] args) {
        new JoinClubForm("IEEE Student Branch"); 
    }
}