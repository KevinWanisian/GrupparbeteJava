package org.example;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;



class CheckDBTest {

    @Test
    public void testCheckBooking() {
        // Kontrollerar att metoden returnerar 5 om det finns 4 bokningar
        int result = CheckDB.CheckBooking("2024-01-27", "17:00-19:00");
        assertEquals(2, result);

        // Kontrollerar att metoden returnerar 1 om det finns 3 bokningar
        result = CheckDB.CheckBooking("2024-01-27", "19:00-21:00");
        assertEquals(1, result);


    }

    @Test
    public void testMain() {
        // Kontrollerar att det första exemplet i main() returnerar 2
        int result = CheckDB.CheckBooking("2024-01-27", "17:00-19:00");
        assertEquals(2, result);

    }
}