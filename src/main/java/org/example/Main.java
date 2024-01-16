package org.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

record Reservation(String customerName, String phoneNumber, int numberOfGuests, String reservationDate, String reservationTime) {}

class Restaurant {
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    public void makeReservation(String customerName, String phoneNumber, int numberOfGuests, String reservationDate, String reservationTime) {
        // För att koppla till databas: Ersätt hårdkodade delar med nya parametrar
        InsertDB insertDB = new InsertDB();
        insertDB.InsertIntoTableBookings(customerName, phoneNumber, numberOfGuests, 5, reservationDate, reservationTime);
        // Tidigare kod
        Reservation reservation = new Reservation(customerName, phoneNumber, numberOfGuests, reservationDate, reservationTime);
        reservations.add(reservation);
        messageDialog("Bokning skapad för " + customerName + " på " + reservationDate);
    }

    public void removeReservation(String customerName) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).customerName().equals(customerName)) {
                reservations.remove(i);
                messageDialog("Bokningen för " + customerName + " har tagits bort.");
                return;
            }
        }
        messageDialog("Ingen bokning hittades för " + customerName);
    }

    public void changeReservation(String reservationDate, String newCustomerName, int newNumberOfGuests) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if (reservation.reservationDate().equals(reservationDate)) {
                Reservation updatedReservation = new Reservation(newCustomerName, reservation.phoneNumber(), newNumberOfGuests, reservationDate, reservation.reservationTime());
                reservations.set(i, updatedReservation);
                messageDialog("Bokningen ändrad för " + newCustomerName + " på " + reservationDate);
                return;
            }
        }
        messageDialog("Ingen bokning hittades för datumet " + reservationDate);
    }

    public void displayReservations() {
        if (reservations.isEmpty()) {
            messageDialog("Inga bokningar för närvarande.");
        } else {
            StringBuilder reservationInfo = new StringBuilder("Aktuella bokningar:\n");
            for (Reservation reservation : reservations) {
                reservationInfo.append("Kund: ").append(reservation.customerName())
                        .append(", Antal gäster: ").append(reservation.numberOfGuests())
                        .append(", Datum: ").append(reservation.reservationDate()).append("\n");
            }
            showPopupDialog(reservationInfo.toString(), "Aktuella bokningar");
        }
    }

    private void showPopupDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void messageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        ReadDB readDB = new ReadDB();

        while (true) {
            String[] options = {"Gör en bokning", "Visa aktuella bokningar", "Ändra en bokning", "Ta bort en bokning", "Avsluta"};
            int choice = JOptionPane.showOptionDialog(null, "Välkommen till bordsbokningssystemet!", "Meny",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    String customerName = inputDialog("Ange ditt namn:");
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
}
