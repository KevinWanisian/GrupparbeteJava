package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        while (true) {
            String[] options = {"Gör en bokning", "Visa aktuella bokningar", "Ändra en bokning", "Ta bort en bokning", "Avsluta"};
            int choice = JOptionPane.showOptionDialog(null, "Välkommen till bordsbokningssystemet!", "Meny",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            DisplayLogic displayLogic = new DisplayLogic(restaurant);

            switch (choice) {
                case 0:
                    displayLogic.makeReservation();
                    break;
                case 1:
                    displayLogic.displayReservations();
                    break;
                case 2:
                    displayLogic.changeReservation();
                    break;
                case 3:
                    displayLogic.removeReservation();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Tack för att du använde bordsbokningssystemet. Hej då!");
                    System.exit(0);
                    break;
                default:
                    displayLogic.showInvalidChoiceMessage();
            }
        }
    }
}