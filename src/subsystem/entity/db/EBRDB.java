package subsystem.entity.db;

import utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class EBRDB {

    private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
    private static Connection connect;

    private static String DB_URL = "jdbc:mysql://localhost:3306/ebr";
    private static String USER_NAME = "root";
    private static String PASSWORD = "luongbs01";

    public static Connection getConnection() {
        if (connect != null)
            return connect;
        try {
            connect = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            LOGGER.info("Connect database successfully");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        return connect;
    }

    public static void main(String[] args) {
        EBRDB.getConnection();
    }
}
