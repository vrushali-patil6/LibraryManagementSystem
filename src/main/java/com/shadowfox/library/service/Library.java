package com.shadowfox.library.service;

import com.shadowfox.library.exception.BookNotFoundException;
import com.shadowfox.library.model.Book;

import java.io.*;
import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
        loadBooksFromFile();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile();
        System.out.println("Book added successfully.");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("\n----- BOOK LIST -----");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public Book searchBook(String bookId) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        throw new BookNotFoundException("Book not found.");
    }

    public void removeBook(String bookId) {
        try {
            Book book = searchBook(bookId);
            books.remove(book);
            saveBooksToFile();
            System.out.println("Book removed successfully.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void issueBook(String bookId) {
        try {
            Book book = searchBook(bookId);

            if (book.isAvailable()) {
                book.setAvailable(false);
                saveBooksToFile();
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("Book is already issued.");
            }

        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook(String bookId) {
        try {
            Book book = searchBook(bookId);

            if (!book.isAvailable()) {
                book.setAvailable(true);
                saveBooksToFile();
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book is already available.");
            }

        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveBooksToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))) {

            for (Book book : books) {

                writer.write(book.getBookId() + "," +
                        book.getTitle() + "," +
                        book.getAuthor() + "," +
                        book.isAvailable());

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving books.");
        }
    }

    private void loadBooksFromFile() {

        File file = new File("books.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Book book = new Book(
                        data[0],
                        data[1],
                        data[2]
                );

                book.setAvailable(Boolean.parseBoolean(data[3]));

                books.add(book);
            }

        } catch (IOException e) {
            System.out.println("Error loading books.");
        }
    }
}