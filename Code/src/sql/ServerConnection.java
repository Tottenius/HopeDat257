package sql;

import javax.swing.*;
import java.sql.*;
import java.util.Properties;

/**
 * Thhis class handles handles communication between client and server.
 */

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

    public String changePassword(String userName, String oldPassword,  String newPassword){

        try{
            // Hämta gamla lösenordet
            PreparedStatement input1 = conn.prepareStatement( "SELECT password FROM users WHERE name = ?");
            input1.setString(1, userName);
            ResultSet rs = input1.executeQuery();
            // Få nästa i setet
            rs.next();
            String boolPassword = rs.getString("password");
            System.out.println("Gamla lösenordet från query: " + boolPassword);
            // Om det är rätt lösenord byt
            if(boolPassword.equals(oldPassword)) {
                PreparedStatement input2 = conn.prepareStatement("UPDATE users SET password = ? WHERE name = ?");
                input2.setString(1, newPassword);
                input2.setString(2, userName);
                input2.executeUpdate();
                return ("{\"success password change\":true}");
            }
            else{
                JOptionPane.showMessageDialog(null,"Wrong password!","Error",JOptionPane.ERROR_MESSAGE);
                return ("{\"failed password change\":false}");
            }

        } catch (SQLException e) {
            return "{\"failed password change\":false, \"error\":\""+getError(e)+"\"}";
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


    public boolean addFriend(String fromUser, String toUser) {

        try {

            //Check if user you wanna add exist
            PreparedStatement input = conn.prepareStatement("SELECT *  FROM Users WHERE name = ?");
            input.setString(1, toUser);
            ResultSet rs = input.executeQuery();

            if(!rs.next()) {

                JOptionPane.showMessageDialog(null, "No person exist with the given Username", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            PreparedStatement input2 = conn.prepareStatement("SELECT id  FROM Users WHERE name = ?");
            input2.setString(1, toUser);
            ResultSet toUserID = input2.executeQuery();

            PreparedStatement input3 = conn.prepareStatement("SELECT id  FROM Users WHERE name = ?");
            input3.setString(1, fromUser);
            ResultSet fromUserID = input3.executeQuery();

            if(!toUserID.next() || !fromUserID.next()) {

                return false;
            }

            //Check if they are already friends
            PreparedStatement input4 = conn.prepareStatement("SELECT friendshipstatus  FROM friends WHERE fromuserid = ? AND touserid = ?");
            input4.setInt(1, toUserID.getInt(1));
            input4.setInt(2, fromUserID.getInt(1));
            ResultSet rsStatus = input4.executeQuery();

            PreparedStatement input5 = conn.prepareStatement("INSERT INTO friends VALUES (?,?,?)");
            input5.setInt(1, fromUserID.getInt(1));
            input5.setInt(2, toUserID.getInt(1));
            input5.setBoolean(3,true);
            input5.executeUpdate();
               return true;

            }catch (SQLException e) {
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
