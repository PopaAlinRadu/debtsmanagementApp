package ro.siit.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String pass = "admin";

    private static final ConnectionService instance = new ConnectionService();

    private Connection connection;

    private ConnectionService() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(url, user, pass);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionService getInstance() {
        return instance;
    }

    protected void closeConnection() throws Throwable {
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }
}
