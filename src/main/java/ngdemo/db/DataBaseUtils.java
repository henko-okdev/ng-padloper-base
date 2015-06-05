package ngdemo.db;


import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseUtils {

    public static void closeSilently(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (SQLException ignore) { }
    }

}
