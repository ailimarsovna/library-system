package com.company;

import java.sql.*;

public class Student implements Human{

    Connection c = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ps = null;


    @Override
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "1724");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("DB opened successfully");
    }

    @Override
    public void showBooks() {
        try {
            String sql = "SELECT * FROM books";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("id\t|\tname\t|\tauthor\t|\tgenre\t|\tdescription\t|\tavailability");
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                String description = rs.getString("description");
                String availability = rs.getString("availability");
                System.out.println(id + "\t|\t" + name
                        + "\t|\t" + author + "\t|\t" + genre + "\t|\t" + description+ "\t|\t" + availability);
            }
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void showBookInfo(int id) {
        try {
            String sql = "SELECT * FROM books WHERE book_id = " + id;
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();


            System.out.println("id\t|\tname\t|\tauthor\t|\tgenre\t|\tdescription\t|\tavailability");
            while (rs.next()) {

                String name = rs.getString("name");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                String description = rs.getString("description");
                String availability = rs.getString("availability");
                System.out.println(id + "\t|\t" + name
                        + "\t|\t" + author + "\t|\t" + genre + "\t|\t" + description+ "\t|\t" + availability);
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void showBookInfo(String name) {
        try {
            String sql = "SELECT * FROM books WHERE name = '" + name + "'";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("id\t|\tname\t|\tauthor\t|\tgenre\t|\tdescription\t|\tavailability");
            while (rs.next()) {

                int id = rs.getInt("book_id");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                String description = rs.getString("description");
                String availability = rs.getString("availability");
                System.out.println(id + "\t|\t" + name
                        + "\t|\t" + author + "\t|\t" + genre + "\t|\t" + description+ "\t|\t" + availability);
            }
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void takeABook(int accounting_id, int stud_id, String stud_name, int book_id) {
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO books_accounting VALUES (" + accounting_id + ", " + stud_id + ", '" + stud_name + "', " + book_id + ");" +
                    "UPDATE books as b SET availability = false FROM books_accounting as ba " +
                    " WHERE ba.book_id = b.book_id AND accounting_id = " + accounting_id;
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void returnBook(int accounting_id) {
        try {
            stmt = c.createStatement();
            String sql = "UPDATE books as b SET availability = true FROM books_accounting as ba " +
                    " WHERE b.book_id = ba.book_id AND accounting_id = " + accounting_id + ";" +
                    "DELETE FROM books_accounting WHERE accounting_id = " + accounting_id;
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
