import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

public class CoordinatorDashboard {

    public CoordinatorDashboard() {
        JFrame window = new JFrame("ClubConnect - Coordinator Dashboard");
        window.setSize(600, 440);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setResizable(false);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 600, 65);
        headerPanel.setBackground(new Color(16, 42, 86));
        headerPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Coordinator Dashboard");
        titleLabel.setBounds(30, 10, 300, 25);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JLabel subLabel = new JLabel("Club overview, member requests, and event scheduling");
        subLabel.setBounds(30, 35, 400, 18);
        subLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subLabel.setForeground(new Color(200, 215, 240));

        headerPanel.add(titleLabel);
        headerPanel.add(subLabel);
        window.add(headerPanel);

        // Basic Data / Stat Cards (Row 1)
        JPanel cardClub = makeBadge("Assigned Club", "IEEE Branch", 35, 80, 160, 58);
        JPanel cardMembers = makeBadge("Total Members", "120", 220, 80, 160, 58);
        JPanel cardEvents = makeBadge("Active Events", "3", 405, 80, 160, 58);

        // Basic Data / Stat Cards (Row 2)
        JPanel cardRequests = makeBadge("Pending Requests", "5", 35, 148, 160, 58);
        JPanel cardCategory = makeBadge("Category", "Technical", 220, 148, 160, 58);
        JPanel cardStatus = makeBadge("Club Status", "Active", 405, 148, 160, 58);

        window.add(cardClub);
        window.add(cardMembers);
        window.add(cardEvents);
        window.add(cardRequests);
        window.add(cardCategory);
        window.add(cardStatus);

        // The 3 Main Action Buttons
        JButton upcomingEventsBtn = new JButton("Upcoming Events");
        upcomingEventsBtn.setBounds(35, 230, 160, 42);

        JButton addEventBtn = new JButton("Add Event");
        addEventBtn.setBounds(220, 230, 160, 42);

        JButton pendingRequestsBtn = new JButton("Pending Requests");
        pendingRequestsBtn.setBounds(405, 230, 160, 42);

        // Logout Button
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(230, 310, 140, 36);
        logoutBtn.setBackground(new Color(220, 53, 69));
        logoutBtn.setForeground(Color.WHITE);

        // Button Actions
        upcomingEventsBtn.addActionListener(e -> {
            CoordinatorActivities.main(null);
            window.dispose();
        });

        addEventBtn.addActionListener(e -> {
            // 1. Create a panel with input fields for event details
            JPanel formPanel = new JPanel(new java.awt.GridLayout(4, 2, 8, 8));
            
            JTextField nameField = new JTextField();
            JTextField dateField = new JTextField();
            JTextField timeField = new JTextField();
            JTextField venueField = new JTextField();

            formPanel.add(new JLabel("Event Name:"));
            formPanel.add(nameField);
            formPanel.add(new JLabel("Date (e.g., 15-Nov-2026):"));
            formPanel.add(dateField);
            formPanel.add(new JLabel("Time (e.g., 10:00 AM):"));
            formPanel.add(timeField);
            formPanel.add(new JLabel("Venue:"));
            formPanel.add(venueField);

            // 2. Show the popup dialog on top of the current window
            int result = JOptionPane.showConfirmDialog(
                window, 
                formPanel, 
                "Add New Campus Event", 
                JOptionPane.OK_CANCEL_OPTION, 
                JOptionPane.PLAIN_MESSAGE
            );

            // 3. Handle user submission
            if (result == JOptionPane.OK_OPTION) {
                String eventName = nameField.getText().trim();
                String eventDate = dateField.getText().trim();
                String eventTime = timeField.getText().trim();
                String eventVenue = venueField.getText().trim();

                if (eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty() || eventVenue.isEmpty()) {
                    JOptionPane.showMessageDialog(window, "Please fill in all event fields.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Ready for your INSERT INTO events SQL query
                    JOptionPane.showMessageDialog(window, "Event \"" + eventName + "\" published successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        pendingRequestsBtn.addActionListener(e -> {
            CoordinatorActivities.main(null);
            window.dispose();
        });

        logoutBtn.addActionListener(e -> {
            new LoginScreen();
           window.dispose();
        });

        // Add Buttons to Frame
        window.add(upcomingEventsBtn);
        window.add(addEventBtn);
        window.add(pendingRequestsBtn);
        window.add(logoutBtn);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private JPanel makeBadge(String title, String val, int x, int y, int w, int h) {
        JPanel p = new JPanel();
        p.setBounds(x, y, w, h);
        p.setBackground(new Color(238, 242, 248));
        p.setLayout(null);

        JLabel t = new JLabel(title);
        t.setBounds(10, 6, w - 20, 15);
        t.setFont(new Font("Arial", Font.PLAIN, 11));
        t.setForeground(Color.DARK_GRAY);

        JLabel v = new JLabel(val);
        v.setBounds(10, 24, w - 20, 25);
        v.setFont(new Font("Arial", Font.BOLD, 17));
        v.setForeground(new Color(16, 42, 86));

        p.add(t);
        p.add(v);
        return p;
    }

    public static void main(String[] args) {
        new CoordinatorDashboard();
    }
}