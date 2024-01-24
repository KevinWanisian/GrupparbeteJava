package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReadDBTest {

    @Test
    void testSelectAllFromTableTables() {
        ReadDB readDB = new ReadDB();
        // Försöker hämta alla värden från tabellen Tables
        // Kontrollera att resultatet inte är null eller tom
        assertNotNull(readDB.selectAllFromTableTables());
        assertFalse(readDB.selectAllFromTableTables().isEmpty());
    }

    @Test
    void testSelectAllBookingsSortedByDate() {
        ReadDB readDB = new ReadDB();
        // Försök hämta alla bokningar sorterade efter datum
        // Kontrollera att resultatet inte är null eller tom
        assertNotNull(readDB.selectAllBookingsSortedByDate());
        assertFalse(readDB.selectAllBookingsSortedByDate().isEmpty());
    }
}
