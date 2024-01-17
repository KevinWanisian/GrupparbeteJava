package org.example;

import java.sql.*;

import java.util.ArrayList;

public class DeleteRow {
    static String url = CreateDB.url;

    // Tar bort en rad från databasen baserat på id nummer
    // Returnerar rad som indikerar ifall queryt har gjort någon skillnad
    static String deleteRowFromTable(int id) {
        String query = "DELETE FROM Bookings WHERE BookingID = ? ";

        // Kollar ursprunlig storlek på databasen
        int preSize = ReadDB.selectAllBookingsSortedByDate().length();

        try {
            // Utför queryt
            Connection kontakt = new GetConnection().kontakt(url);
            PreparedStatement cursor = kontakt.prepareStatement(query);
            cursor.setInt(1, id);
            cursor.executeUpdate();

            } catch (Exception e) {
                System.out.println(e.getMessage());
        }
        // Jämför storleken på databasen efter queryt
        int currSize = ReadDB.selectAllBookingsSortedByDate().length();
        if (currSize < preSize) {
            return "Bokning borttagen";
        } else {
            return "Okänt Id";
        }
    }
}
