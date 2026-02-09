package JDBC.util;

import java.io.InputStream;
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
        try (InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")){
            
            props.load(is);
            Class.forName(props.getProperty("db.driver"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // step02
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password"));
    }

}
