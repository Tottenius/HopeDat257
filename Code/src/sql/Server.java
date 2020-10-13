package sql;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

/**
 * This class handles the server.
 */

public class Server {

    private Connection sqlConnection;
    ServerSocket serverSocket;
    Socket socket;

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.init();
        server.requestHandler();
    }

    // Initierar nödvändigheter för postgresql
    private void init() throws Exception {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        sqlConnection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", props);
    }

    // Anropar receiver för att ta emot ett meddelande och kallar på aktuell metod för att hantera meddelandet
    private void requestHandler() throws Exception {
        while(true) {
            String[] request = receiver();
            switch (Objects.requireNonNull(request)[0]) {
                case "login" -> login(request);
                case "register" -> register(request);
            }
            serverSocket.close();
        }
    }

    // Tar emot ett meddelande och return:ar innehållet som är uppdelat med mellanrum i en string array
    // tex om meddelandet är "login Calle Password5" blir arrayen: array[0] = login, array[1] = Calle och array[2] = Password5
    private String[] receiver() throws Exception {
        serverSocket = new ServerSocket(9999);
        socket = serverSocket.accept();
        System.out.println("ny connection");
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String message = dataInputStream.readUTF();
        return message.split("\\s");
    }

    // Skickar tillbaka ett meddelande "message" till klienten som kontaktat servern
    private void sender(String message) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(message);
    }

    // Kollar om användarnamnet och lösenordet stämmer för en registrerad användare och anropar sender för att skicka tillbaka true eller false till klienten
    private void login(String[] loginInfo) throws Exception {
        PreparedStatement preparedStatement = sqlConnection.prepareStatement( "SELECT *  FROM Users WHERE name = ? AND password = ? ");
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
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("INSERT INTO users VALUES ( 1, ?, ?) ");
            preparedStatement.setString(1, registerInfo[1]);
            preparedStatement.setString(2, registerInfo[2]);
            preparedStatement.executeUpdate();
            sender("success");
        } catch (SQLException e) {
            sender("fail");
        }
    }

}