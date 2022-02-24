package com.company;

import java.sql.*;

public class Librarian implements Human{

    Connection c = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ps = null;


    @Override
    public void connect() {
        // This method makes a connection to the database
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
        // This method shows all books from the table
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
        // this method shows one book by its id number
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
        // This method shows one book by its name
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
    public void addBook(int id, String name, String author, String genre, String description) {
        // This method adds a book to the books table
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO books VALUES(" + id + ", '" + name +
                    "', '" + author + "', '" + genre + "', '" + description + "', true" + ")";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void deleteBook(int id) {
        // this method removes a book from the table by its id
        try {
            stmt = c.createStatement();
            String sql = "DELETE FROM books WHERE id = " + id;
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void deleteBook(String name) {
        // this method removes a book from the table by its name
        try {
            stmt = c.createStatement();
            String sql = "DELETE FROM books WHERE name = '" + name + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
