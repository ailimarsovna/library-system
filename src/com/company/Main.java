package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Librarian lb = new Librarian();
        Student st = new Student();
        st.connect();
        lb.connect();
        int s = 0;
        System.out.println("1. Login as a librarian\n2. Login as a student");
        s = sc.nextInt();
        switch (s) {
            case 1:
                int n = 0;
                while (n != 5) {
                    System.out.println("1. Add new book\n2. Delete book\n3. Show book info\n4. Show books table\n5. Quit");
                    n = sc.nextInt();
                    switch (n) {
                        case 1:
                            System.out.println("Insert id");
                            int id = sc.nextInt();
                            System.out.println("Insert name");
                            String name = sc.next();
                            System.out.println("Insert author");
                            String author = sc.next();
                            System.out.println("Insert genre");
                            String genre = sc.next();
                            System.out.println("Insert description (Not mandatory)");
                            // getInput method is used to send big text as a String to the DB
                            String description = getInput();
                            lb.addBook(id, name, author, genre, description);
                            break;
                        case 2:
                            System.out.println("Insert book's id or name");
                            // Here the input itself determines which method should be called.
                            // If a number was entered, the method with the id will be called,
                            // if a letter or word, the method with the book name will be called
                            String x = sc.next();
                            if (Character.isDigit(x.charAt(0))) {
                                lb.deleteBook(Integer.parseInt(x));
                            } else {
                                lb.deleteBook(x);
                            }
                            break;
                        case 3:
                            System.out.println("Insert book's id or name");
                            // Here the input itself determines which method should be called.
                            // If a number was entered, the method with the id will be called,
                            // if a letter or word, the method with the book name will be called
                            String y = sc.next();
                            if (Character.isDigit(y.charAt(0))) {
                                lb.showBookInfo(Integer.parseInt(y));
                            } else {
                                lb.showBookInfo(y);
                            }
                            break;
                        case 4:
                            lb.showBooks();
                    }
                    lb.connect();
                }
                break;
            case 2:
                System.out.println("Insert your stud_id");
                int stud_id = sc.nextInt();
                int m = 0;
                while (m != 6) {
                    System.out.println("1. Show book info\n2. Take a book\n3. Return the book\n4. Show books table\n5. Show my accountings\n6. Quit");
                    m = sc.nextInt();
                    switch (m) {
                        case 1:
                            System.out.println("Insert book's id or name");
                            // Here the input itself determines which method should be called.
                            // If a number was entered, the method with the id will be called,
                            // if a letter or word, the method with the book name will be called
                            String y = sc.next();
                            if (Character.isDigit(y.charAt(0))) {
                                st.showBookInfo(Integer.parseInt(y));
                            } else {
                                st.showBookInfo(y);
                            }
                            break;
                        case 2:
                            System.out.println("Insert accounting id, book's id");
                            st.takeABook(sc.nextInt(), stud_id, sc.nextInt());
                            break;
                        case 3:
                            System.out.println("Insert accounting_id");
                            st.returnBook(sc.nextInt());
                            break;
                        case 4:
                            st.showBooks();
                            break;
                        case 5:
                            st.showMyAccountings(stud_id);
                            break;
                    }
                    st.connect();
                }
                break;
        }
    }

    // getInput method is used to send big text as a String to the DB
    private static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
