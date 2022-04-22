import java.sql.*;

public class Database {
    private final String url = "jdbc:postgresql://123.30.234.11:5432/vaccine";
    private final String user = "tung491";
    private final String password = "Tung040901";
    
    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

