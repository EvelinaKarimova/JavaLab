package helpers;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

    public static Connection getConnection() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\PC\\Desktop\\EvelinaKarimova\\JavaLab\\RabbitMQTask2\\src\\ru\\itis\\documents\\db.properties"));

            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String url = properties.getProperty("db.url");
            Connection connection;
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        }catch (IOException | SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
