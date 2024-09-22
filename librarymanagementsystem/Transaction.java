package com.mycompany.librarymanagementsystem;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private LibraryUser user;
    private Book book;
    private String transactionType;//Borrow or return

    public Transaction(LibraryUser user, Book book, String transactionType) {
        this.user = user;
        this.book = book;
        this.transactionType = transactionType;
    }
    
    public void performTransaction(){
        if(transactionType.equalsIgnoreCase("borrow")){
            book.borrowBook();
        } else if(transactionType.equalsIgnoreCase("return")){
            book.returnBook();
        }
    }
    
    public void showTransactionDetails(){
        System.out.println("User: "+user.getName()+" | Book: "+book.getTitle()+" | Action: "+transactionType);
    }
}
