package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDBTest {

    // Anger en temporär databasfil för teständamål
    static String testFilename = "test_bookingsystem.db";
    static String testUrl = "jdbc:sqlite:src/test/java/org/example/" + testFilename;

    @BeforeAll
    static void setUp() {
        // Skapa en temporär databas för testning
        CreateDB.url = testUrl;
        CreateDB.CreateNewDB();
        CreateDB.CreateTableTables();
        CreateDB.CreateTableBookings();
    }

    @AfterAll
    static void tearDown() {
        // Ta bort den temporära databasen efter testning
        try (Connection connection = DriverManager.getConnection(testUrl);
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS Tables;");
            statement.execute("DROP TABLE IF EXISTS Bookings;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreateNewDB() {
        // Kolla om att databasen skapas korrekt
        try (Connection connection = DriverManager.getConnection(testUrl)) {
            assertNotNull(connection);
        } catch (SQLException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testCreateTableTables() {
        // Kolla om att Tables-tabellen skapas korrekt
        try (Connection connection = DriverManager.getConnection(testUrl);
             Statement statement = connection.createStatement()) {
            assertTrue(statement.execute("SELECT * FROM Tables;"));
        } catch (SQLException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testCreateTableBookings() {
        // Kolla om att Bookings-tabellen skapas korrekt
        try (Connection connection = DriverManager.getConnection(testUrl);
             Statement statement = connection.createStatement()) {
            assertTrue(statement.execute("SELECT * FROM Bookings;"));
        } catch (SQLException e) {
            fail("Exception should not be thrown");
        }
    }
}
