package com.mycompany.librarymanagementsystem;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private boolean isAvailable;
    
    public Book(String title, String author){
        this.title=title;
        this.author=author;
        this.isAvailable=true; //Initially the book is available.
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    
    public boolean isAvailable(){
    return isAvailable;
}
    
    public void borrowBook(){
        if(isAvailable){
            this.isAvailable=false;
            System.out.println("Book borrowed: "+title);
        } else {
            System.out.println("Sorry, this book is already borrowed.");
            }
    }
    
    public void returnBook(){
        this.isAvailable=true;
        System.out.println("Book returned: "+title);
    }
}
