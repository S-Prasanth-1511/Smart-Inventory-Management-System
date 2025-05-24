import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    public static Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/inventory_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "2022506018";
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }
}
