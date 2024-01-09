package org.example;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class ReadDB {
    static String url = CreateDB.url;
    public static void main (String[] args){
        ReadDB read = new ReadDB();
        String output = read.selectAll().toString();
        System.out.println(output);
    }
    // Skapar en ny koppling till databasen och returnerar Connection objektet
    private Connection kontakt() {
        Connection kontakt = null;
        try {
            kontakt = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kontakt;
    }
    // Skickar ett SELECT query till databasen
    // Returnerar en Arraylist med värdena i test tabellen
    // Ändra till flera metoder så man kan kolla bokningar, namn osv
    public ArrayList<String> selectAll() {
        String query = "SELECT id, name, number FROM test_table";
        ArrayList<String> lst_response = new ArrayList<String>();

        try {
            Connection kontakt = this.kontakt();
            Statement cursor = kontakt.createStatement();
            ResultSet r = cursor.executeQuery(query);

            while (r.next()) {
                String id = String.valueOf(r.getInt("id"));
                String name = r.getString("name");
                String number = String.valueOf(r.getInt("number"));

                lst_response.add(id + "\t" + name + "\t" + number);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lst_response;
    }
}
