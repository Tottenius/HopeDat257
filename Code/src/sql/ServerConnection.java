package sql;

import javax.swing.*;
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

    // Register and add an user to the database, returns a tiny JSON document (as a String)

    public String register(int userid, String userName, String password){
        try{
            PreparedStatement input = conn.prepareStatement("INSERT INTO users VALUES ( ?, ?, ?) ");
            input.setInt(1, userid);
            input.setString(2, userName);
            input.setString(3, password);
            input.executeUpdate();
            return("{\"success registration\":true}");

        } catch (SQLException e) {
            return "{\"success registration\":false, \"error\":\""+getError(e)+"\"}";
        }
    }
    public boolean login( String userName, String password){
        Statement stmt = null;
        String realPassword = "";
        String realName = "";

        try{
            //För debug
            System.out.println("Försöker logga in med " + userName + " " + password );

            PreparedStatement input = conn.prepareStatement( "SELECT *  FROM Users WHERE name = ? AND password = ? ");
            input.setString(1, userName);
            input.setString(2, password);

            ResultSet rs = input.executeQuery();

            if(!rs.next()){

                JOptionPane.showMessageDialog(null,"Wrong Username or Password","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else
                return true;

        } catch (SQLException e) {
            return false;
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
