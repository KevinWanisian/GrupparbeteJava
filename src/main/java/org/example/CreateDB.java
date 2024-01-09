package org.example;
import java.sql.*;

public class CreateDB {
    // Anger namn på databasen
    static String filename = "test.db";
    static String url = "jdbc:sqlite:src/main/java/org/example/" + filename;
    public static void main(String[] args) {
        CreateNewDB();
        CreateTable();
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
    static void CreateTable() {
        // SQL koden som skickas till databasen
        // Ändra till värden som passar uppgiften
        String query = "CREATE TABLE IF NOT EXISTS test_table"
                + "(id integer PRIMARY KEY, "
                + "	name text NOT NULL, "
                + "number integer);";
        try {
            Connection kontakt = DriverManager.getConnection(url); // Skapar en ny koppling till filen vid urlen
            Statement cursor = kontakt.createStatement(); // Typ samma som cursor i python
            cursor.execute(query); // Skickar sql queryt till databasen
            System.out.println("Tabell skapad");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
