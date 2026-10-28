import javax.swing.*;
import java.awt.*;

public class CoordinatorDashboard extends JFrame {

    Color navy = new Color(16, 42, 86);
    Color bg = new Color(244, 247, 251);
    JPanel content;

    CoordinatorDashboard() {
        setTitle("ClubConnect - Coordinator Dashboard");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new BorderLayout());
        JPanel side = new JPanel(new GridLayout(9, 1, 5, 5));
        side.setPreferredSize(new Dimension(220, 650));
        side.setBackground(navy);

        JLabel logo = new JLabel("  ClubConnect");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        side.add(logo);

        content = new JPanel(new BorderLayout(10, 20));
        content.setBackground(bg);

        String[] menu = {"Dashboard", "My Club", "Activities", "Members",
            "Registrations", "Announcements", "Profile", "Logout"};

        for (String name : menu) {
            JButton b = new JButton(name);
            b.setForeground(Color.WHITE);
            b.setBackground(navy);
            b.setFont(new Font("Arial", Font.PLAIN, 15));
            side.add(b);

            b.addActionListener(e -> {
                if (name.equals("Dashboard")) showDashboard();
                else if (name.equals("Logout")) {
                    if (JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to logout?", "Logout",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        dispose();
                        JOptionPane.showMessageDialog(null, "Logged out successfully!");
                    }
                } else showPage(name);
            });
        }

        main.add(side, BorderLayout.WEST);
        main.add(content, BorderLayout.CENTER);
        add(main);
        showDashboard();
    }

    void showDashboard() {
        content.removeAll();
        content.setLayout(new BorderLayout(10, 20));

        JLabel title = new JLabel("<html><h1>Coordinator Dashboard</h1>"
            + "<font color='gray' size='4'>Manage your club activities and members.</font></html>");
        content.add(title, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(2, 2, 20, 20));
        cards.setBackground(bg);
        cards.add(createCard("1", "My Club"));
        cards.add(createCard("86", "Club Members"));
        cards.add(createCard("6", "Upcoming Activities"));
        cards.add(createCard("120", "Total Registrations"));
        content.add(cards, BorderLayout.CENTER);

        content.revalidate();
        content.repaint();
    }

    JPanel createCard(String number, String name) {
        JPanel card = new JPanel(new GridLayout(2, 1));
        card.setBackground(Color.WHITE);
        JLabel n = new JLabel(number);
        n.setFont(new Font("Arial", Font.BOLD, 28));
        JLabel l = new JLabel(name);
        l.setFont(new Font("Arial", Font.PLAIN, 16));
        card.add(n);
        card.add(l);
        return card;
    }

    void showPage(String name) {
        content.removeAll();
        content.setLayout(new GridLayout(2, 1, 10, 10));

        JLabel title = new JLabel(name, JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        JLabel message = new JLabel("Welcome to the " + name + " page!", JLabel.CENTER);
        message.setFont(new Font("Arial", Font.PLAIN, 18));

        content.add(title);
        content.add(message);
        content.revalidate();
        content.repaint();
    }

    public static void main(String[] args) {
        new CoordinatorDashboard().setVisible(true);
    }
}