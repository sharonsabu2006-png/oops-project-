import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CoordinatorDashboard extends JFrame {

    // Colors
    Color navy = new Color(16, 42, 86);
    Color blue = new Color(23, 105, 224);
    Color lightBlue = new Color(232, 241, 255);
    Color background = new Color(244, 247, 251);
    Color textColor = new Color(23, 32, 51);
    Color gray = new Color(110, 120, 135);

    public CoordinatorDashboard() {

        setTitle("ClubConnect - Coordinator Dashboard");
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(background);

    

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(230, 720));
        sidebar.setBackground(navy);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(25, 15, 20, 15));

        JLabel logo = new JLabel("🎓  Club Activity Manager");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 21));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(40));

        addMenu(sidebar, "▣   Dashboard", true);
        addMenu(sidebar, "🏛   My Club", false);
        addMenu(sidebar, "📅   Activities", false);
        addMenu(sidebar, "👥   Members", false);
        addMenu(sidebar, "📝   Registrations", false);
        addMenu(sidebar, "📢   Announcements", false);
        addMenu(sidebar, "👤   Profile", false);

        mainPanel.add(sidebar, BorderLayout.WEST);

       

        JPanel content = new JPanel();
        content.setBackground(background);
        content.setLayout(new BorderLayout());
        content.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Top section
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Coordinator Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(textColor);

        JLabel subtitle = new JLabel(
                "Manage your club activities and members."
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(gray);

        heading.add(title);
        heading.add(Box.createVerticalStrut(5));
        heading.add(subtitle);

        top.add(heading, BorderLayout.WEST);

        JPanel profile = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profile.setOpaque(false);

        JLabel profileIcon = new JLabel("👤");
        profileIcon.setFont(new Font("Arial", Font.PLAIN, 25));

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

        profile.add(profileIcon);
        profile.add(profileText);

        top.add(profile, BorderLayout.EAST);

        content.add(top, BorderLayout.NORTH);

       

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BorderLayout(0, 20));

        // Statistics
        JPanel stats = new JPanel(new GridLayout(1, 4, 18, 0));
        stats.setOpaque(false);

        stats.add(createStatCard("🏛", "1", "My Club"));
        stats.add(createStatCard("👥", "86", "Club Members"));
        stats.add(createStatCard("📅", "6", "Upcoming Activities"));
        stats.add(createStatCard("📝", "120", "Total Registrations"));

        center.add(stats, BorderLayout.NORTH);

        // Bottom content
        JPanel bottom = new JPanel(new GridLayout(1, 2, 20, 0));
        bottom.setOpaque(false);

        bottom.add(createActivitiesPanel());
        bottom.add(createRequestsPanel());

        center.add(bottom, BorderLayout.CENTER);

        content.add(center, BorderLayout.CENTER);

        mainPanel.add(content, BorderLayout.CENTER);

        add(mainPanel);
    }

    

    private void addMenu(
            JPanel sidebar,
            String text,
            boolean active) {

        JLabel item = new JLabel(text);
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Arial", Font.PLAIN, 14));
        item.setBorder(new EmptyBorder(12, 12, 12, 12));
        item.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (active) {
            item.setOpaque(true);
            item.setBackground(blue);
        }

        sidebar.add(item);
        sidebar.add(Box.createVerticalStrut(5));
    }

 

    private JPanel createStatCard(
            String icon,
            String number,
            String label) {

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(18, 18, 18, 18));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        JLabel numberLabel = new JLabel(number);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 26));
        numberLabel.setForeground(textColor);

        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        textLabel.setForeground(gray);

        card.add(iconLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(numberLabel);
        card.add(Box.createVerticalStrut(3));
        card.add(textLabel);

        return card;
    }

  

    private JPanel createActivitiesPanel() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 15, 20));

        JLabel heading = new JLabel("Upcoming Activities");
        heading.setFont(new Font("Arial", Font.BOLD, 19));

        panel.add(heading, BorderLayout.NORTH);

        JPanel list = new JPanel();
        list.setOpaque(false);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));

        addActivity(
                list,
                "💻",
                "Hackathon 2026",
                "October 5, 2026 • 9:00 AM"
        );

        addActivity(
                list,
                "☕",
                "Java Workshop",
                "October 8, 2026 • 10:00 AM"
        );

        addActivity(
                list,
                "🌐",
                "Web Development Workshop",
                "October 15, 2026 • 11:00 AM"
        );

        addActivity(
                list,
                "🏆",
                "Inter College Competition",
                "October 20, 2026 • 9:30 AM"
        );

        panel.add(list, BorderLayout.CENTER);

        return panel;
    }

    private void addActivity(
            JPanel list,
            String icon,
            String activityName,
            String date) {

        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setOpaque(false);
        row.setBorder(new EmptyBorder(13, 0, 13, 0));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 22));

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

        row.add(iconLabel, BorderLayout.WEST);
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

        addRequest(
                list,
                "New Member Request",
                "Rahul S • Computer Science"
        );

        addRequest(
                list,
                "New Member Request",
                "Arjun K • Computer Science"
        );

        addRequest(
                list,
                "Activity Approval",
                "Technical Workshop"
        );

        addRequest(
                list,
                "Event Registration",
                "Hackathon 2026"
        );

        panel.add(list, BorderLayout.CENTER);

        return panel;
    }

    private void addRequest(
            JPanel list,
            String requestName,
            String description) {

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

            CoordinatorDashboard dashboard =
                    new CoordinatorDashboard();

            dashboard.setVisible(true);
        });
    }
}