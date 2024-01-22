package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ClearDB {

        private Connection kontakt;
        static String filename = "bookingsystem.db";
        static String url = STR."jdbc:sqlite:src/main/java/org/example/\{filename}";

    public ClearDB(Connection connection) {
        this.kontakt = connection;
    }

    public static void main(String[] args) {
            //DeleteTableTables();
            //DeleteTableBookings();
            //ClearTableTables();
            //ClearTableBookings();
        }

        public boolean DeleteTableTables() {
            // SQL koden som skickas till databasen
            // Bords-tabell
            String query = "DROP TABLE IF EXISTS Tables";

            try {
                //Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
                Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
                cursor.execute(query); // Skickar sql queryt till databasen
                System.out.println("Table-tabell borttagen");
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public boolean DeleteTableBookings() {
            // SQL koden som skickas till databasen
            // Bords-tabell
            String query = "DROP TABLE IF EXISTS Bookings";

            try {
                //Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
                Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
                cursor.execute(query); // Skickar sql queryt till databasen
                System.out.println("Bookings-tabell borttagen");
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public boolean ClearTableTables() {
            // SQL koden som skickas till databasen
            // Bords-tabell
            String query = "DELETE FROM Tables";

            try {
                //Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
                Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
                cursor.execute(query); // Skickar sql queryt till databasen
                System.out.println("Table-tabell rensad");
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public boolean ClearTableBookings() {
            // SQL koden som skickas till databasen
            // Bords-tabell
            String query = "DELETE FROM Bookings";

            try {
                //Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
                Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
                cursor.execute(query); // Skickar sql queryt till databasen
                System.out.println("Bookings-tabell rensad");
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

}
