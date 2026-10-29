import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RegistrationFrame extends JFrame {

    private String memberId = "MEM001"; // Fallback member ID for testing

    public RegistrationFrame() {
        this("MEM001");
    }

    public RegistrationFrame(String memberId) {
        if (memberId != null && !memberId.trim().isEmpty()) {
            this.memberId = memberId.trim();
        }

        setTitle("College Club Activity Manager - My Registrations");
        setSize(680, 560);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header Panel
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));

        JLabel title = new JLabel("My Registrations", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        headerPanel.add(title);

        JLabel subtitle = new JLabel("Registered activities for Member: " + this.memberId, JLabel.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(Color.DARK_GRAY);
        headerPanel.add(subtitle);

        add(headerPanel, BorderLayout.NORTH);

        // Events List Container (Vertical layout of dynamic cards)
        JPanel listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Load data dynamically via SQL JOIN
        loadUserRegistrations(listContainer);

        JScrollPane scrollPane = new JScrollPane(listContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Controls
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 15, 25));

        JCheckBox reminder = new JCheckBox("Enable event reminders");
        bottomPanel.add(reminder, BorderLayout.WEST);

        JPanel buttonSubPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backBtn = new JButton("Back to Events");
        backBtn.setPreferredSize(new Dimension(140, 32));
        backBtn.addActionListener(e -> {
            upcomingeventss.main(null);
            dispose();
        });
        buttonSubPanel.add(backBtn);
        bottomPanel.add(buttonSubPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadUserRegistrations(JPanel listContainer) {
        // Query joins registrations and events table based on event_id
        String sql = "SELECT e.event_name, e.club_name, e.event_date, e.venue, r.status, r.registration_date " +
                     "FROM registrations r " +
                     "INNER JOIN events e ON r.event_id = e.event_id " +
                     "WHERE r.member_id = ? " +
                     "ORDER BY r.registration_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, this.memberId);
            ResultSet rs = stmt.executeQuery();

            boolean hasRegistrations = false;

            while (rs.next()) {
                hasRegistrations = true;

                String eventName = rs.getString("event_name");
                String clubName = rs.getString("club_name");
                String eventDate = rs.getString("event_date");
                String venue = rs.getString("venue");
                String status = rs.getString("status");

                // Single Event Card Panel
                JPanel card = new JPanel(new BorderLayout(10, 5));
                card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 0, 8, 0),
                    BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                    )
                ));

                // Left Info: Event Name & Details
                JPanel infoPanel = new JPanel(new GridLayout(2, 1, 2, 2));
                JLabel nameLabel = new JLabel(eventName);
                nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
                infoPanel.add(nameLabel);

                JLabel detailLabel = new JLabel(clubName + "  |  " + eventDate + "  |  " + venue);
                detailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                detailLabel.setForeground(Color.GRAY);
                infoPanel.add(detailLabel);

                card.add(infoPanel, BorderLayout.CENTER);

                // Right Info: Status Badge
                JLabel statusLabel = new JLabel(status.toUpperCase(), JLabel.RIGHT);
                statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
                if ("Confirmed".equalsIgnoreCase(status)) {
                    statusLabel.setForeground(new Color(0, 140, 70));
                } else {
                    statusLabel.setForeground(new Color(210, 105, 30));
                }
                card.add(statusLabel, BorderLayout.EAST);

                listContainer.add(card);
            }

            if (!hasRegistrations) {
                JLabel emptyLabel = new JLabel("No registered events found for Member ID: " + this.memberId, JLabel.CENTER);
                emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                emptyLabel.setForeground(Color.GRAY);
                emptyLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
                listContainer.add(emptyLabel);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            new RegistrationFrame(args[0]);
        } else {
            new RegistrationFrame();
        }
    }
}