import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CoordinatorDashboard extends JFrame {

    Color navy = new Color(16, 42, 86);
    Color blue = new Color(23, 105, 224);
    Color background = new Color(244, 247, 251);
    Color textColor = new Color(23, 32, 51);
    Color gray = new Color(110, 120, 135);

    public CoordinatorDashboard() {
        setTitle("College Club Activity Manager - Coordinator Dashboard");
        setSize(1150, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(background);

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(230, 700));
        sidebar.setBackground(navy);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(25, 15, 20, 15));

        JLabel logo = new JLabel("<html>Club Activity<br>Manager</html>");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 18));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(35));

        addMenu(sidebar, "Dashboard", true);
        addMenu(sidebar, "My Club", false);
        addMenu(sidebar, "Activities", false);
        addMenu(sidebar, "Members", false);
        addMenu(sidebar, "Registrations", false);
        addMenu(sidebar, "Logout", false);

        mainPanel.add(sidebar, BorderLayout.WEST);

        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setBackground(background);
        content.setBorder(new EmptyBorder(25, 30, 25, 30));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Coordinator Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(textColor);

        JLabel subtitle = new JLabel("Manage your club activities and members.");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(gray);

        heading.add(title);
        heading.add(Box.createVerticalStrut(5));
        heading.add(subtitle);

        top.add(heading, BorderLayout.WEST);

        JPanel profileText = new JPanel();
        profileText.setOpaque(false);
        profileText.setLayout(new BoxLayout(profileText, BoxLayout.Y_AXIS));

        JLabel name = new JLabel("Madhav P");
        name.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel role = new JLabel("Coordinator");
        role.setForeground(gray);
        role.setFont(new Font("Arial", Font.PLAIN, 12));

        profileText.add(name);
        profileText.add(role);

        top.add(profileText, BorderLayout.EAST);
        content.add(top, BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(0, 20));
        center.setOpaque(false);

        JPanel stats = new JPanel(new GridLayout(1, 4, 18, 0));
        stats.setOpaque(false);

        stats.add(createStatCard("1", "My Club"));
        stats.add(createStatCard("86", "Club Members"));
        stats.add(createStatCard("6", "Upcoming Activities"));
        stats.add(createStatCard("120", "Total Registrations"));

        center.add(stats, BorderLayout.NORTH);

        JPanel bottom = new JPanel(new GridLayout(1, 2, 20, 0));
        bottom.setOpaque(false);

        bottom.add(createActivitiesPanel());
        bottom.add(createRequestsPanel());

        center.add(bottom, BorderLayout.CENTER);
        content.add(center, BorderLayout.CENTER);
        mainPanel.add(content, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void addMenu(JPanel sidebar, String text, boolean active) {
        JButton item = new JButton(text);
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Arial", Font.PLAIN, 14));
        item.setBackground(active ? blue : navy);
        item.setFocusPainted(false);
        item.setBorderPainted(false);
        item.setHorizontalAlignment(SwingConstants.LEFT);
        item.setMaximumSize(new Dimension(200, 42));
        item.setAlignmentX(Component.LEFT_ALIGNMENT);

        item.addActionListener(e -> {
            if (text.equals("My Club")) {
                JOptionPane.showMessageDialog(this,
                        "Club Name: Tech Club\nCoordinator: Madhav P (CSE)\nTotal Members: 86\nActive Events: 6",
                        "My Club Details", JOptionPane.INFORMATION_MESSAGE);
            } else if (text.equals("Activities")) {
                showActivitiesDialog();
            } else if (text.equals("Members")) {
                showMembersDialog();
            } else if (text.equals("Registrations")) {
                showRegistrationsDialog();
            } else if (text.equals("Logout")) {
                dispose();
            }
        });

        sidebar.add(item);
        sidebar.add(Box.createVerticalStrut(8));
    }

    private void showActivitiesDialog() {
        JDialog dialog = new JDialog(this, "Club Activities - Ongoing & Completed", true);
        dialog.setSize(650, 420);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(2, 1, 0, 10));

        String[] ongoingCols = {"Event ID", "Ongoing Activity", "Date & Venue", "Who All Registered"};
        Object[][] ongoingData = {
            {"E101", "Hackathon 2026", "Oct 5, 2026 (Auditorium A)", "45 Registered (Rahul S, Arjun K...)"},
            {"E102", "Java Workshop", "Oct 8, 2026 (Lab 3)", "30 Registered (Sneha R, Vishnu S...)"}
        };
        JTable ongoingTable = new JTable(ongoingData, ongoingCols);
        JScrollPane sp1 = new JScrollPane(ongoingTable);
        sp1.setBorder(BorderFactory.createTitledBorder("Ongoing Activities & Event Details"));

        String[] compCols = {"Event ID", "Completed Activity", "Date", "Who All Took Part"};
        Object[][] compData = {
            {"E095", "Web Design Contest", "Sep 12, 2026", "38 Participated (1st Place: Pranav S)"},
            {"E088", "Linux Install Fest", "Aug 25, 2026", "52 Participated"}
        };
        JTable compTable = new JTable(compData, compCols);
        JScrollPane sp2 = new JScrollPane(compTable);
        sp2.setBorder(BorderFactory.createTitledBorder("Completed Activities"));

        dialog.add(sp1);
        dialog.add(sp2);
        dialog.setVisible(true);
    }

    private void showMembersDialog() {
        JDialog dialog = new JDialog(this, "List of Club Members (With Contact Info)", true);
        dialog.setSize(550, 320);
        dialog.setLocationRelativeTo(this);

        String[] cols = {"Member ID", "Student Name", "Semester", "Department", "Contact Info"};
        Object[][] data = {
            {"M101", "Rahul S", "S3", "CSE", "9876543210"},
            {"M102", "Arjun K", "S3", "CSE", "9876543211"},
            {"M103", "Sneha R", "S5", "IT", "9876543212"},
            {"M104", "Vishnu S", "S5", "EEE", "9876543213"},
            {"M105", "Anjali M", "S3", "CSE", "9876543214"}
        };

        JTable table = new JTable(data, cols);
        table.setRowHeight(25);
        dialog.add(new JScrollPane(table));
        dialog.setVisible(true);
    }

    private void showRegistrationsDialog() {
        JDialog dialog = new JDialog(this, "Event Registrations", true);
        dialog.setSize(520, 300);
        dialog.setLocationRelativeTo(this);

        String[] cols = {"Reg ID", "Student Name", "Event Name", "Status"};
        Object[][] data = {
            {"R201", "Rahul S", "Hackathon 2026", "Registered"},
            {"R202", "Arjun K", "Hackathon 2026", "Registered"},
            {"R203", "Sneha R", "Java Workshop", "Registered"}
        };

        JTable table = new JTable(data, cols);
        table.setRowHeight(25);
        dialog.add(new JScrollPane(table));
        dialog.setVisible(true);
    }

    private JPanel createStatCard(String number, String label) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(18, 18, 18, 18));

        JLabel numberLabel = new JLabel(number);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 26));
        numberLabel.setForeground(textColor);

        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        textLabel.setForeground(gray);

        card.add(numberLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(textLabel);

        return card;
    }

    private JPanel createActivitiesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 20, 15, 20));

        JLabel heading = new JLabel("Upcoming Activities");
        heading.setFont(new Font("Arial", Font.BOLD, 19));
        panel.add(heading, BorderLayout.NORTH);

        JPanel list = new JPanel();
        list.setOpaque(false);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));

        addActivity(list, "Hackathon 2026", "October 5, 2026 - 9:00 AM");
        addActivity(list, "Java Workshop", "October 8, 2026 - 10:00 AM");
        addActivity(list, "Web Development Workshop", "October 15, 2026 - 11:00 AM");
        addActivity(list, "Inter College Competition", "October 20, 2026 - 9:30 AM");

        panel.add(list, BorderLayout.CENTER);
        return panel;
    }

    private void addActivity(JPanel list, String activityName, String date) {
        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setOpaque(false);
        row.setBorder(new EmptyBorder(13, 0, 13, 0));

        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel name = new JLabel(activityName);
        name.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateLabel.setForeground(gray);

        details.add(name);
        details.add(Box.createVerticalStrut(4));
        details.add(dateLabel);

        JLabel status = new JLabel("Upcoming");
        status.setForeground(new Color(22, 131, 75));
        status.setFont(new Font("Arial", Font.PLAIN, 11));
        status.setOpaque(true);
        status.setBackground(new Color(223, 247, 233));
        status.setBorder(new EmptyBorder(5, 8, 5, 8));

        row.add(details, BorderLayout.CENTER);
        row.add(status, BorderLayout.EAST);

        list.add(row);

        JSeparator line = new JSeparator();
        line.setForeground(new Color(235, 238, 243));
        list.add(line);
    }

    private JPanel createRequestsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 20, 15, 20));

        JLabel heading = new JLabel("Pending Requests");
        heading.setFont(new Font("Arial", Font.BOLD, 19));
        panel.add(heading, BorderLayout.NORTH);

        JPanel list = new JPanel();
        list.setOpaque(false);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));

        addRequest(list, "New Member Request", "Rahul S - Computer Science");
        addRequest(list, "New Member Request", "Arjun K - Computer Science");
        addRequest(list, "Activity Approval", "Technical Workshop");
        addRequest(list, "Event Registration", "Hackathon 2026");

        panel.add(list, BorderLayout.CENTER);
        return panel;
    }

    private void addRequest(JPanel list, String requestName, String description) {
        JPanel row = new JPanel();
        row.setOpaque(false);
        row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
        row.setBorder(new EmptyBorder(12, 0, 12, 0));

        JLabel name = new JLabel(requestName);
        name.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel desc = new JLabel(description);
        desc.setFont(new Font("Arial", Font.PLAIN, 12));
        desc.setForeground(gray);

        JLabel pending = new JLabel("Pending");
        pending.setFont(new Font("Arial", Font.PLAIN, 11));
        pending.setForeground(new Color(166, 106, 0));
        pending.setOpaque(true);
        pending.setBackground(new Color(255, 243, 214));
        pending.setBorder(new EmptyBorder(5, 8, 5, 8));

        row.add(name);
        row.add(Box.createVerticalStrut(4));
        row.add(desc);
        row.add(Box.createVerticalStrut(7));
        row.add(pending);

        list.add(row);

        JSeparator line = new JSeparator();
        line.setForeground(new Color(235, 238, 243));
        list.add(line);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoordinatorDashboard dashboard = new CoordinatorDashboard();
            dashboard.setVisible(true);
        });
    }
}