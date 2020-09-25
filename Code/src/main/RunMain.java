package main;

import sql.ServerConnection;

import java.sql.SQLException;

public class RunMain {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ServerConnection c = new ServerConnection();
        /*
        c.register(7, "Hejsanyo1", "LösenordYOLO");
        c.register(7, "Hejsanyo2", "LösenordYOLO");
        c.register(7, "Hejsanyo3", "LösenordYOLO");
        */

        Main.main(args);

    }
}
