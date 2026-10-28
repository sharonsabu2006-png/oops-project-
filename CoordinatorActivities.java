import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CoordinatorActivities extends JFrame {

    JPanel activities, requests;

    CoordinatorActivities() {
        setTitle("ClubConnect - Coordinator Activities");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new GridLayout(1, 2, 20, 0));
        main.setBorder(new EmptyBorder(25, 25, 25, 25));
        main.setBackground(new Color(244, 247, 251));

        activities = box("Upcoming Activities");
        String[] a = {"Hackathon 2026", "Java Workshop",
            "Web Development Workshop", "Inter College Competition"};
        for (String s : a) addActivity(s);

        JButton add = new JButton("Add Activity");
        add.addActionListener(e -> {
            String s = JOptionPane.showInputDialog(this, "Enter activity name:");
            if (s != null && !s.trim().isEmpty()) addActivity(s.trim());
        });
        activities.add(add, BorderLayout.SOUTH);

        requests = box("Pending Requests");
        String[] r = {"Rahul S - New Member Request",
            "Arjun K - New Member Request",
            "Technical Workshop - Activity Approval",
            "Hackathon 2026 - Event Registration"};
        for (String s : r) addRequest(s);

        main.add(activities);
        main.add(requests);
        add(main);
    }

    JPanel box(String title) {
        JPanel p = new JPanel(new BorderLayout(10, 15));
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel h = new JLabel(title);
        h.setFont(new Font("Arial", Font.BOLD, 20));
        p.add(h, BorderLayout.NORTH);

        JPanel list = new JPanel();
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        list.setBackground(Color.WHITE);
        p.add(new JScrollPane(list), BorderLayout.CENTER);
        return p;
    }

    void addActivity(String name) {
        JPanel list = (JPanel) ((JScrollPane) activities.getComponent(1))
                .getViewport().getView();

        JPanel row = new JPanel(new BorderLayout(5, 5));
        row.setBackground(Color.WHITE);
        row.setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel label = new JLabel(name);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        JButton view = new JButton("View"), del = new JButton("Delete");
        view.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Activity: " + name));

        del.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Delete " + name + "?",
                    "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                list.remove(row);
                list.revalidate();
                list.repaint();
            }
        });

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.WHITE);
        buttons.add(view);
        buttons.add(del);
        row.add(label, BorderLayout.CENTER);
        row.add(buttons, BorderLayout.SOUTH);
        list.add(row);
        list.add(new JSeparator());
        list.revalidate();
        list.repaint();
    }

    void addRequest(String name) {
        JPanel list = (JPanel) ((JScrollPane) requests.getComponent(1))
                .getViewport().getView();

        JPanel row = new JPanel(new BorderLayout(5, 5));
        row.setBackground(Color.WHITE);
        row.setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel label = new JLabel(name);
        label.setFont(new Font("Arial", Font.PLAIN, 13));

        JButton approve = new JButton("Approve"), reject = new JButton("Reject");

        approve.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, name + " approved successfully!");
            list.remove(row);
            list.revalidate();
            list.repaint();
        });

        reject.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Reject this request?",
                    "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                list.remove(row);
                list.revalidate();
                list.repaint();
            }
        });

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.WHITE);
        buttons.add(approve);
        buttons.add(reject);
        row.add(label, BorderLayout.CENTER);
        row.add(buttons, BorderLayout.SOUTH);
        list.add(row);
        list.add(new JSeparator());
        list.revalidate();
        list.repaint();
    }

    public static void main(String[] args) {
        new CoordinatorActivities().setVisible(true);
    }
}