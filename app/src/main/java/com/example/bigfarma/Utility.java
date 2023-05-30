package com.example.bigfarma;

import java.util.ArrayList;

public class Utility {

    private static int ID;

    private static ArrayList<Category> AllCategories;

    public Utility() {
        if (AllCategories ==null){
            AllCategories = new ArrayList<>();
            initAllCategory();
        }
    }



    public static ArrayList<Category> getAllCategories() {
        return AllCategories;
    }

    public boolean removeCategory(Category category){
        return AllCategories.remove(category);
    }

    private static void initAllCategory() {
       String Name="Homework";
       String ImageUrl = "java";
       ID++;

       AllCategories.add(new Category(ID, Name, ImageUrl));

        Name="Week End";
         ImageUrl = "java";
        ID++;

        AllCategories.add(new Category(ID, Name, ImageUrl));

        Name="Week Day";
         ImageUrl = "java";
        ID++;

        AllCategories.add(new Category(ID, Name, ImageUrl));
    }
}
