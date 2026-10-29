import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 3306 is default for MySQL. Replace 'club_db' with whatever your database is named
    private static final String URL = "jdbc:mysql://localhost:3306/club_db";
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = ""; // Your MySQL root password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Establishes the connection using the driver jar we just linked
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Connected to MySQL successfully!");
        } catch (SQLException e) {
            System.out.println(" Database connection failed!");
            e.printStackTrace();
        }
        return conn;
    }

    // Quick test method to run right now
    public static void main(String[] args) {
        getConnection();
    }
}