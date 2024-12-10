package com.example.library;
import java.util.*;
public interface Quanlythuvien {
    void addBook(Book book);
    void removeBook(String bookId);
    Book searchBook(String keyword);
    void displayBooks();
}

class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private String status;

    public Book(String id, String title, String author, String genre, int publicationYear, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Genre: " + genre +
                ", Year: " + publicationYear + ", Status: " + status;
    }
}

class Library implements Quanlythuvien {
    private String id;
    private String name;
    private List<Book> books;

    public Library(String id, String name) {
        this.id = id;
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    @Override
    public void removeBook(String bookId) {
        books.removeIf(book -> book.getId().equals(bookId));
        System.out.println("Book with ID " + bookId + " has been removed.");
    }

    @Override
    public Book searchBook(String keyword) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    @Override
    public String toString() {
        return "Library ID: " + id + ", Name: " + name + ", Total Books: " + books.size();
    }

    public void sortBooksByYear() {
        books.sort(Comparator.comparingInt(Book::getPublicationYear));
        System.out.println("Books sorted by publication year.");
    }

    public void sortBooksByTitle() {
        books.sort(Comparator.comparing(Book::getTitle));
        System.out.println("Books sorted by title.");
    }
}
