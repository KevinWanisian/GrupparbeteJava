package org.example;

import java.sql.*;

import java.util.ArrayList;

public class DeleteRow {
    static String url = CreateDB.url;

    // Tar bort en rad från databasen baserat på id nummer
    // Returnerar rad som indikerar ifall queryt har gjort någon skillnad
    static String deleteRowFromTable(int id) {
        String query = "DELETE FROM Bookings WHERE BookingID = ? ";

        try {
            // Utför queryt
            Connection kontakt = new GetConnection().kontakt(url);
            if (!CheckDB.getID(id, kontakt)) {
                return "Okänt Id";
            }
            PreparedStatement cursor = kontakt.prepareStatement(query);
            cursor.setInt(1, id);
            cursor.executeUpdate();
            cursor.close();
            cursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Bokning borttagen";
    }
}
