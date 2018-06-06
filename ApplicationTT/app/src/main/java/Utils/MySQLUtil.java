package Utils;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
public class MySQLUtil {
    private static final String jdbcUrl="jdbc:mysql://120.79.138.108:3306/booklib";
    private static final String dbUser="root";
    private static final String dbPwd="123456";
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws java.sql.SQLException{
        return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
    }
}
