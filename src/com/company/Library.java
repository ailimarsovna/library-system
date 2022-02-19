package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Library {
    Connection c = null;
    Statement stmt = null;
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "6569");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("DB opened successfully");
    }
    public void addBook(int id, String name, String author, String genre, String description) {
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO books VALUES(" + id + ", '"+ name +
                    "', '" + author + "', '" + genre + "', '" + description + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
    }
}
