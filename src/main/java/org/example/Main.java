package org.example;

import java.util.ArrayList;
import java.util.Scanner;

record Reservation(String customerName, int numberOfGuests, String reservationDate) {}

class Restaurant {
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    public void makeReservation(String customerName, int numberOfGuests, String reservationDate) {
        Scanner scanner = new Scanner(System.in);

        // Lägg till begäran om e-post
        System.out.print("Ange din e-post: ");
        String email = scanner.nextLine();

        // Lägg till begäran om telefonnummer och felhantering för endast siffror
        String phoneNumber;
        do {
            System.out.print("Ange ditt telefonnummer (endast siffror): ");
            phoneNumber = scanner.nextLine();
            if (!phoneNumber.matches("\\d+")) {
                System.out.println("Ogiltigt telefonnummer. Ange endast siffror.");
            }
        } while (!phoneNumber.matches("\\d+"));

        Reservation reservation = new Reservation(customerName, numberOfGuests, reservationDate);
        reservations.add(reservation);
        System.out.println("Bokning skapad för " + customerName + " på " + reservationDate +
                " med e-post: " + email + " och telefonnummer: " + phoneNumber);
    }
    public void removeReservation(String customerName) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).customerName().equals(customerName)) {
                reservations.remove(i);
                System.out.println("Bokningen för " + customerName + " har tagits bort.");
                return;
            }
        }
        System.out.println("Ingen bokning hittades för " + customerName);
    }
    public void changeReservation(String reservationDate, String newCustomerName, int newNumberOfGuests) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if (reservation.reservationDate().equals(reservationDate)) {
                Reservation updatedReservation2 = new Reservation(newCustomerName, newNumberOfGuests, reservationDate);
                reservations.set(i, updatedReservation2);
                System.out.println("Bokningen ändrad för " + newCustomerName + " på " + reservationDate);
                return;
            }
        }
        System.out.println("Ingen bokning hittades för datumet " + reservationDate);
    }

    public void displayReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Inga bokningar för närvarande.");
        } else {
            System.out.println("Aktuella bokningar:");
            for (Reservation reservation : reservations) {
                System.out.println("Kund: " + reservation.customerName() +
                        ", Antal gäster: " + reservation.numberOfGuests() +
                        ", Datum: " + reservation.reservationDate());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();

        while (true) {
            System.out.println("Välkommen till bordsbokningssystemet!");
            System.out.println("1. Gör en bokning");
            System.out.println("2. Visa aktuella bokningar");
            System.out.println("3. Ändra en bokning");
            System.out.println("4. Ta bort en bokning");
            System.out.println("5. Avsluta");

            // Felhantering för ogiltigt menyval
            while (!scanner.hasNextInt()) {
                System.out.println("Ogiltigt val. Vänligen försök igen.");
                scanner.next(); // Rensa inmantningen
            }
            int choice = scanner.nextInt();

            // Felhantering för namn
            switch (choice) {
                case 1:
                    System.out.print("Ange ditt namn: ");
                    while (!scanner.hasNext("[a-zA-Z]+")) {
                        System.out.println("Endast bokstäver tillåtna i namnet. Försök igen.");
                        System.out.print("Ange ditt namn: ");
                        scanner.next();
                    }
                    String customerName = scanner.next();

                    // Felhantering för antal gäster
                    System.out.print("Ange antal gäster: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Endast siffror. Försök igen.");
                        System.out.print("Ange antal gäster: ");
                        scanner.next();
                    }
                    int numberOfGuests = scanner.nextInt();

                    // Felhantering för datum
                    System.out.print("Ange datum för bokningen (YYYY-MM-DD): ");
                    while (!scanner.hasNext("\\d{4}-\\d{2}-\\d{2}")) {
                        System.out.println("Ogiltigt datum inskrivet. Försök igen.");
                        System.out.print("Ange datum för bokningen (YYYY-MM-DD): ");
                        scanner.next();
                    }
                    String reservationDate = scanner.next();

                    restaurant.makeReservation(customerName, numberOfGuests, reservationDate);
                    break;
                case 2:
                    restaurant.displayReservations();
                    break;
                case 3:
                    System.out.print("Ange datum för bokningen som ska ändras (YYYY-MM-DD): ");
                    String changeReservationDate = scanner.next();
                    System.out.print("Ange nytt namn för kunden: ");
                    String newCustomerName = scanner.next();
                    System.out.print("Ange nytt antal gäster: ");
                    int newNumberOfGuests = scanner.nextInt();
                    restaurant.changeReservation(changeReservationDate, newCustomerName, newNumberOfGuests);
                    break;
                case 4:
                    System.out.print("Ange namnet på kunden vars bokning ska tas bort: ");
                    String removeCustomerName = scanner.next();
                    restaurant.removeReservation(removeCustomerName);
                    break;
                case 5:
                    System.out.println("Tack för att du använde bordsbokningssystemet. Hej då!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ogiltigt val. Vänligen försök igen.");
            }
        }
    }
}
