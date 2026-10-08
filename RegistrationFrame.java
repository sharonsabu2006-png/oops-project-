import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends JFrame {

    public RegistrationFrame() {

        setTitle("College Club Activity Manager");
        setSize(650, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("My Registrations");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(200, 30, 300, 40);
        add(title);

        JLabel subtitle = new JLabel("Your registered club activities");
        subtitle.setBounds(210, 75, 250, 25);
        add(subtitle);

        JLabel event1 = new JLabel("Hackathon 2026");
        event1.setFont(new Font("Arial", Font.BOLD, 17));
        event1.setBounds(50, 130, 250, 30);
        add(event1);

        JLabel club1 = new JLabel("Coding Club   |   October 5, 2026");
        club1.setBounds(50, 160, 300, 25);
        add(club1);

        JLabel status1 = new JLabel("REGISTERED");
        status1.setForeground(new Color(0, 130, 70));
        status1.setBounds(450, 145, 120, 25);
        add(status1);

        JLabel event2 = new JLabel("Design Workshop");
        event2.setFont(new Font("Arial", Font.BOLD, 17));
        event2.setBounds(50, 220, 250, 30);
        add(event2);

        JLabel club2 = new JLabel("Design Club   |   October 8, 2026");
        club2.setBounds(50, 250, 300, 25);
        add(club2);

        JLabel status2 = new JLabel("REGISTERED");
        status2.setForeground(new Color(0, 130, 70));
        status2.setBounds(450, 235, 120, 25);
        add(status2);

        JLabel event3 = new JLabel("Open Mic Night");
        event3.setFont(new Font("Arial", Font.BOLD, 17));
        event3.setBounds(50, 310, 250, 30);
        add(event3);

        JLabel club3 = new JLabel("Arts Club   |   October 12, 2026");
        club3.setBounds(50, 340, 300, 25);
        add(club3);

        JLabel status3 = new JLabel("REGISTERED");
        status3.setForeground(new Color(0, 130, 70));
        status3.setBounds(450, 325, 120, 25);
        add(status3);

        JCheckBox reminder = new JCheckBox("Enable event reminders");
        reminder.setBounds(50, 400, 220, 30);
        add(reminder);

        JButton back = new JButton("Back");
        back.setBounds(250, 450, 120, 35);
        add(back);

        setVisible(true);
    }

    public static void main(String[] args) {
        new RegistrationFrame();
    }
}
