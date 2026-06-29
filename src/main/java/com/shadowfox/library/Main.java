package com.shadowfox.library;

import com.shadowfox.library.exception.BookNotFoundException;
import com.shadowfox.library.model.Book;
import com.shadowfox.library.service.Library;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        int choice;

        do {

            System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Book ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    library.addBook(new Book(id, title, author));
                    break;

                case 2:

                    library.displayBooks();
                    break;

                case 3:

                    System.out.print("Enter Book ID: ");
                    String searchId = sc.nextLine();

                    try {
                        Book book = library.searchBook(searchId);

                        System.out.println("\nBook Found!");
                        System.out.println(book);

                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:

                    System.out.print("Enter Book ID: ");
                    library.removeBook(sc.nextLine());
                    break;

                case 5:

                    System.out.print("Enter Book ID: ");
                    library.issueBook(sc.nextLine());
                    break;

                case 6:

                    System.out.print("Enter Book ID: ");
                    library.returnBook(sc.nextLine());
                    break;

                case 7:

                    System.out.println("Thank you for using Library Management System!");
                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while (choice != 7);

        sc.close();
    }
}