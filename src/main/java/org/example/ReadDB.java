package org.example;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class ReadDB {
    static String url = CreateDB.url;
    public static void main (String[] args){
        ReadDB read = new ReadDB();

        // Printar ut innehållet i tabellen Tables
        for (String i : read.selectAllFromTableTables()) {
            System.out.println(i);
        }
        // Printar ut alla bokningar i datumordning
        for (String i: read.selectAllBookingsSortedByDate()) {
            System.out.println(i);
        }
    }
    // Skickar ett SELECT query till databasen
    // Returnerar en Arraylist med värdena i test tabellen
    // Ändra till flera metoder så man kan kolla bokningar, namn osv
    public ArrayList<String> selectAllFromTableTables() {
        String query = "SELECT TableID, Seats FROM Tables;";
        ArrayList<String> lst_response = new ArrayList<String>();

        try {
            Connection kontakt = new GetConnection().kontakt(url);
            Statement cursor = kontakt.createStatement();
            ResultSet r = cursor.executeQuery(query);

            // Lägger till resultatet från databasen i en arraylist och returnerar den
            while (r.next()) {
                String id = String.valueOf(r.getInt("TableID"));
                String seats = String.valueOf(r.getInt("Seats"));

                lst_response.add(id + "\t" + "\t" + seats);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lst_response;
    }

    public ArrayList<String> selectAllBookingsSortedByDate() {
        String query = "SELECT Bookings.BookingID, Bookings.Name, Bookings.Phone, Bookings.Guests,"
                + "Tables.TableID, Bookings.Day, Bookings.Time FROM Bookings"
                + " JOIN Tables ON Bookings.TableID = Tables.TableID"
                + " ORDER BY Bookings.Day ASC, Bookings.Time ASC;";
        ArrayList<String> lst_response = new ArrayList<String>();

        try {
            Connection kontakt = new GetConnection().kontakt(url);
            Statement cursor = kontakt.createStatement();
            ResultSet r = cursor.executeQuery(query);

            // Lägger till resultatet från databasen i en arraylist och returnerar den
            while (r.next()) {
                String BookingID = String.valueOf(r.getInt("BookingID"));
                String Name = r.getString("Name");
                String Phone = r.getString("Phone");
                String Guests = String.valueOf(r.getInt("Guests"));
                String TableID = String.valueOf(r.getString("TableID"));
                String Day = r.getString("Day");
                String Time = r.getString("Time");


                lst_response.add(BookingID + "\t" + Day + "\t" + Time + "\t" + TableID + "\t" + Guests + "\t" + Name + "\t\t" + Phone);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lst_response;
    }
}
