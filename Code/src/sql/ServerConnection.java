package sql;

import org.postgresql.copy.CopyOut;

import java.sql.*;
import java.util.Properties;

public class ServerConnection {
    // For connecting to the portal database on your local machine
    static final String DATABASE = "jdbc:postgresql://localhost/postgres";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "postgres";

    // This is the JDBC connection object you will be using in your methods.
    private Connection conn;

    public ServerConnection() throws SQLException, ClassNotFoundException {
        this(DATABASE, USERNAME, PASSWORD);
    }

    public ServerConnection(String database, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        conn = DriverManager.getConnection(database, props);
    }
    // Register a student on a course, returns a tiny JSON document (as a String)

    public String register(int id, String userName, String password){
        try{
            System.out.println("ÄR inne yo");
            PreparedStatement input = conn.prepareStatement("INSERT INTO Users VALUES (id +'"+userName +"','" +password +"') ");
            System.out.println("Borde göra prepared statement yo");
            input.executeUpdate();
            return("{\"success registration\":true}");

        } catch (SQLException e) {
            return "{\"success registration\":false, \"error\":\""+getError(e)+"\"}";
        }
    }

    // This is a hack to turn an SQLException into a JSON string error message. No need to change.
    public static String getError(SQLException e){
        String message = e.getMessage();
        int ix = message.indexOf('\n');
        if (ix > 0) message = message.substring(0, ix);
        message = message.replace("\"","\\\"");
        return message;
    }

}
