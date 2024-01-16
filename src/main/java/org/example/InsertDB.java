package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDB {
    static String url = CreateDB.url;
    public static void main(String[] args) {
        InsertDB add = new InsertDB();
        //add.InsertIntoTableTables(4);
        //add.InsertIntoTableTables(4);
        //add.InsertIntoTableTables(4);
        //add.InsertIntoTableTables(4);
        //add.InsertIntoTableTables(4);
        add.InsertIntoTableBookings("Nalle Puh", "070 777 88 99", 3, 5,"2024-01-19", "17:00-19:00");
        add.InsertIntoTableBookings("Robin Hood", "070 888 55 22",4, 3, "2024-01-19", "19:00-21:00");
        add.InsertIntoTableBookings("Riddar Kato", "070 444 55 66", 2,1,"2024-01-18", "21:00-23:00");
        add.InsertIntoTableBookings("Mumin Pappa", "070 222 55 88", 4, 4,"2024-01-21", "19:00-21:00");
        add.InsertIntoTableBookings("Phileas Fogg", "070 333 55 77", 4, 2, "2024-01-20", "17:00-19:00");
    }

    // Sätter in parametervärden i tabellen Tables
    public void InsertIntoTableTables(int Seats) {
        // Ändra till värden som passar till uppgiften
        String query = "INSERT INTO Tables(Seats) VALUES(?)";

        try {
            // Kallar kontakt klassen för att skapa en koppling till databasen
            Connection kontakt = new GetConnection().kontakt(url);
            PreparedStatement cursor = kontakt.prepareStatement(query);

            // Ändrar '?' i SQL strängen till parametervärdena
            cursor.setInt(1, Seats);
            cursor.executeUpdate();
            System.out.println("Tabell 'Tables' uppdaterad");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Sätter in parametervärden i tabellen Bookings
    public void InsertIntoTableBookings(String Name, String Phone, int Guests, int TableID, String Day, String Time) {
        // Ändra till värden som passar till uppgiften
        String query = "INSERT INTO Bookings(Name, Phone, Guests, TableID, Day, Time) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            // Kallar kontakt klassen för att skapa en koppling till databasen
            Connection kontakt = new GetConnection().kontakt(url);
            PreparedStatement cursor = kontakt.prepareStatement(query);

            // Ändrar '?' i SQL strängen till parametervärdena
            cursor.setString(1, Name);
            cursor.setString(2, Phone);
            cursor.setInt(3, Guests);
            cursor.setInt(4, TableID);
            cursor.setString(5, Day);
            cursor.setString(6, Time);
            cursor.executeUpdate();
            System.out.println("Tabell 'Bookings' uppdaterad");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
