package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateRow {
    static String url = CreateDB.url;

    // Uppdaterar en rad från databasen baserat på id nummer
    // Returnerar rad som indikerar ifall queryt har gjort någon skillnad
    static String updateRowFromTable(int id, String name, String phone, int number) {
        String query = """                        
                UPDATE Bookings 
                        SET Name = ?, Phone = ?, Guests = ?
                        WHERE BookingID = ? """;

        try {
            // Utför queryt
            Connection kontakt = new GetConnection().kontakt(url);
            if (!CheckDB.getID(id, kontakt)) {
                return "Okänt Id";
            }
            PreparedStatement cursor = kontakt.prepareStatement(query);
            cursor.setString(1, name);
            cursor.setString(2, phone);
            cursor.setInt(3, number);
            cursor.setInt(4, id);
            cursor.executeUpdate();
            return "Ändring utförd";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Ingen ändring utförd";
    }
}