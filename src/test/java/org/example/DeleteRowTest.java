package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DeleteRowTest {

    // Testar om raden tas bort och returnerar rätt meddelande
    @Test
    public void testDeleteRowFromTable() {
        int idToRemove = 46; // Här tar den faktiskt bort raden från databasen, så 46 behövs ändras efter varje test

        String result = DeleteRow.deleteRowFromTable(idToRemove);

        // förväntat resultat
        assertEquals("Bokning borttagen", result);
    }

    // Testar om felaktigt ID returnerar rätt felmeddelande
    @Test
    public void testDeleteRowWithUnknownId() {
        // Förbered testdata
        int unknownId = 100; // Valde 1000 eftersom vi troligtvis inte kommer komma upp i den siffran

        String result = DeleteRow.deleteRowFromTable(unknownId);

        // förväntat resultat
        assertEquals("Okänt Id", result);
    }
}
