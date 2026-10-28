import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class ClubDetails {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Club Details - IEEE");
        frame.setSize(400, 420);
        frame.setLayout(new BorderLayout(10, 10)); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        JPanel titlePanel = new JPanel(new GridLayout(2, 1)); 
        titlePanel.add(new JLabel("IEEE STUDENT BRANCH", JLabel.CENTER));
        titlePanel.add(new JLabel("120 Members", JLabel.CENTER));
        frame.add(titlePanel, BorderLayout.NORTH);

        JTextArea detailsArea = new JTextArea(
            "Purpose:\n" +
            "IEEE focuses on advancing technology,\n" +
            "hardware projects, and technical papers.\n\n" +
            "Upcoming Events:\n" +
            "- Tech Symposium 2026\n" +
            "- Circuit Debugging Workshop\n"
        );
        detailsArea.setEditable(false);
        frame.add(detailsArea, BorderLayout.CENTER);

        // Updated southPanel to 4 rows to fit the Back button
        JPanel southPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        southPanel.add(new JLabel("Status: Open for Registration", JLabel.CENTER));
        southPanel.add(new JLabel("Contact: ieeecep@gmail.com | +91 9876543210", JLabel.CENTER));
        
        JButton joinButton = new JButton("JOIN CLUB");
        joinButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Sent request to join club. Await approval.");
        });
        southPanel.add(joinButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            Clubslist.main(null); // Reopens the Clubs List frame
            frame.dispose();      // Closes this details frame
        });
        southPanel.add(backButton);

        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}