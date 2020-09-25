package main;

import sql.ServerConnection;

import java.sql.SQLException;

public class RunMain {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ServerConnection c = new ServerConnection();
        c.register(5, "Hejsan", "LösenordYOLO");
        Main.main(args);

    }
}
