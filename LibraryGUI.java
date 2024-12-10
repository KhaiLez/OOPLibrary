package com.example.library;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;

public class LibraryGUI extends Application {
    private Library library;
    private ListView<String> bookListView; // Danh sách hiển thị sách
    private TextField searchField; // Ô tìm kiếm
    private TextField idField, titleField, authorField, genreField, yearField, statusField; // Thông tin sách

    @Override
    public void start(Stage primaryStage) {
        library = new Library("L001", "City Library");

        // Danh sách sách
        bookListView = new ListView<>();
        updateBookListView();

        // Các trường nhập thông tin sách
        idField = new TextField();
        titleField = new TextField();
        authorField = new TextField();
        genreField = new TextField();
        yearField = new TextField();
        statusField = new TextField();

        GridPane bookForm = new GridPane();
        bookForm.setHgap(10);
        bookForm.setVgap(10);
        bookForm.add(new Label("ID:"), 0, 0);
        bookForm.add(idField, 1, 0);
        bookForm.add(new Label("Title:"), 0, 1);
        bookForm.add(titleField, 1, 1);
        bookForm.add(new Label("Author:"), 0, 2);
        bookForm.add(authorField, 1, 2);
        bookForm.add(new Label("Genre:"), 0, 3);
        bookForm.add(genreField, 1, 3);
        bookForm.add(new Label("Year:"), 0, 4);
        bookForm.add(yearField, 1, 4);
        bookForm.add(new Label("Status:"), 0, 5);
        bookForm.add(statusField, 1, 5);

        // Các nút điều khiển
        Button addBookButton = new Button("Add Book");
        Button removeBookButton = new Button("Remove Book");
        Button searchBookButton = new Button("Search");
        Button sortTitleButton = new Button("Sort by Title");
        Button sortYearButton = new Button("Sort by Year");

        addBookButton.setOnAction(e -> addBook());
        removeBookButton.setOnAction(e -> removeSelectedBook());
        searchBookButton.setOnAction(e -> searchBook());
        sortTitleButton.setOnAction(e -> sortBooksByTitle());
        sortYearButton.setOnAction(e -> sortBooksByYear());

        VBox controlButtons = new VBox(11, addBookButton, removeBookButton, searchBookButton, sortTitleButton, sortYearButton);
        controlButtons.setAlignment(Pos.CENTER);

        // Giao diện chính
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(new VBox(new Label("Books List:"), bookListView));
        mainLayout.setCenter(bookForm);
        mainLayout.setRight(controlButtons);

        Scene scene = new Scene(mainLayout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Library Management System");
        primaryStage.show();
    }

    // Cập nhật danh sách sách hiển thị
    private void updateBookListView() {
        bookListView.getItems().clear();
        for (Book book : library.getBooks()) {
            bookListView.getItems().add(book.toString());
        }
    }

    // Thêm sách
    private void addBook() {
        try {
            String id = idField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();
            int year = Integer.parseInt(yearField.getText());
            String status = statusField.getText();

            Book newBook = new Book(id, title, author, genre, year, status);
            library.addBook(newBook);
            updateBookListView();

            // Clear fields
            idField.clear();
            titleField.clear();
            authorField.clear();
            genreField.clear();
            yearField.clear();
            statusField.clear();
        } catch (Exception e) {
            showAlert("Error", "Invalid input! Please check your fields.");
        }
    }

    // Xóa sách được chọn
    private void removeSelectedBook() {
        String selectedBook = bookListView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            String bookId = selectedBook.split(",")[0].split(":")[1].trim(); // Lấy ID sách
            library.removeBook(bookId);
            updateBookListView();
        } else {
            showAlert("Error", "No book selected!");
        }
    }

    // Tìm kiếm sách
    private void searchBook() {
        String keyword = searchField.getText();
        Book foundBook = library.searchBook(keyword);
        if (foundBook != null) {
            showAlert("Search Result", foundBook.toString());
        } else {
            showAlert("Search Result", "No book found!");
        }
    }

    // Sắp xếp sách theo tiêu đề
    private void sortBooksByTitle() {
        library.sortBooksByTitle();
        updateBookListView();
    }

    // Sắp xếp sách theo năm xuất bản
    private void sortBooksByYear() {
        library.sortBooksByYear();
        updateBookListView();
    }

    // Hiển thị thông báo
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

