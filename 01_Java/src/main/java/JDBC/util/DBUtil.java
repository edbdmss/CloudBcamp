package JDBC.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    // properties 객체 불러오기
    static Properties props = new Properties();

    // step01
    static {
        try {
            props.load(new FileInputStream("db.properties"));
            Class.forName(props.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    // step02
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(props.getProperty("jdbc.url"),
                props.getProperty("jdbc.id"),
                props.getProperty("jdbc.pw"));
    }

}
