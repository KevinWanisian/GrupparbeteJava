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
        // Printar ut innehållet i tabellen Customers
        for (String i: read.selectAllFromTableCustomers()) {
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
        String query = "SELECT TableID, TableNumber, Seats FROM Tables;";
        ArrayList<String> lst_response = new ArrayList<String>();

        try {
            Connection kontakt = new GetConnection().kontakt(url);
            Statement cursor = kontakt.createStatement();
            ResultSet r = cursor.executeQuery(query);

            // Lägger till resultatet från databasen i en arraylist och returnerar den
            while (r.next()) {
                String id = String.valueOf(r.getInt("TableID"));
                String number = String.valueOf(r.getInt("TableNumber"));
                String seats = String.valueOf(r.getInt("Seats"));

                lst_response.add(id + "\t" + number + "\t" + seats);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lst_response;
    }

    public ArrayList<String> selectAllFromTableCustomers() {
        String query = "SELECT CustomerID, Name, Email, Phone FROM Customers;";
        ArrayList<String> lst_response = new ArrayList<String>();

        try {
            Connection kontakt = new GetConnection().kontakt(url);
            Statement cursor = kontakt.createStatement();
            ResultSet r = cursor.executeQuery(query);

            // Lägger till resultatet från databasen i en arraylist och returnerar den
            while (r.next()) {
                String CustomerID = String.valueOf(r.getInt("CustomerID"));
                String Name = r.getString("Name");
                String Email = r.getString("Email");
                String Phone = r.getString("Phone");

                lst_response.add(CustomerID + "\t" + Name + "\t\t" + Email + "\t\t" + Phone);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lst_response;
    }

    public ArrayList<String> selectAllBookingsSortedByDate() {
        String query = "SELECT Bookings.BookingID, Bookings.Day, Bookings.Time, Tables.TableNumber, Customers.Name FROM Bookings"
                + " JOIN Customers ON Bookings.CustomerID = Customers.CustomerID"
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
                String Day = r.getString("Day");
                String Time = r.getString("Time");
                String TableNumber = String.valueOf(r.getString("TableNumber"));
                String Name = r.getString("Name");
                lst_response.add(BookingID + "\t" + Day + "\t" + Time + "\t" + TableNumber + "\t" + Name);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lst_response;
    }
}
