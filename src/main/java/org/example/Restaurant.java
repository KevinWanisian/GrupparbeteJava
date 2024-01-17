package org.example;

import javax.swing.*;
import java.util.ArrayList;

public class Restaurant {
        private final ArrayList<Reservation> reservations = new ArrayList<>();
        ReadDB readDB = new ReadDB();
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
            StringBuilder reservationInfo = new StringBuilder("Aktuella bokningar:\n");
            //for (String i : readDB.selectAllBookingsSortedByDate()) {
            //    reservationInfo.append(i).append("\n");
            //}
                showPopupDialog(readDB.selectAllBookingsSortedByDate(), "Aktuella bokningar");
            }


        public void showPopupDialog(String message, String title) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        }

        private void messageDialog(String message) {
            JOptionPane.showMessageDialog(null, message);
        }
    }

