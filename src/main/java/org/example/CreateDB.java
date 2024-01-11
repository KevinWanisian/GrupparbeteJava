package org.example;
import java.sql.*;

public class CreateDB {
    // Anger namn på databasen
    static String filename = "bookingsystem.db";
    static String url = "jdbc:sqlite:src/main/java/org/example/" + filename;
    public static void main(String[] args) {
        CreateNewDB();
        CreateTableTables();
        CreateTableCustomers();
        CreateTableBookings();
    }
    // Testar att ansluta till db filen, om ingen fil finns så skapas en ny automatiskt
    static void CreateNewDB() {
        try (Connection kontakt = DriverManager.getConnection(url)) {
            if (kontakt != null) {
                System.out.println(filename + "Skapad");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // Skapar en ny tabell i databasen
    static void CreateTableTables() {
        // SQL koden som skickas till databasen
        // Bords-tabell
        String query = "CREATE TABLE IF NOT EXISTS Tables"
                + "(TableID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "TableNumber INTEGER,"
                + "Seats INTEGER );";

        try {
            Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
            Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
            cursor.execute(query); // Skickar sql queryt till databasen
            System.out.println("Table-tabell skapad");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Skapar en ny tabell i databasen
    static void CreateTableCustomers() {
        // SQL koden som skickas till databasen
        // Kund-tabell
        String query = "CREATE TABLE IF NOT EXISTS Customers"
                + "(CustomerID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Name TEXT,"
                + "Email TEXT,"
                + "Phone TEXT);";

        try {
            Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
            Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
            cursor.execute(query); // Skickar sql queryt till databasen
            System.out.println("Customer-tabell skapad");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // Skapar en ny tabell i databasen
    static void CreateTableBookings() {
        // SQL koden som skickas till databasen
        // Boknings-tabell
        String query = "CREATE TABLE IF NOT EXISTS Bookings"
                + "(BookingID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "CustomerID INTEGER,"
                + "TableID INTEGER,"
                + "Day TEXT,"
                + "Time TEXT,"
                + "FOREIGN KEY (CustomerID)"
                + "REFERENCES Customers (CustomerID)"
                + "FOREIGN KEY (TableID)"
                + "REFERENCES Tables (TableID));";

        try {
            Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
            Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
            cursor.execute(query); // Skickar sql queryt till databasen
            System.out.println("Booking-tabell skapad");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
