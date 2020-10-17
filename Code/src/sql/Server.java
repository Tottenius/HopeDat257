package sql;

import java.io.IOException;
import java.net.*;
import java.sql.*;
import java.util.Properties;

/**
 * This class handles the server.
 */

public class Server {

    private Connection sqlConnection;
    ServerSocket serverSocket;

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.init();
        server.receiver();
    }

    // Initierar nödvändigheter för postgresql och serversocket
    private void init() throws Exception {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        sqlConnection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", props);
        serverSocket = new ServerSocket(9998);
    }

    private void receiver() throws IOException {
        while(true) {
            Socket socket = serverSocket.accept();
            new Thread(
                    new RequestHandler(socket, sqlConnection)
            ).start();
        }
    }
}