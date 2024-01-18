package org.example;

import javax.swing.JOptionPane;

record Reservation(String customerName, String phoneNumber, int numberOfGuests, int tableID, String reservationDate, String reservationTime) {}

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
                    try {
                        String phoneNumber = inputDialog("Ange ditt telefonnummer (endast siffror):");
                        int numberOfGuests = Integer.parseInt(inputDialog("Ange antal gäster:"));
                        String reservationDate = inputDialog("Ange datum för bokningen (YYYY-MM-DD):");
                        String reservationTime = inputDialog("Ange tid för bokningen (HH:MM-HH:MM):");

                        // Kollar om det finns ett tillgängligt bord det datumet och den tiden
                        if (CheckDB.CheckBooking(reservationDate, reservationTime) == null) {
                            JOptionPane.showMessageDialog(null, "Inga bord lediga.");
                            break;}
                        else {
                            int tableID = CheckDB.CheckBooking(reservationDate, reservationTime);
                            restaurant.makeReservation(customerName, phoneNumber, numberOfGuests, tableID, reservationDate, reservationTime);
                            break;
                        }
                    } catch (Exception e) {
                        restaurant.showPopupDialog("Felaktig inmatning\nBokningen avbryts", "Fel inmatning.");
                        break;
                    }

                case 1:
                    restaurant.displayReservations();
                    break;
                case 2:
                    try {
                        int id = Integer.parseInt(inputDialog("Ange id för bokning som ska ändras:"));
                        String newCustomerName = inputDialog("Ange nytt namn för kunden:");
                        String newPhone = inputDialog("Ange nytt telefonnummer:");
                        int newNumberOfGuests = Integer.parseInt(inputDialog("Ange nytt antal gäster:"));
                        String output = restaurant.changeReservation(id, newCustomerName, newPhone, newNumberOfGuests);
                        restaurant.showPopupDialog(output, "Ändra bokning");
                    } catch (Exception e) {
                        restaurant.showPopupDialog("Felaktig inmatning\nUppdateringen avbryts", "Fel inmatning.");
                        break;
                    }
                    break;
                case 3:
                    try{
                        int bookingID = Integer.parseInt(inputDialog("Ange id nummer på bokning som ska tas bort:"));
                        String output = restaurant.removeReservation(bookingID);
                        restaurant.showPopupDialog(output, "Ta bort bokning");
                    } catch (Exception e) {
                        restaurant.showPopupDialog("Felaktig inmatning", "Fel inmatning.");
                        break;
                    }
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
}