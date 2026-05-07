package JDBC_mvc.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtil {

    static Properties props = new Properties();

    static {
        try (InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")){
            props.load(is);
            Class.forName(props.getProperty("db.driver"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
            props.getProperty("db.url"),
            props.getProperty("db.user"),
            props.getProperty("db.password")
        );

    }
}
