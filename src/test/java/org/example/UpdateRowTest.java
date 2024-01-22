package org.example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateRowTest {
    static String testName = "testName";
    static  String testPhone = "123 123 12 23";
    static int testNumber = 4;

    @Test
    public void testUpdateRow() {
        int id = 49;
        String result = UpdateRow.updateRowFromTable(id, testName, testPhone,testNumber);
        assertEquals("Ändring utförd", result);
        assertTrue(ReadDB.selectAllBookingsSortedByDate().contains(testName));
    }
    @Test
    public void testWrongID() {
        int id = 9999;

        String result = UpdateRow.updateRowFromTable(id, testName, testPhone, testNumber);
        assertEquals("Okänt Id", result);
    }
}
