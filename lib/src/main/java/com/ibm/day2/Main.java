package com.ibm.day2;

import java.util.ArrayList;

class Book {
    // Private fields
    private String title;
    private String author;
    private boolean available;

    // Constructor (default available = true)
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true; 
    }

    // Getter for title (needed so Library can search by title)
    public String getTitle() {
        return title;
    }

    // Method to borrow a book
    public void borrowBook() {
        if (this.available) {
            this.available = false;
            System.out.println("Successfully borrowed: \"" + title + "\"");
        } else {
            System.out.println("Error: \"" + title + "\" is already borrowed.");
        }
    }

    // Method to return a book
    public void returnBook() {
        this.available = true;
        System.out.println("Successfully returned: \"" + title + "\"");
    }

    // Method to print book details
    public void getInfo() {
        String status = available ? "Available" : "Borrowed";
        System.out.println("- " + title + " by " + author + " [" + status + "]");
    }
}

class Library {
    // Field to store books
    private ArrayList<Book> books;

    // Constructor
    public Library() {
        books = new ArrayList<>();
    }

    // Method to add a book
    public void addBook(Book b) {
        books.add(b);
    }

    // Method to show all books
    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("The library has no books.");
            return;
        }
        for (Book b : books) {
            b.getInfo();
        }
    }

    // Method to search and borrow a book by title
    public void borrowBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.borrowBook();
                return; // Stop searching once found
            }
        }
        System.out.println("Error: \"" + title + "\" was not found in the library.");
    }

    // Method to search and return a book by title
    public void returnBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.returnBook();
                return; // Stop searching once found
            }
        }
        System.out.println("Error: \"" + title + "\" does not belong to this library.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a Library object
        Library myLibrary = new Library();

        // Add at least 3 books
        myLibrary.addBook(new Book("The Book 1", "Philip Lee"));
        myLibrary.addBook(new Book("The Book 2", "EJ Lee"));
        myLibrary.addBook(new Book("The Book 3", "Janelle Lee"));

        // Show all books before operations
        System.out.println("--- Library Books (Before Operations) ---");
        myLibrary.showAllBooks();
        System.out.println();

        // Borrow one book
        System.out.println("--- Performing Operations ---");
        myLibrary.borrowBook("1984");
        
        // (Optional Test) Trying to borrow the same book again to test your logic
        myLibrary.borrowBook("1984"); 
        
        // Return one book
        myLibrary.returnBook("1984");
        System.out.println();

        // Show all books after operations
        System.out.println("--- Library Books (After Operations) ---");
        myLibrary.showAllBooks();
    }
}