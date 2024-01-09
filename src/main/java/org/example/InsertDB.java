package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDB {
    static String url = CreateDB.url;
    public static void main(String[] args) {
        InsertDB add = new InsertDB();
        add.insert("Tobias", 55);
    }
    // Skapar en ny koppling till databasen och returnerar Connection objektet
    private Connection kontakt() {
        Connection kontakt = null;
        try {
            kontakt = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kontakt;
    }
    // Sätter in parametervärden i tabellen
    public void insert(String name, int number) {
        // Ändra till värden som passar till uppgiften
        String query = "INSERT INTO test_table(name, number) VALUES(?, ?)";

        try {
            Connection kontakt = this.kontakt(); // Kallar kontakt metoden för att skapa en koplling till databasen
            PreparedStatement cursor = kontakt.prepareStatement(query);

            // Ändrar '?' i SQL strängen till parametervärdena
            cursor.setString(1, name);
            cursor.setInt(2, number);
            cursor.executeUpdate();
            System.out.println("Tabell uppdaterad");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
