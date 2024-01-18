package org.example;
public class DeleteRowTest {

    public static void main(String[] args) {
        testDeleteRowFromTable();
    }

    public static void testDeleteRowFromTable() {
        // Skapade en ny bokning som fick BookID 53 (så jag är säker på att 53 finns som ID)
        int existingBookingId = 53;

        // Testa att bokningen tas bort och returnerar "Bokning borttagen"
        String result = DeleteRow.deleteRowFromTable(existingBookingId);
        assert result.equals("Bokning borttagen") : "Test failed: Expected 'Bokning borttagen', got " + result;

        // Anta att BookingID 100 inte finns i databasen
        int nonExistingBookingId = 100;

        // Testa att returnera "Okänt Id" om bokningen inte finns
        result = DeleteRow.deleteRowFromTable(nonExistingBookingId);
        assert result.equals("Okänt Id") : "Test failed: Expected 'Okänt Id', got " + result;

        // Om inga Errors dyker upp, så är testet lyckat
        System.out.println("Alla tester genomförda utan fel.");
    }
}

