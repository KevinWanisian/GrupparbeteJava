package org.example;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    @Test
    void makeReservation_shouldAddReservation() throws NoSuchFieldException, IllegalAccessException {
        // Skapa en instans av Restaurant
        Restaurant restaurant = new Restaurant();

        // Ange värden för en reservation
        String customerName = "Hej Jag är ett test";
        String phoneNumber = "123456789";
        int numberOfGuests = 4;
        int tableID = 1;
        String reservationDate = "2024-01-22";
        String reservationTime = "18:00";

        // Gör en reservation
        restaurant.makeReservation(customerName, phoneNumber, numberOfGuests, tableID, reservationDate, reservationTime);

        // Använd reflektion för att komma åt det privata reservations-fältet
        Field field = Restaurant.class.getDeclaredField("reservations");
        field.setAccessible(true);
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) field.get(restaurant);

        // Verifiera att bokningen lades till
        assertEquals(1, reservations.size());
    }
}

