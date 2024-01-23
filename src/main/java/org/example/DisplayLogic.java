package org.example;

import javax.swing.*;

public class DisplayLogic {

    // Ny instans av Restaurant
    private static Restaurant restaurant;

    // Initierar DisplayLogic med parametern restaurant
    public DisplayLogic(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // Gör ny bokning
    public boolean makeReservation() {
        String customerName;
        while (true) {
            customerName = inputDialog("Ange ditt namn:");
            if (customerName.matches("[a-zA-ZåäöÅÄÖ ]+")) {
                break;
            } else {
                restaurant.showPopupDialog("Endast bokstäver tillåtna i namnet.", "Fel inmatning.");
            }
        }
        try {
            String phoneNumber = inputDialog("Ange ditt telefonnummer (endast siffror):");
            int numberOfGuests = Integer.parseInt(inputDialog("Ange antal gäster:"));
            String reservationDate = inputDialog("Ange datum för bokningen (YYYY-MM-DD):");
            String reservationTime = inputDialog("Ange tid för bokningen (HH:MM-HH:MM):");

            if (CheckDB.CheckBooking(reservationDate, reservationTime) == null) {
                JOptionPane.showMessageDialog(null, "Inga bord lediga.");
            } else {
                int tableID = CheckDB.CheckBooking(reservationDate, reservationTime);
                restaurant.makeReservation(customerName, phoneNumber, numberOfGuests, tableID, reservationDate, reservationTime);
                return true;
            }
        } catch (Exception e) {
            restaurant.showPopupDialog("Felaktig inmatning\nBokningen avbryts", "Fel inmatning.");
            return false;
        }
        return false;
    }


    // Visar befintliga bokningar
    public boolean displayReservations() {
        restaurant.displayReservations();
        return true;
    }

    // Ändra bokning
    public boolean changeReservation() {
        try {
            int id = Integer.parseInt(inputDialog("Ange id för bokning som ska ändras:"));
            String newCustomerName = inputDialog("Ange nytt namn för kunden:");
            String newPhone = inputDialog("Ange nytt telefonnummer:");
            int newNumberOfGuests = Integer.parseInt(inputDialog("Ange nytt antal gäster:"));
            String output = restaurant.changeReservation(id, newCustomerName, newPhone, newNumberOfGuests);
            restaurant.showPopupDialog(output, "Ändra bokning");
            return true;
        } catch (Exception e) {
            restaurant.showPopupDialog("Felaktig inmatning\nUppdateringen avbryts", "Fel inmatning.");
            return false;
        }
    }


    // Ta bort bokning
    public boolean removeReservation() {
        try {
            int bookingID = Integer.parseInt(inputDialog("Ange id nummer på bokning som ska tas bort:"));
            String output = restaurant.removeReservation(bookingID);
            restaurant.showPopupDialog(output, "Ta bort bokning");
            return true;
        } catch (Exception e) {
            restaurant.showPopupDialog("Felaktig inmatning", "Fel inmatning.");
            return false;
        }
    }


    // Pop-up-fönster med inmatning
    public static String inputDialog(String message) {
            return JOptionPane.showInputDialog(null, message);
        }


// Felmeddelande
public void showInvalidChoiceMessage() {
    JOptionPane.showMessageDialog(null, "Ogiltigt val. Vänligen försök igen.");
}
}