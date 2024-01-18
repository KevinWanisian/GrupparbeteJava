package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Skapar en koppling mellan databasen och returnerar den
// Kan kallas från flera olika klasser
public class GetConnection {
    public static Connection kontakt(String url){
        Connection kontakt = null;
        try {
            kontakt = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kontakt;
        }
    }

