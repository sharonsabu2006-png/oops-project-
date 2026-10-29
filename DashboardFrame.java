import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DashboardFrame extends JFrame {

    private String memberId;
    private String studentName;

    // Default constructor for standalone preview/testing
    public DashboardFrame() {
        this("MEM001", "Student");
    }

    // Parameterized constructor called after successful login
    public DashboardFrame(String memberId, String studentName) {
        this.memberId = memberId;
        this.studentName = studentName;

        setTitle("Club Activity Manager - Member Dashboard");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JLabel title = new JLabel("Member Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(180, 25, 300, 40);
        add(title);

        // Personalized dynamic greeting
        JLabel welcome = new JLabel("Welcome, " + studentName + "!");
        welcome.setFont(new Font("Arial", Font.BOLD, 14));
        welcome.setBounds(40, 85, 240, 30);
        add(welcome);

        // Dynamic metric labels
        JLabel clubs = new JLabel("Total Clubs: Loading...");
        clubs.setBounds(40, 135, 200, 30);
        add(clubs);

        JLabel activities = new JLabel("Available Events: Loading...");
        activities.setBounds(40, 185, 200, 30);
        add(activities);

        JLabel registration = new JLabel("Member ID: " + memberId);
        registration.setBounds(40, 235, 200, 30);
        add(registration);

        // Events Section Header
        JLabel heading = new JLabel("Upcoming Events");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setBounds(310, 85, 200, 30);
        add(heading);

        // Event labels to populate from DB
        JLabel event1 = new JLabel("-");
        event1.setBounds(310, 135, 250, 30);
        add(event1);

        JLabel event2 = new JLabel("-");
        event2.setBounds(310, 185, 250, 30);
        add(event2);

        JLabel event3 = new JLabel("-");
        event3.setBounds(310, 235, 250, 30);
        add(event3);

        // Buttons
        JButton viewClubs = new JButton("View Clubs");
        viewClubs.setBounds(40, 340, 140, 40);
        add(viewClubs);
        viewClubs.addActionListener(e -> {
            Clubslist.main(null);
            dispose();
        });

        JButton viewEvents = new JButton("Events");
        viewEvents.setBounds(210, 340, 140, 40);
        add(viewEvents);
        viewEvents.addActionListener(e -> {
            upcomingeventss.main(null);
            dispose();
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(380, 340, 140, 40);
        add(logout);
        logout.addActionListener(e -> {
            new LoginScreen();
            dispose();
        });

        // Fetch live database values
        loadDashboardData(clubs, activities, event1, event2, event3);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadDashboardData(JLabel clubsLabel, JLabel eventsCountLabel, JLabel e1, JLabel e2, JLabel e3) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) return;

            // 1. Fetch total clubs count
            String clubCountSql = "SELECT COUNT(*) AS total FROM clubs";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(clubCountSql)) {
                if (rs.next()) {
                    clubsLabel.setText("Total Clubs: " + rs.getInt("total"));
                }
            }

            // 2. Fetch total events count
            String eventCountSql = "SELECT COUNT(*) AS total FROM events";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(eventCountSql)) {
                if (rs.next()) {
                    eventsCountLabel.setText("Available Events: " + rs.getInt("total"));
                }
            }

            // 3. Fetch top 3 upcoming events
            String eventsSql = "SELECT event_name, event_date FROM events LIMIT 3";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(eventsSql)) {
                
                JLabel[] eventLabels = {e1, e2, e3};
                int i = 0;
                while (rs.next() && i < 3) {
                    eventLabels[i].setText("• " + rs.getString("event_name") + " (" + rs.getString("event_date") + ")");
                    i++;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load dashboard data: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DashboardFrame();
    }
}