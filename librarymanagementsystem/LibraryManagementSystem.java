package com.mycompany.librarymanagementsystem;

public class LibraryManagementSystem {

    public static void main(String[] args) {
    //Create library and books
    Library library=new Library();
    library.addBook(new Book("JAVA Programming", "John Doe"));
    library.addBook(new Book("Python Essentials", "Jane Smith"));
    
    //Create library users
    LibraryUser user1=new LibraryUser("Farah", 101);
    
    //List available books
    library.listAvailableBooks();
    
    //User borrows a book
    Book bookToBorrow=library.findBookByTitle("JAVA Programming");
    if(bookToBorrow!=null){
        Transaction borrowTransaction = new Transaction(user1, bookToBorrow, "borrow");
        borrowTransaction.performTransaction();
        borrowTransaction.showTransactionDetails();
    }
        
    //List available books again
    library.listAvailableBooks();
    
    
    //User returns a book
    Transaction returnTransaction= new Transaction(user1, bookToBorrow, "return" );
    returnTransaction.performTransaction();
    returnTransaction.showTransactionDetails();
    
    //List available books again
    library.listAvailableBooks();
    }
    
}
