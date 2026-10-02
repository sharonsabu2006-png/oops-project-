import javax.swing.*;
import java.awt.*;

public class EventDetailsFrame extends JFrame {

    public EventDetailsFrame() {
        setTitle("Event Details");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219));
        JLabel headerLabel = new JLabel("Event Details");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel(new GridLayout(7, 2, 10, 15));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        detailsPanel.add(new JLabel("Event ID:"));
        detailsPanel.add(new JLabel("E101"));
        detailsPanel.add(new JLabel("Event Name:"));
        detailsPanel.add(new JLabel("Hackathon 2026"));
        detailsPanel.add(new JLabel("Host Club:"));
        detailsPanel.add(new JLabel("Tech Club"));
        detailsPanel.add(new JLabel("Date:"));
        detailsPanel.add(new JLabel("10-Oct-2026"));
        detailsPanel.add(new JLabel("Time:"));
        detailsPanel.add(new JLabel("09:00 AM"));
        detailsPanel.add(new JLabel("Venue:"));
        detailsPanel.add(new JLabel("Auditorium A"));
        detailsPanel.add(new JLabel("Status:"));
        detailsPanel.add(new JLabel("Open"));

        add(detailsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dispose());
        bottomPanel.add(closeBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventDetailsFrame().setVisible(true));
    }
}