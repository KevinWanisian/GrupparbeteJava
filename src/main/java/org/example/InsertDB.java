package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDB {
    static String url = CreateDB.url;
    public static void main(String[] args) {
        InsertDB add = new InsertDB();
        add.insert("Jansson", 335);
    }

    // Sätter in parametervärden i tabellen
    public void insert(String name, int number) {
        // Ändra till värden som passar till uppgiften
        String query = "INSERT INTO test_table(name, number) VALUES(?, ?)";

        try {
            // Kallar kontakt klassen för att skapa en koplling till databasen
            Connection kontakt = new GetConnection().kontakt(url);
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
