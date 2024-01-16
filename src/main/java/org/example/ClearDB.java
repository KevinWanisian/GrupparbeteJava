package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class ClearDB {
        static String filename = "bookingsystem.db";
        static String url = STR."jdbc:sqlite:src/main/java/org/example/\{filename}";
        public static void main(String[] args) {
            //DeleteTableTables();
            //DeleteTableBookings();
            //ClearTableTables();
            //ClearTableBookings();
        }

        static void DeleteTableTables() {
            // SQL koden som skickas till databasen
            // Bords-tabell
            String query = "DROP TABLE Tables";

            try {
                Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
                Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
                cursor.execute(query); // Skickar sql queryt till databasen
                System.out.println("Table-tabell borttagen");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        static void DeleteTableBookings() {
            // SQL koden som skickas till databasen
            // Bords-tabell
            String query = "DROP TABLE Bookings";

            try {
                Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
                Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
                cursor.execute(query); // Skickar sql queryt till databasen
                System.out.println("Bookings-tabell borttagen");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        static void ClearTableTables() {
            // SQL koden som skickas till databasen
            // Bords-tabell
            String query = "DELETE FROM Tables";

            try {
                Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
                Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
                cursor.execute(query); // Skickar sql queryt till databasen
                System.out.println("Table-tabell rensad");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        static void ClearTableBookings() {
            // SQL koden som skickas till databasen
            // Bords-tabell
            String query = "DELETE FROM Bookings";

            try {
                Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
                Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
                cursor.execute(query); // Skickar sql queryt till databasen
                System.out.println("Bookings-tabell rensad");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

}
