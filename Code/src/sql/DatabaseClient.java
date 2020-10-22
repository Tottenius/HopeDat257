package sql;

import java.io.*;
import java.net.Socket;

/**
 * Server stuff
 */

public class DatabaseClient {

    // Skickar ett meddelande (message) till servern och returnerar ett svar från servern
    private static String contactServer(String message) throws IOException {
        Socket socket = new Socket("217.209.131.86", 9998);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        output.writeUTF(message);
        String response = input.readUTF();
        socket.close();
        return response;
    }

    // Anropar contactServer för att kolla om det finns en användare med specificerat username och password
    // Returnerar true om det finns och false annars
    public static boolean login(String username, String password) throws IOException {
        String message = "login " + username + " " + password;
        String response = contactServer(message);
        return response.equals("true");
    }

    // Anropar contactServer för att försöka registrera en användare med specificerat username och password
    // Returnerar true om registreringen lyckades och false annars
    public static boolean register(String username, String password) throws IOException {
        String message = "register " + username + " " + password;
        String response = contactServer(message);
        return response.equals("success");
    }

    // Anropar contactServer för att försöka lägga till ett matvärde till en användare på ett specifikt datum
    // Returnerar true om det lyckades och false annars
    public static boolean addEmission(String username, String date, String food, int emission) throws IOException {
        String message = "addEmission " + username + " " + date + " " + food + " " + emission;
        String response = contactServer(message);
        return response.equals("success");
    }

    // Anropar contactServer för att hämta alla matvärden tillhörande en specifik användare
    // Returnerar en string array med matvärdena och dess data
    public static String[] getEmission(String username) throws IOException {
        String message = "getEmission " + username;
        String response = contactServer(message);
        return response.split("\\s");
    }

    // Anropar contactServer för att byta lösenordet på en användare
    // Returnerar true om lösenordet lyckades bytas
    public static boolean changePassword(String username, String oldPassword, String newPassword) throws IOException {
        String message = "changePassword " + newPassword + " " + username + " " + oldPassword;
        String response = contactServer(message);
        return response.equals("success");
    }

    // Anropar contactServer för att lägga till en vän
    // returnerar true om det lyckades
    public static boolean addFriend(String username, String friendToAdd) throws IOException {
        String message = "addFriend " + username + " " + friendToAdd;
        String response = contactServer(message);
        return response.equals("success");
    }

    // Anropar contactServer för att hämta användarnamnet på alla vänner
    // Returnerar en string array med alla användarnamn på ens vänner
    public static String[] getFriends(String username)throws IOException{
        String message = "getFriends " + username;
        String response = contactServer(message);
        return response.split("\\s");
    }
}