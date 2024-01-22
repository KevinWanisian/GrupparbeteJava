package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class CheckDB {
    static String url = CreateDB.url;

    public static Integer CheckBooking(String date, String time) {
        String query = "SELECT Day, Time, TableID FROM Bookings WHERE Day = ? AND Time = ?";
        ArrayList<Integer> response = new ArrayList<Integer>();

        try {
            Connection kontakt = new GetConnection().kontakt(url);
            PreparedStatement cursor = kontakt.prepareStatement(query);
            cursor.setString(1, date);
            cursor.setString(2, time);

            ResultSet r = cursor.executeQuery();

            // Hämtar TableID från de bokningar som är det aktuella datumet och tiden och sorterar dem i nummerordning
            while (r.next()) {
                Integer TableID = r.getInt("TableID");
                response.add(TableID);
            }
            Collections.sort(response);

            // Kollar om listan innehåller 5 bokningar eller mindre, returnerar ledigt bordsnummer om det finns eller null
            int size = response.size();
            if (size > 4) {
                return null;
            } else {
                return (size + 1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Boolean getID(int id, Connection kontakt) {
        String query = "SELECT * FROM Bookings WHERE Bookingid = ?";

        try {
            PreparedStatement cursor = kontakt.prepareStatement(query);
            cursor.setInt(1, id);

            ResultSet r = cursor.executeQuery();

            // Hämtar TableID från de bokningar som är det aktuella datumet och tiden och sorterar dem i nummerordni
            int tableID = r.getInt("TableID");
            cursor.close();
            if (tableID != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}