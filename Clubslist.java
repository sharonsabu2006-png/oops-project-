import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Clubslist {

    public static void main(String[] args) {
        JFrame frame = new JFrame("ClubConnect - Clubs List");
        frame.setSize(650, 560);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel mainTitle = new JLabel("College Clubs", JLabel.CENTER);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 22));
        mainTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(mainTitle, BorderLayout.NORTH);

        // 2-column dynamic grid: rows grow automatically
        JPanel clubsGrid = new JPanel(new GridLayout(0, 2, 12, 12));
        clubsGrid.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // ------------------ DYNAMIC DATABASE FETCH WITH JOIN ------------------
        String sql = "SELECT c.club_id, c.club_name, c.description, co.coordinator_name " +
                     "FROM clubs c " +
                     "LEFT JOIN coordinators co ON c.coordinator_id = co.coordinator_id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean hasClubs = false;

            while (rs.next()) {
                hasClubs = true;
                int clubId = rs.getInt("club_id");
                String name = rs.getString("club_name");
                String desc = rs.getString("description");
                String coord = rs.getString("coordinator_name");

                // Build card panel for this club
                JPanel clubCard = new JPanel(new BorderLayout(5, 5));
                clubCard.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY, 1),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
                ));

                // Club Name
                JLabel nameLabel = new JLabel(name, JLabel.CENTER);
                nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                clubCard.add(nameLabel, BorderLayout.NORTH);

                // Description + Synchronized Coordinator Name
                JTextArea descArea = new JTextArea(desc + "\nCoord: " + (coord != null ? coord : "Not Assigned"));
                descArea.setWrapStyleWord(true);
                descArea.setLineWrap(true);
                descArea.setEditable(false);
                descArea.setFont(new Font("Arial", Font.PLAIN, 12));
                clubCard.add(descArea, BorderLayout.CENTER);

                // Action Button
                JButton viewBtn = new JButton("View Club");
                viewBtn.addActionListener(e -> {
                    ClubDetails.main(new String[]{String.valueOf(clubId), name});
                    frame.dispose();
                });
                clubCard.add(viewBtn, BorderLayout.SOUTH);

                clubsGrid.add(clubCard);
            }

            if (!hasClubs) {
                clubsGrid.add(new JLabel("No clubs registered yet.", JLabel.CENTER));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        // Put the grid inside a scroll pane so it scrolls if there are many clubs
        JScrollPane scrollPane = new JScrollPane(clubsGrid);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel: Back Button
        JPanel bottomPanel = new JPanel();
        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(100, 32));
        backBtn.addActionListener(e -> {
            DashboardFrame.main(null);
            frame.dispose();
        });
        bottomPanel.add(backBtn);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}