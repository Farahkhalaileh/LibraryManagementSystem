package com.mycompany.librarymanagementsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryGUI extends Application {

    private Library library = new Library();
    private TextArea outputArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        // Layout setup
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        // Book fields
        TextField titleField = new TextField();
        titleField.setPromptText("Book Title");
        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        Button addBookButton = new Button("Add Book");
        addBookButton.setOnAction(e -> addBook(titleField.getText(), authorField.getText()));

        // Member fields
        TextField memberNameField = new TextField();
        memberNameField.setPromptText("Member Name");

        Button addMemberButton = new Button("Add Member");
        addMemberButton.setOnAction(e -> addMember(memberNameField.getText()));

        // Borrow book fields
        TextField borrowMemberField = new TextField();
        borrowMemberField.setPromptText("Member Name for Borrowing");
        TextField borrowBookField = new TextField();
        borrowBookField.setPromptText("Book Title to Borrow");

        Button borrowBookButton = new Button("Borrow Book");
        borrowBookButton.setOnAction(e -> borrowBook(borrowMemberField.getText(), borrowBookField.getText()));

        // Return book fields
        TextField returnMemberField = new TextField();
        returnMemberField.setPromptText("Member Name for Returning");
        TextField returnBookField = new TextField();
        returnBookField.setPromptText("Book Title to Return");

        Button returnBookButton = new Button("Return Book");
        returnBookButton.setOnAction(e -> returnBook(returnMemberField.getText(), returnBookField.getText()));

        // Display available books
        Button displayBooksButton = new Button("Display Available Books");
        displayBooksButton.setOnAction(e -> displayAvailableBooks());

        // Output area for displaying results
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200);

        // Arrange fields in layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("Add Book"), 0, 0);
        gridPane.add(titleField, 1, 0);
        gridPane.add(authorField, 2, 0);
        gridPane.add(addBookButton, 3, 0);

        gridPane.add(new Label("Add Member"), 0, 1);
        gridPane.add(memberNameField, 1, 1);
        gridPane.add(addMemberButton, 3, 1);

        gridPane.add(new Label("Borrow Book"), 0, 2);
        gridPane.add(borrowMemberField, 1, 2);
        gridPane.add(borrowBookField, 2, 2);
        gridPane.add(borrowBookButton, 3, 2);

        gridPane.add(new Label("Return Book"), 0, 3);
        gridPane.add(returnMemberField, 1, 3);
        gridPane.add(returnBookField, 2, 3);
        gridPane.add(returnBookButton, 3, 3);

        layout.getChildren().addAll(gridPane, displayBooksButton, outputArea);

        // Set up the scene and show the stage
        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        private void addBook(String title, String author) {
        if (!title.isEmpty() && !author.isEmpty()) {
            library.addBook(new Book(title, author));
            outputArea.setText("Added book: " + title + " by " + author);
        } else {
            outputArea.setText("Please enter both title and author.");
        }
    }

    private void addMember(String name) {
        if (!name.isEmpty()) {
            int userId = library.getMembers().size() + 1; // Generate a new user ID
            library.addMember(new LibraryUser(name, userId));
            outputArea.setText("Added member: " + name);
        } else {
            outputArea.setText("Please enter a valid member name.");
        }
    }

    private void borrowBook(String memberName, String bookTitle) {
        LibraryUser user = findMemberByName(memberName);
        Book book = findBookByTitle(bookTitle);

        if (user != null && book != null) {
            if (book.isAvailable()) {
                library.borrowBook(user, book);
                outputArea.setText(memberName + " borrowed the book: " + bookTitle);
            } else {
                outputArea.setText("The book '" + bookTitle + "' is already borrowed.");
            }
        }
    }

    private void returnBook(String memberName, String bookTitle) {
        LibraryUser user = findMemberByName(memberName);
        Book book = findBookByTitle(bookTitle);

        if (user != null && book != null) {
            if (!book.isAvailable()) {
                library.returnBook(user, book);
                outputArea.setText(memberName + " returned the book: " + bookTitle);
            } else {
                outputArea.setText("The book '" + bookTitle + "' is not currently borrowed.");
            }
        }
    }

    private void displayAvailableBooks() {
        StringBuilder availableBooks = new StringBuilder("Available Books:\n");
        for (Book book : library.getBooks()) {
            if (book.isAvailable()) {
                availableBooks.append(book.getTitle()).append(" by ").append(book.getAuthor()).append("\n");
            }
        }
        if (availableBooks.length() == 0) {
            outputArea.setText("No books available.");
        } else {
            outputArea.setText(availableBooks.toString());
        }
    }

    private LibraryUser findMemberByName(String name) {
        for (LibraryUser member : library.getMembers()) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        outputArea.setText("Member not found: " + name);
        return null;
    }

    private Book findBookByTitle(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        outputArea.setText("Book not found: " + title);
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}