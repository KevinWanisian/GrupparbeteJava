package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearDBTest {
    private static final String url = "jdbc:sqlite:src/main/java/org/example/bookingsystem.db";
    private Connection connection;
    private ClearDB clearDB;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = GetConnection.kontakt(url);
        connection.setAutoCommit(false);
        clearDB = new ClearDB(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Rulla tillbaka transaktionen efter varje test
        connection.rollback();
        connection.close();
    }

    @Test
    public void testDeleteTableTables() {
        assertTrue(clearDB.DeleteTableTables());
    }

    @Test
    public void testDeleteTableBookings() {
        assertTrue(clearDB.DeleteTableBookings());
    }

    @Test
    public void testClearTableTables() {
        assertTrue(clearDB.ClearTableTables());
    }

    @Test
    public void testClearTableBookings() {
        assertTrue(clearDB.ClearTableBookings());
    }
}
