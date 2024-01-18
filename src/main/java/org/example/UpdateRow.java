package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateRow {
    static String url = CreateDB.url;

    // Tar bort en rad från databasen baserat på id nummer
    // Returnerar rad som indikerar ifall queryt har gjort någon skillnad
    static void updateRowFromTable(int id, String name, String phone, int number) {
        String query = """
                        UPDATE Bookings 
                        SET Name = ?, Phone = ?, Guests = ?
                        WHERE BookingID = ? """;

        // Kollar ursprunlig storlek på databasen
        int preSize = ReadDB.selectAllBookingsSortedByDate().length();

        try {
            // Utför queryt
            Connection kontakt = new GetConnection().kontakt(url);
            PreparedStatement cursor = kontakt.prepareStatement(query);
            cursor.setString(1, name);
            cursor.setString(2, phone);
            cursor.setInt(3, number);
            cursor.setInt(4, id);
            cursor.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
