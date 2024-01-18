package org.example;

import javax.swing.*;
import java.util.ArrayList;

public class Restaurant {
        private final ArrayList<Reservation> reservations = new ArrayList<>();

        public void makeReservation(String customerName, String phoneNumber, int numberOfGuests, int tableID, String reservationDate, String reservationTime) {
            // För att koppla till databas: Ersätt hårdkodade delar med nya parametrar
            InsertDB insertDB = new InsertDB();
            insertDB.InsertIntoTableBookings(customerName, phoneNumber, numberOfGuests, tableID, reservationDate, reservationTime);
            // Tidigare kod
            Reservation reservation = new Reservation(customerName, phoneNumber, numberOfGuests, tableID, reservationDate, reservationTime);
            reservations.add(reservation);
            messageDialog("Bokning skapad för " + customerName + " på " + reservationDate);
        }

        public String removeReservation(int id) {
            return DeleteRow.deleteRowFromTable(id);
        }

        public String changeReservation(int id, String newCustomerName, String phone, int newNumberOfGuests) {
            return UpdateRow.updateRowFromTable(id, newCustomerName, phone, newNumberOfGuests);
        }

        public void displayReservations() {
            StringBuilder reservationInfo = new StringBuilder("Aktuella bokningar:\n");

            showPopupDialog(ReadDB.selectAllBookingsSortedByDate(), "Aktuella bokningar");
            }


        public void showPopupDialog(String message, String title) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        }

        private void messageDialog(String message) {
            JOptionPane.showMessageDialog(null, message);
        }
    }

