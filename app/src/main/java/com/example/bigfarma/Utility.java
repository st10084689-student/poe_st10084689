package com.example.bigfarma;

import java.util.ArrayList;

public class Utility {

    private static ArrayList<Category> AllCategories;

    public Utility() {
        if (AllCategories ==null){
            AllCategories = new ArrayList<>();
            initAllBooks();
        }
    }

    private static void initAllBooks() {
        //TODO: initialize all books in the array list
    }
}
