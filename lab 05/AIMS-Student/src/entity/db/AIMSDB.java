package entity.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import java.sql.Connection;
import utils.*;

public class AIMSDB {

	private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;

    public static Connection getConnection() {
        if (connect != null) return connect;
        try {
			Class.forName("org.sqlite.JDBC");
            //DriverManager.registerDriver(new org.sqlite.JDBC());
            String url = "jdbc:sqlite:./assets/db/aims.db";
            System.out.println("da qua url");
            connect = DriverManager.getConnection(url);
            System.out.println("da qua connect");
            LOGGER.info("Connect database successfully");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        } 
        return connect;
    }
    

    public static void main(String[] args) {
        //AIMSDB aimsdb= new AIMSDB();
        AIMSDB.getConnection();
    }
}
