package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class CheckDB {
    static String url = CreateDB.url;

    public static void main(String[] args) {
        // Test exempel
        //System.out.println(CheckDB.CheckBooking("2024-01-27", "17:00-19:00"));
        //System.out.println(CheckDB.CheckBooking("2024-01-28", "19:00-21:00"));

    }

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
}