package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDB {
    static String url = CreateDB.url;
    public static void main(String[] args) {
        InsertDB add = new InsertDB();
        add.InsertIntoTableTables(10, 2);
        add.InsertIntoTableTables(11, 4);
        add.InsertIntoTableTables(12, 4);
        add.InsertIntoTableTables(13, 6);
        add.InsertIntoTableCustomers("Jenny Storm", "jenny.storm@mail.com", "070 111 22 33");
        add.InsertIntoTableCustomers("Nalle Puh", "nalle.puh@mail.com", "070 777 88 99");
        add.InsertIntoTableCustomers("Robin Hood", "robin.hood@mail.com", "070 444 55 66");
        add.InsertIntoTableCustomers("Riddar Kato", "sten.hjarta@mail.com", "070 111 44 77");
        add.InsertIntoTableCustomers("Mumin Pappa", "mumin.pappa@mail.com", "070 222 55 88");
        add.InsertIntoTableCustomers("Phileas Fogg", "mr.fogg@mail.com", "070 333 55 77");
        add.InsertIntoTableBookings(1, 2, "2024-01-19", "17:00-19:00");
        add.InsertIntoTableBookings(2, 2, "2024-01-19", "19:00-21:00");
        add.InsertIntoTableBookings(5, 4, "2024-01-18", "21:00-23:00");
        add.InsertIntoTableBookings(4, 1, "2024-01-21", "19:00-21:00");
        add.InsertIntoTableBookings(3, 3, "2024-01-20", "17:00-19:00");
        add.InsertIntoTableBookings(6, 2, "2024-01-21", "17:00-19:00");
    }

    // Sätter in parametervärden i tabellen Tables
    public void InsertIntoTableTables(int TableNumber, int Seats) {
        // Ändra till värden som passar till uppgiften
        String query = "INSERT INTO Tables(TableNumber, Seats) VALUES(?, ?)";

        try {
            // Kallar kontakt klassen för att skapa en koppling till databasen
            Connection kontakt = new GetConnection().kontakt(url);
            PreparedStatement cursor = kontakt.prepareStatement(query);

            // Ändrar '?' i SQL strängen till parametervärdena
            cursor.setInt(1, TableNumber);
            cursor.setInt(2, Seats);
            cursor.executeUpdate();
            System.out.println("Tabell 'Tables' uppdaterad");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Sätter in parametervärden i tabellen Customers
    public void InsertIntoTableCustomers(String Name, String Email, String Phone) {
        // Ändra till värden som passar till uppgiften
        String query = "INSERT INTO Customers(Name, Email, Phone) VALUES(?, ?, ?)";

        try {
            // Kallar kontakt klassen för att skapa en koppling till databasen
            Connection kontakt = new GetConnection().kontakt(url);
            PreparedStatement cursor = kontakt.prepareStatement(query);

            // Ändrar '?' i SQL strängen till parametervärdena
            cursor.setString(1, Name);
            cursor.setString(2, Email);
            cursor.setString(3, Phone);
            cursor.executeUpdate();
            System.out.println("Tabell 'Customers' uppdaterad");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Sätter in parametervärden i tabellen Bookings
    public void InsertIntoTableBookings(int CustomerID, int TableID, String Day, String Time) {
        // Ändra till värden som passar till uppgiften
        String query = "INSERT INTO Bookings(CustomerID, TableID, Day, Time) VALUES(?, ?, ?, ?)";

        try {
            // Kallar kontakt klassen för att skapa en koppling till databasen
            Connection kontakt = new GetConnection().kontakt(url);
            PreparedStatement cursor = kontakt.prepareStatement(query);

            // Ändrar '?' i SQL strängen till parametervärdena
            cursor.setInt(1, CustomerID);
            cursor.setInt(2, TableID);
            cursor.setString(3, Day);
            cursor.setString(4, Time);
            cursor.executeUpdate();
            System.out.println("Tabell 'Bookings' uppdaterad");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
