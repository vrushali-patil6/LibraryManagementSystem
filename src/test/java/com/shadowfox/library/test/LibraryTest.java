package com.shadowfox.library.test;

import com.shadowfox.library.exception.BookNotFoundException;
import com.shadowfox.library.model.Book;
import com.shadowfox.library.service.Library;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    void testAddBook() throws BookNotFoundException {

        Library library = new Library();

        Book book = new Book(
                "101",
                "Atomic Habits",
                "James Clear"
        );

        library.addBook(book);

        Book foundBook = library.searchBook("101");

        assertEquals(
                "Atomic Habits",
                foundBook.getTitle()
        );
    }

    @Test
    void testIssueBook() throws BookNotFoundException {

        Library library = new Library();

        Book book = new Book(
                "102",
                "Ikigai",
                "Hector Garcia"
        );

        library.addBook(book);

        library.issueBook("102");

        Book foundBook = library.searchBook("102");

        assertFalse(foundBook.isAvailable());
    }

    @Test
    void testReturnBook() throws BookNotFoundException {

        Library library = new Library();

        Book book = new Book(
                "103",
                "The Alchemist",
                "Paulo Coelho"
        );

        library.addBook(book);

        library.issueBook("103");
        library.returnBook("103");

        Book foundBook = library.searchBook("103");

        assertTrue(foundBook.isAvailable());
    }

    @Test
    void testBookNotFound() {

        Library library = new Library();

        assertThrows(
                BookNotFoundException.class,
                () -> library.searchBook("999")
        );
    }
}