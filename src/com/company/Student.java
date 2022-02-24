package com.company;

import java.sql.*;

public class Student implements Human{

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
    public void takeABook(int accounting_id, int stud_id, int book_id) {
        // This method adds a new entry to the accountings table, which means that the student with logged in id
        // has picked up the book. Also, if an entry is added to this table, the availability of this book in the
        // books table will be changed to false

        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO books_accounting VALUES (" + accounting_id + ", " + stud_id + ", " + book_id + ");" +
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
        // This method assumes that the student has returned the book to the library.
        // The entry in the accountings table is deleted, and the availability in the
        // books table is changed to true
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

    public void showMyAccountings(int stud_id) {
        // This method shows all the records of a particular student
        try {
            String sql = "SELECT * FROM books_accounting WHERE student_id = " + stud_id ;
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("acc_id\t|\tstud_id\t|\tbook_id");
            while (rs.next()) {

                int accounting_id = rs.getInt("accounting_id");
                int st_id = rs.getInt("student_id");
                int book_id = rs.getInt("book_id");
                System.out.println(accounting_id + "\t|\t" + st_id
                        + "\t|\t" + book_id);
            }
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
