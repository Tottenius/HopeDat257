package sql;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;

/**
 * Handles requests to and from the server
 */
public class RequestHandler implements Runnable{

    Socket socket;
    Connection sqlConnection;

    public RequestHandler(Socket socket, Connection sqlConnection) {
        this.socket = socket;
        this.sqlConnection = sqlConnection;
    }

    public synchronized void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String message = dataInputStream.readUTF();
            requestHandler(message.split("\\s"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestHandler(String[] request) throws Exception {
        System.out.println("ny connection from: " + request[1]);
        switch (request[0]) {
            case "login" -> login(request);
            case "register" -> register(request);
            case "addEmission" -> addEmission(request);
            case "getEmission" -> getEmission(request);
            case "removeEmission" -> removeEmission(request);
            case "changePassword" -> changePassword(request);
            case "addFriend" -> addFriend(request);
            case "getFriends" -> getFriends(request);
        }
    }
    // Skickar tillbaka ett meddelande "message" till klienten som kontaktat servern
    private void sender(String message) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(message);
    }

    // Kollar om användarnamnet och lösenordet stämmer för en registrerad användare och anropar sender för att skicka tillbaka true eller false till klienten
    private void login(String[] loginInfo) throws Exception {
        PreparedStatement preparedStatement = sqlConnection.prepareStatement( "SELECT *  FROM Users WHERE username = ? AND password = ? ");
        preparedStatement.setString(1, loginInfo[1]);
        preparedStatement.setString(2, loginInfo[2]);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            sender("true");
        } else {
            sender("false");
        }
    }

    private void register(String[] registerInfo) throws IOException {
        try{
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("INSERT INTO users VALUES ( ?, ?) ");
            preparedStatement.setString(1, registerInfo[1]);
            preparedStatement.setString(2, registerInfo[2]);
            preparedStatement.executeUpdate();
            sender("success");
        } catch (SQLException e) {
            sender("fail");
        }
    }

    private void addEmission(String[] addInfo) throws IOException {
        try{
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("INSERT INTO EmissionData VALUES ( ?, ?, ?, ?, 1)");
            preparedStatement.setString(1, addInfo[1]);
            preparedStatement.setDate(2, Date.valueOf(addInfo[2]));
            preparedStatement.setString(3, addInfo[3]);
            preparedStatement.setInt(4, Integer.parseInt(addInfo[4]));
            preparedStatement.executeUpdate();
            sender("success");
        } catch (SQLException e) {
            sender("fail");
        }
    }

    private void getEmission(String[] getInfo) throws IOException {
        try{
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("SELECT food, emission, date FROM EmissionData WHERE username = ?");
            preparedStatement.setString(1, getInfo[1]);
            ResultSet resultSet = preparedStatement.executeQuery();
            StringBuilder result = new StringBuilder();
            while(resultSet.next()) {
                result.append(resultSet.getString(1)).append(" ");
                result.append(resultSet.getString(2)).append(" ");
                result.append(resultSet.getString(3)).append(" ");
            }
            sender(result.toString());
        } catch (SQLException e) {
            sender("fail");
        }
    }

    private void removeEmission(String[] addInfo) throws IOException {
        try{
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("DELETE FROM EmissionData WHERE username = ? AND date = ? AND food = ?");
            preparedStatement.setString(1, addInfo[1]);
            preparedStatement.setDate(2, Date.valueOf(addInfo[2]));
            preparedStatement.setString(3, addInfo[3]);
            int result = preparedStatement.executeUpdate();
            if(result > 0) {
                sender("success");
            } else {
                sender("fail");
            }
        } catch (SQLException e) {
            sender("fail");
        }

    }

    private void changePassword(String[] passwordInfo) throws IOException {
        try{
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("UPDATE Users SET password = ? WHERE username = ? AND password = ?");
            preparedStatement.setString(1, passwordInfo[1]);
            preparedStatement.setString(2, passwordInfo[2]);
            preparedStatement.setString(3, passwordInfo[3]);
            int result = preparedStatement.executeUpdate();
            if(result > 0) {
                sender("success");
            } else {
                sender("fail");
            }
        } catch (SQLException e) {
            sender("fail");
        }
    }

    private void addFriend(String[] passwordInfo) throws IOException {
        try{
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("INSERT INTO Friends VALUES (?, ?)");
            preparedStatement.setString(1, passwordInfo[1]);
            preparedStatement.setString(2, passwordInfo[2]);
            preparedStatement.executeUpdate();
            sender("success");
        } catch (SQLException e) {
            sender("fail");
        }
    }

    private void getFriends(String[] getInfo) throws IOException {
        try{
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("SELECT toUser FROM friends WHERE fromUser = ?  ");
            preparedStatement.setString(1, getInfo[1]);
            ResultSet resultSet = preparedStatement.executeQuery();
            StringBuilder result = new StringBuilder();
            while(resultSet.next()) {
                result.append(resultSet.getString(1)).append(" ");
            }
            PreparedStatement preparedStatement2 = sqlConnection.prepareStatement("SELECT fromUser FROM friends WHERE toUser = ?  ");
            preparedStatement2.setString(1, getInfo[1]);
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            while(resultSet2.next()) {
                result.append(resultSet2.getString(1)).append(" ");
            }
            sender(result.toString());
        } catch (SQLException e) {
            sender("fail");
        }
    }
}