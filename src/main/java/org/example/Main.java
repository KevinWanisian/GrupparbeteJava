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
                    System.out.println("Tack för att du använde bordsbokningssystemet. Hej då!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ogiltigt val. Vänligen försök igen.");
            }
        }
    }
}
