package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class DisplayLogicTest {

    private static final String url = "jdbc:sqlite:src/main/java/org/example/bookingsystem.db";
    private Connection connection;
    private DisplayLogic displayLogic;

    @BeforeEach
    public void setUp() throws SQLException {
        Restaurant restaurant = new Restaurant();
        connection = GetConnection.kontakt(url);
        connection.setAutoCommit(false);
        displayLogic = new DisplayLogic(restaurant);
    }

    @AfterEach
    public void tearDown() {
        try {
            // Rulla tillbaka transaktionen efter varje test
            connection.rollback();
            connection.close();
            System.out.println("Connection closed successfully.");
        } catch (SQLException e) {
            System.err.println("Error during rollback or closing connection: " + e.getMessage());
        }
    }


    @Test
    @Order(1)
    public void testMakeReservation() {
        assertTrue(displayLogic.makeReservation());
    }

    @Test
    @Order(2)
    public void testDisplayReservations() {
        assertTrue(displayLogic.displayReservations());
    }

    @Test
    @Order(3)
    public void testChangeReservation() {
        assertTrue(displayLogic.changeReservation());
    }

    @Test
    @Order(4)
    public void testDisplayReservationsAgain() {
        assertTrue(displayLogic.displayReservations());
    }

    @Test
    @Order(5)
    public void testRemoveReservation() {
        assertTrue(displayLogic.removeReservation());
    }

    @Test
    @Order(6)
    public void testDisplayReservationsAgainAgain() {
        assertTrue(displayLogic.displayReservations());
    }
}