import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Club Activity Manager");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Member Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(190, 30, 250, 40);
        add(title);

        JLabel welcome = new JLabel("Welcome Student!");
        welcome.setBounds(40, 90, 200, 30);
        add(welcome);

        JLabel clubs = new JLabel("Clubs Joined : 4");
        clubs.setBounds(40, 140, 180, 40);
        add(clubs);

        JLabel activities = new JLabel("Upcoming Activities : 6");
        activities.setBounds(40, 190, 200, 40);
        add(activities);

        JLabel registration = new JLabel("My Registrations : 3");
        registration.setBounds(40, 240, 200, 40);
        add(registration);

        JLabel heading = new JLabel("Upcoming Events");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setBounds(300, 100, 200, 30);
        add(heading);

        JLabel event1 = new JLabel("Hackathon 2026");
        event1.setBounds(300, 150, 200, 30);
        add(event1);

        JLabel event2 = new JLabel("Design Workshop");
        event2.setBounds(300, 200, 200, 30);
        add(event2);

        JLabel event3 = new JLabel("Open Mic Night");
        event3.setBounds(300, 250, 200, 30);
        add(event3);

        JButton viewClubs = new JButton("View Clubs");
        viewClubs.setBounds(40, 330, 140, 40);
        add(viewClubs);

        JButton viewActivities = new JButton("Activities");
        viewActivities.setBounds(210, 330, 140, 40);
        add(viewActivities);

        JButton logout = new JButton("Logout");
        logout.setBounds(380, 330, 120, 40);
        add(logout);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DashboardFrame();
    }
}