package org.example;

import java.util.ArrayList;
import java.util.Optional;
import javax.swing.JOptionPane;

record Reservation(String customerName, int numberOfGuests, String reservationDate){}

class Restaurant {
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    public void makeReservation() {
        String customerName = inputDialog("Ange ditt namn:");
        int numberOfGuests = Integer.parseInt(inputDialog("Ange antal gäster:"));
        String reservationDate = inputDialog("Ange datum för bokningen (YYYY-MM-DD):");

        Reservation reservation = new Reservation(customerName, numberOfGuests, reservationDate);
        reservations.add(reservation);
        messageDialog("Bokning skapad för " + customerName + " på " + reservationDate);
    }

    public void removeReservation() {
        String customerName = inputDialog("Ange namnet på kunden vars bokning ska tas bort:");
        Optional<Reservation> foundReservation = reservations.stream()
                .filter(reservation -> reservation.customerName().equals(customerName))
                .findFirst();

        if (foundReservation.isPresent()) {
            reservations.remove(foundReservation.get());
            messageDialog("Bokningen för " + customerName + " har tagits bort.");
        } else {
            messageDialog("Ingen bokning hittades för " + customerName);
        }
    }

    public void changeReservation() {
        String reservationDate = inputDialog("Ange datum för bokningen som ska ändras (YYYY-MM-DD):");
        Optional<Reservation> foundReservation = reservations.stream()
                .filter(reservation -> reservation.reservationDate().equals(reservationDate))
                .findFirst();

        if (foundReservation.isPresent()) {
            String newCustomerName = inputDialog("Ange nytt namn för kunden:");
            int newNumberOfGuests = Integer.parseInt(inputDialog("Ange nytt antal gäster:"));

            Reservation updatedReservation = new Reservation(newCustomerName, newNumberOfGuests, reservationDate);
            reservations.set(reservations.indexOf(foundReservation.get()), updatedReservation);

            messageDialog("Bokningen ändrad för " + newCustomerName + " på " + reservationDate);
        } else {
            messageDialog("Ingen bokning hittades för datumet " + reservationDate);
        }
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
            messageDialog(reservationInfo.toString());
        }
    }

    private String inputDialog(String message) {
        return JOptionPane.showInputDialog(null, message);
    }

    private void messageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        while (true) {
            String[] options = {"Gör en bokning", "Visa aktuella bokningar", "Ändra en bokning", "Ta bort en bokning", "Avsluta"};
            int choice = JOptionPane.showOptionDialog(null, "Välkommen till bordsbokningssystemet!", "Meny",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    restaurant.makeReservation();
                    break;
                case 1:
                    restaurant.displayReservations();
                    break;
                case 2:
                    restaurant.changeReservation();
                    break;
                case 3:
                    restaurant.removeReservation();
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
}
