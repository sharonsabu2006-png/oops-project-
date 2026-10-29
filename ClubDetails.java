import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClubDetails {

    public static void main(String[] args) {
        // Fallback default ID if launched directly for testing
        int selectedClubId = 1;
        if (args != null && args.length > 0) {
            try {
                selectedClubId = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                selectedClubId = 1;
            }
        }

        JFrame frame = new JFrame("Club Details");
        frame.setSize(440, 480);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Top Panel: Club Title & Coordinator
        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 3, 3));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel nameLabel = new JLabel("Loading Club...", JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(nameLabel);

        JLabel coordLabel = new JLabel("Coordinator: Loading...", JLabel.CENTER);
        coordLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        titlePanel.add(coordLabel);

        frame.add(titlePanel, BorderLayout.NORTH);

        // Center Area: Purpose and Dynamic Events
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setFont(new Font("Arial", Font.PLAIN, 13));
        detailsArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JScrollPane centerScroll = new JScrollPane(detailsArea);
        centerScroll.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        frame.add(centerScroll, BorderLayout.CENTER);

        // Bottom Panel: Status, Join, and Back Buttons
        JPanel southPanel = new JPanel(new GridLayout(3, 1, 6, 6));
        southPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 15, 20));

        JLabel statusLabel = new JLabel("Status: Open for Registration", JLabel.CENTER);
        southPanel.add(statusLabel);

        JButton joinButton = new JButton("JOIN CLUB");
        joinButton.setFont(new Font("Arial", Font.BOLD, 13));
        joinButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Sent request to join club. Await approval.");
        });
        southPanel.add(joinButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            Clubslist.main(null);
            frame.dispose();
        });
        southPanel.add(backButton);

        frame.add(southPanel, BorderLayout.SOUTH);

        // ------------------ DATABASE FETCH ------------------
        loadClubData(selectedClubId, frame, nameLabel, coordLabel, detailsArea);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void loadClubData(int clubId, JFrame frame, JLabel nameLabel, JLabel coordLabel, JTextArea detailsArea) {
        String clubSql = "SELECT c.club_name, c.description, co.coordinator_name " +
                         "FROM clubs c " +
                         "LEFT JOIN coordinators co ON c.coordinator_id = co.coordinator_id " +
                         "WHERE c.club_id = ?";

        String eventsSql = "SELECT event_name, event_date FROM events WHERE club_name = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) return;

            String currentClubName = "";

            // 1. Fetch club info and coordinator
            try (PreparedStatement stmt = conn.prepareStatement(clubSql)) {
                stmt.setInt(1, clubId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    currentClubName = rs.getString("club_name");
                    String desc = rs.getString("description");
                    String coordinator = rs.getString("coordinator_name");

                    frame.setTitle("Club Details - " + currentClubName);
                    nameLabel.setText(currentClubName.toUpperCase());
                    coordLabel.setText("Coordinator: " + (coordinator != null ? coordinator : "Not Assigned"));

                    StringBuilder content = new StringBuilder();
                    content.append("About Club:\n")
                           .append(desc != null ? desc : "No description provided.")
                           .append("\n\nUpcoming Events:\n");

                    // 2. Fetch events specific to this club
                    try (PreparedStatement eventStmt = conn.prepareStatement(eventsSql)) {
                        eventStmt.setString(1, currentClubName);
                        ResultSet eventRs = eventStmt.executeQuery();

                        boolean hasEvents = false;
                        while (eventRs.next()) {
                            hasEvents = true;
                            content.append("• ")
                                   .append(eventRs.getString("event_name"))
                                   .append(" (")
                                   .append(eventRs.getString("event_date"))
                                   .append(")\n");
                        }
                        if (!hasEvents) {
                            content.append("• No upcoming events scheduled.\n");
                        }
                    }

                    detailsArea.setText(content.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "Club record not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}