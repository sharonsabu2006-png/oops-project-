import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CoordinatorActivities extends JFrame {

    CoordinatorActivities() {

        setTitle("ClubConnect - Coordinator Activities");
        setSize(1000,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main=new JPanel(new GridLayout(1,2,20,0));
        main.setBackground(new Color(244,247,251));
        main.setBorder(new EmptyBorder(35,35,35,35));

        // UPCOMING ACTIVITIES
        JPanel activities=box("Upcoming Activities");

        String[][] a={
            {"💻","Hackathon 2026","October 5, 2026 • 9:00 AM"},
            {"☕","Java Workshop","October 8, 2026 • 10:00 AM"},
            {"🌐","Web Development Workshop","October 15, 2026 • 11:00 AM"},
            {"🏆","Inter College Competition","October 20, 2026 • 9:30 AM"}
        };

        JPanel alist=new JPanel();
        alist.setOpaque(false);
        alist.setLayout(new BoxLayout(alist,BoxLayout.Y_AXIS));

        for(String[] x:a){

            JLabel r=new JLabel(
                "<html>"+x[0]+"  <b>"+x[1]+
                "</b><br><font color='gray'>"+
                x[2]+"</font><br>"+
                "<font color='#16834B'>Upcoming</font></html>"
            );

            r.setBorder(new EmptyBorder(15,0,15,0));
            alist.add(r);
            alist.add(new JSeparator());
        }

        activities.add(alist,BorderLayout.CENTER);

        // PENDING REQUESTS
        JPanel requests=box("Pending Requests");

        String[][] r={
            {"New Member Request","Rahul S • Computer Science"},
            {"New Member Request","Arjun K • Computer Science"},
            {"Activity Approval","Technical Workshop"},
            {"Event Registration","Hackathon 2026"}
        };

        JPanel rlist=new JPanel();
        rlist.setOpaque(false);
        rlist.setLayout(new BoxLayout(rlist,BoxLayout.Y_AXIS));

        for(String[] x:r){

            JLabel item=new JLabel(
                "<html><b>"+x[0]+"</b><br>"+
                "<font color='gray'>"+x[1]+
                "</font><br>"+
                "<font color='#A66A00'>Pending</font></html>"
            );

            item.setBorder(new EmptyBorder(15,0,15,0));
            rlist.add(item);
            rlist.add(new JSeparator());
        }

        requests.add(rlist,BorderLayout.CENTER);

        main.add(activities);
        main.add(requests);

        add(main);
    }

    JPanel box(String title){

        JPanel p=new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(20,20,15,20));

        JLabel h=new JLabel(title);
        h.setFont(new Font("Arial",Font.BOLD,20));

        p.add(h,BorderLayout.NORTH);

        return p;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(
            () -> new CoordinatorActivities().setVisible(true)
        );
    }
}