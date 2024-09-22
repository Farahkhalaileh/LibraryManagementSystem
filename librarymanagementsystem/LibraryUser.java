package com.mycompany.librarymanagementsystem;
import java.util.ArrayList;
import java.util.List;

public class LibraryUser {
    private String name;
    private int userId;

    public LibraryUser(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
       
}
