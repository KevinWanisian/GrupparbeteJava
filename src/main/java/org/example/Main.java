package org.example;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

record Reservation(String customerName, String phoneNumber, int numberOfGuests, String reservationDate, String reservationTime) {}

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        while (true) {
            String[] options = {"Gör en bokning", "Visa aktuella bokningar", "Ändra en bokning", "Ta bort en bokning", "Avsluta"};
            int choice = JOptionPane.showOptionDialog(null, "Välkommen till bordsbokningssystemet!", "Meny",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            String customerName = null;
            switch (choice) {
                case 0:
                    // Felhantering för namn
                    while (true) {
                        customerName = inputDialog("Ange ditt namn:");
                        if (customerName.matches("[a-zA-ZåäöÅÄÖ ]+")) {
                            break;
                        } else {
                            restaurant.showPopupDialog("Endast bokstäver tillåtna i namnet.", "Fel inmatning.");
                        }
                    }

                    String phoneNumber = inputDialog("Ange ditt telefonnummer (endast siffror):");
                    int numberOfGuests = Integer.parseInt(inputDialog("Ange antal gäster:"));
                    String reservationDate = inputDialog("Ange datum för bokningen (YYYY-MM-DD):");
                    String reservationTime = inputDialog("Ange tid för bokningen (HH:MM-HH:MM):");
                    restaurant.makeReservation(customerName, phoneNumber, numberOfGuests, reservationDate, reservationTime);
                    break;
                case 1:
                    restaurant.displayReservations();
                    break;
                case 2:
                    String changeReservationDate = inputDialog("Ange datum för bokningen som ska ändras (YYYY-MM-DD):");
                    String newCustomerName = inputDialog("Ange nytt namn för kunden:");
                    int newNumberOfGuests = Integer.parseInt(inputDialog("Ange nytt antal gäster:"));
                    restaurant.changeReservation(changeReservationDate, newCustomerName, newNumberOfGuests);
                    break;
                case 3:
                    String removeCustomerName = inputDialog("Ange namnet på kunden vars bokning ska tas bort:");
                    restaurant.removeReservation(removeCustomerName);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Tack för att du använde bordsbokningssystemet. Hej då!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ogiltigt val. Vänligen försök igen.");
            }
        }
    }

    private static String inputDialog(String message) {
        return JOptionPane.showInputDialog(null, message);
    }

    public void main() {
    }
}