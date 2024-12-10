package com.example.library;
public class Main
{
    public static void main(String[] args)
    {
        Library library = new Library("L001", "City Library");

        // Adding books
        library.addBook(new Book("B001", "Java Programming", "Author A", "Education", 2020, "Available"));
        library.addBook(new Book("B002", "Python Basics", "Author B", "Education", 2019, "Borrowed"));
        library.addBook(new Book("B003", "Data Structures", "Author C", "Education", 2021, "Available"));

        // Displaying books
        System.out.println("\nAll Books in Library:");
        library.displayBooks();

        // Searching for a book
        System.out.println("\nSearching for book with keyword 'Python':");
        Book foundBook = library.searchBook("Python");
        System.out.println(foundBook != null ? foundBook : "Book not found.");

        // Removing a book
        System.out.println("\nRemoving book with ID 'B002':");
        library.removeBook("B002");

        // Displaying books after removal
        System.out.println("\nBooks after removal:");
        library.displayBooks();

        // Sorting books by title
        System.out.println("\nSorting books by title:");
        library.sortBooksByTitle();
        library.displayBooks();

        // Sorting books by publication year
        System.out.println("\nSorting books by publication year:");
        library.sortBooksByYear();
        library.displayBooks();

        // Displaying library information
        System.out.println("\nLibrary Information:");
        System.out.println(library);

    }
}
