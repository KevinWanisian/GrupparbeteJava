import java.util.ArrayList;
import java.util.Scanner;

record Reservation(String customerName, int numberOfGuests, String reservationDate) {
}

class Restaurant {
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    public void makeReservation(String customerName, int numberOfGuests, String reservationDate) {
        Reservation reservation = new Reservation(customerName, numberOfGuests, reservationDate);
        reservations.add(reservation);
        System.out.println("Bokning skapad för " + customerName + " på " + reservationDate);
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
            System.out.println("3. Avsluta");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Ange ditt namn: ");
                    String customerName = scanner.next();

                    // Felhantering för antal gäster
                    System.out.print("Ange antal gäster: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Ange antal i siffror, försök igen.");
                        System.out.print("Ange antal gäster: ");
                        scanner.next(); // Rensa inmatningen
                    }
                    int numberOfGuests = scanner.nextInt();

                    System.out.print("Ange datum för bokningen (YYYY-MM-DD): ");
                    String reservationDate = scanner.next();

                    restaurant.makeReservation(customerName, numberOfGuests, reservationDate);
                    break;
                case 2:
                    restaurant.displayReservations();
                    break;
                case 3:
                    System.out.println("Tack för att du använde bordsbokningssystemet. Hej då!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ogiltigt val. Vänligen försök igen.");
            }
        }
    }
}
