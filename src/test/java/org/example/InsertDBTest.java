package org.example;

import org.example.InsertDB;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class InsertDBTest {

    // Anger en temporär databasfil för teständamål
    static String testFilename = "test_bookingsystem.db";
    static String testUrl = "jdbc:sqlite:src/test/java/org/example/" + testFilename;

    @Test
    void testInsertIntoTableBookings() {
        // Använd assertThrows för att fånga exception och kontrollera dess typ
        SQLException exception = assertThrows(SQLException.class, () -> {
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
                // Om det uppstår ett SQLException, låt assertThrows fånga det
                throw e;
            }
        });

        // Skriv ut stacktrace för att få mer information om exceptionet
        exception.printStackTrace();
    }
}
