package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class InsertDBTest {

    // skapar en temporär databasfil för test
    static String testFilename = "test_bookingsystem.db";
    static String testUrl = "jdbc:sqlite:src/test/java/org/example/" + testFilename;

    @BeforeAll //Before all krävs fär att skapa tabeller innan testet körs
    static void setup() {
        // Skapa tabellerna innan testerna körs
        try (Connection connection = DriverManager.getConnection(testUrl);
             Statement statement = connection.createStatement()) {
            // Skapa Tables-tabellen
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Tables (TableID INTEGER PRIMARY KEY AUTOINCREMENT, Seats INTEGER);");

            // Skapa Bookings-tabellen
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Bookings (BookingID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Name TEXT, Phone TEXT, Guests INTEGER, TableID INTEGER, Day TEXT, Time TEXT, " +
                    "FOREIGN KEY (TableID) REFERENCES Tables (TableID));");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testInsertIntoTableTables() {
        // Kollar om att Tables-tabellen uppdateras korrekt
        try (Connection connection = DriverManager.getConnection(testUrl);
             Statement statement = connection.createStatement()) {
            InsertDB insertDB = new InsertDB();
            insertDB.url = testUrl;
            insertDB.InsertIntoTableTables(4);

            // Hämta antalet rader från Tables-tabellen
            var resultSet = statement.executeQuery("SELECT COUNT(*) FROM Tables;");
            assertTrue(resultSet.next());
            assertEquals(1, resultSet.getInt(1)); // Det bör finnas 1 rad i tabellen
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testInsertIntoTableBookings() {
        // Kolla om att Bookings-tabellen uppdateras korrekt
        try (Connection connection = DriverManager.getConnection(testUrl);
             Statement statement = connection.createStatement()) {
            InsertDB insertDB = new InsertDB();
            insertDB.url = testUrl;
            insertDB.InsertIntoTableBookings("Test Name", "Test Phone", 2, 1, "2024-01-27", "17:00-19:00");

            // Hämta antalet rader från Bookings-tabellen
            var resultSet = statement.executeQuery("SELECT COUNT(*) FROM Bookings;");
            assertTrue(resultSet.next());
            assertEquals(1, resultSet.getInt(1)); // Det bör finnas 1 rad i tabellen
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception should not be thrown");
        }
    }
}
