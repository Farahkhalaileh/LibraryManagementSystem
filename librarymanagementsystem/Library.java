package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<LibraryUser> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book: " + book.getTitle());
    }

    public List<Book> getBooks() {
        return books;
    }

    public void listAvailableBooks() {
        System.out.println("Available Books: ");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        System.out.println("Book not found: " + title);
        return null;
    }

    public void addMember(LibraryUser member) {
        members.add(member);
        System.out.println("Added member: " + member.getName());
    }

    public List<LibraryUser> getMembers() {
        return members;
    }

    public void borrowBook(LibraryUser member, Book book) {
        if (book.isAvailable()) {
            book.borrowBook();
            System.out.println(member.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook(LibraryUser member, Book book) {
        book.returnBook();
        System.out.println(member.getName() + " returned " + book.getTitle());
    }
}