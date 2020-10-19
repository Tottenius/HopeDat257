package sql;

import java.io.*;
import java.net.Socket;

/**
 * Server stuff
 */

public class DatabaseClient {

    // Skickar ett meddelande (message) till servern och returnerar ett svar från servern
    public static String contactServer(String message) throws IOException {
        Socket socket = new Socket("217.209.131.86", 9998);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        output.writeUTF(message);
        String response = input.readUTF();
        socket.close();
        return response;
    }

    public static boolean login(String username, String password) throws IOException {
        String message = "login " + username + " " + password;
        String response = contactServer(message);
        return response.equals("true");
    }

    public static boolean register(String username, String password) throws IOException {
        String message = "register " + username + " " + password;
        String response = contactServer(message);
        return response.equals("success");
    }

    public static boolean addEmission(String username, String date, String food, int emission) throws IOException {
        String message = "addEmission " + username + " " + date + " " + food + " " + emission;
        String response = contactServer(message);
        return response.equals("success");
    }

    // returnerar en string array med alla emmision värden för specificerat username och date
    public static String[] getEmission(String username) throws IOException {
        String message = "getEmission " + username;
        String response = contactServer(message);
        return response.split("\\s");
    }

    // Kanske ej behövs
    public static boolean removeEmission(String username, String date, String food) throws IOException {
        String message = "removeEmission " + username + " " + date +  " " + food;
        String response = contactServer(message);
        return response.equals("success");
    }

    public static boolean changePassword(String username, String oldPassword, String newPassword) throws IOException {
        String message = "changePassword " + newPassword + " " + username + " " + oldPassword;
        String response = contactServer(message);
        return response.equals("success");
    }

    public static boolean addFriend(String username, String friendToAdd) throws IOException {
        String message = "addFriend " + username + " " + friendToAdd;
        String response = contactServer(message);
        return response.equals("success");
    }

    public static String[] getFriends(String username)throws IOException{
        String message = "getFriends " + username;
        String response = contactServer(message);
        return response.split("\\s");
    }
}