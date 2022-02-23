package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Library library = new Library();
        library.connect();
        int n = 0;
        while (n != 4) {
            System.out.println("1. Add new book\n2. Delete book\n3. Show book info\n4. Quit");
            n = sc.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Insert id, name, author and genre with the Enter");
                    library.addBook(sc.nextInt(), sc.next(), sc.next(), sc.next());
                    break;
                case 2:
                    System.out.println("Insert book's id or name");
                    String x = sc.next();
                    if (Character.isDigit(x.charAt(0))) {
                        library.deleteBook(Integer.parseInt(x));
                    } else {
                        library.deleteBook(x);
                    }
                    break;
                case 3:
                    System.out.println("Insert book's id or name");
                    String y = sc.next();
                    if (Character.isDigit(y.charAt(0))) {
                        library.showBookInfo(Integer.parseInt(y));
                    } else {
                        library.showBookInfo(y);
                    }
            }
            library.connect();
        }
    }
} //testt
