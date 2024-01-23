package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;

public class ClearDB {

    private static Connection kontakt;

    public ClearDB(Connection connection) {
        kontakt = connection;
    }

    public static void main(String[] args) {
        try {
            // Anropa GetConnection för att få en Connection
            Connection connection = GetConnection.kontakt("jdbc:sqlite:src/main/java/org/example/bookingsystem.db");

            // Skicka Connection till ClearDB-klassen
            ClearDB clearDB = new ClearDB(connection);
            clearDB.DeleteTableTables();
            clearDB.DeleteTableBookings();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    public static boolean DeleteTableTables() throws SQLException {
        String query = "DROP TABLE IF EXISTS Tables";

        try (Statement cursor = kontakt.createStatement()) {
            cursor.execute(query);
            System.out.println("Table-tabell borttagen");
            return true;
        }
    }

    public static boolean DeleteTableBookings() throws SQLException {
        String query = "DROP TABLE IF EXISTS Bookings";

        try (Statement cursor = kontakt.createStatement()) {
            cursor.execute(query);
            System.out.println("Bookings-tabell borttagen");
            return true;
        }
    }

    public boolean ClearTableTables() {
        String query = "DELETE FROM Tables";

        try (Statement cursor = kontakt.createStatement()) {
            cursor.execute(query);
            System.out.println("Table-tabell rensad");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean ClearTableBookings() {
        String query = "DELETE FROM Bookings";

        try (Statement cursor = kontakt.createStatement()) {
            cursor.execute(query);
            System.out.println("Bookings-tabell rensad");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

