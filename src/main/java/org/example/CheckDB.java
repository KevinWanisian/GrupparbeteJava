package org.example;

import java.sql.*;

public class CheckDB {
    static String url = CreateDB.url;
    public static void main(String[] args) {
        // Test exempel
        //System.out.println(CheckDB.CheckBooking("2024-01-18", "21:00-23:00"));
        //System.out.println(CheckDB.CheckBooking("2030-01-18", "21:00-23:00"));
    }
    public static String CheckBooking(String date, String time) {
        String query = "SELECT Day, Time FROM Bookings WHERE Day = ? AND Time = ?";

        try {
            Connection kontakt = new GetConnection().kontakt(url);
            PreparedStatement cursor = kontakt.prepareStatement(query);

            cursor.setString(1, date);
            cursor.setString(2, time);
            ResultSet r = cursor.executeQuery();

            int rowCount = 0;
            while (r.next()){
                rowCount += 1;
            }
            if (rowCount == 0) {
                return "Tiden är ledig";
            } else {
                return "Tiden är redan bokad";
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Ran out of code";
    }
}
