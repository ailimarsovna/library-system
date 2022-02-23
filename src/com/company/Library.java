package com.company;

import java.sql.*;

public class Library {
    Connection c = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "1724");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("DB opened successfully");
    }
    public void addBook(int id, String name, String author, String genre) {
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO books VALUES(" + id + ", '"+ name +
                    "', '" + author + "', '" + genre + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
    }
    public void deleteBook(int id) {
        try {
            stmt = c.createStatement();
            String sql = "DELETE FROM books WHERE id = " + id;
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
    }
    public void deleteBook(String name) {
        try {
            stmt = c.createStatement();
            String sql = "DELETE FROM books WHERE name = '" + name + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
    }

    public void showBookInfo(int id) {
        try {
            String sql = "SELECT * FROM books WHERE id = " + id;
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();


            System.out.println("id\t|\tname\t|\tauthor\t|\tgenre");
            while (rs.next()) {

                int id1 = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                System.out.println(id1 + "\t|\t" + name
                        + "\t|\t" + author + "\t|\t" + genre);
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
    }
    public void showBookInfo(String name) {
        try {
            String sql = "SELECT * FROM books WHERE id = " + name;
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);

            int id1 = rs.getInt("id");
//            String name = rs.getString("name");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            System.out.println(id1 + "\t\t" + name
                    + "\t\t" + author + "\t\t"
                    + genre);
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
    }
}