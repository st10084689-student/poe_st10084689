package com.example.bigfarma;

import java.util.ArrayList;
import java.util.Date;

public class Utility {

    private static int CATEGORY_ID;

    private static int TASK_ID;

    private static ArrayList<Category> AllCategories;

    private static ArrayList<Task> newTask;

    public Utility() {
        if (AllCategories ==null){
            AllCategories = new ArrayList<>();
            initAllCategory();
        }

        if(newTask ==null){
            newTask = new ArrayList<>();
            initAllTask();
        }
    }



    public static ArrayList<Category> getAllCategories() {
        return AllCategories;
    }

    public static ArrayList<Task> getNewTask() {
        return newTask;
    }

    public static void setNewTask(int _TASK_ID,String _Title, String _ImageUrl,String _Description,String _EndDate,String _StartDate,String _Time) {
        newTask.add(new Task(_TASK_ID, _Title,  _ImageUrl, _Description, _EndDate, _StartDate, _Time));
    }

    public static int getTaskId() {
        TASK_ID++;
        return TASK_ID;
    }

    public static void setTaskId(int taskId) {
        TASK_ID = taskId;
    }

    public boolean removeCategory(Category category){
        return AllCategories.remove(category);
    }

    private static void initAllCategory() {
       String Name="Homework";
       String ImageUrl = "java";
        CATEGORY_ID++;

       AllCategories.add(new Category(CATEGORY_ID, Name, ImageUrl));

        Name="Week End";
         ImageUrl = "java";
        CATEGORY_ID++;

        AllCategories.add(new Category(CATEGORY_ID, Name, ImageUrl));

        Name="Week Day";
         ImageUrl = "java";
        CATEGORY_ID++;

        AllCategories.add(new Category(CATEGORY_ID, Name, ImageUrl));
    }

    private static void initAllTask() {
        String Title="Homework";
        String ImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJ2HCQwNOetaK7RAV-XTjKdwNsOlGce2D_QljqHUMI&s";
        String Description = "java";
        String EndDate = "java";
        String StartDate = "java";
        String Time = "java";

        TASK_ID++;

        newTask.add(new Task(TASK_ID, Title, ImageUrl,Description,EndDate,StartDate,Time));

         Title="Homework";
         ImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJ2HCQwNOetaK7RAV-XTjKdwNsOlGce2D_QljqHUMI&s";
         Description = "java";
         EndDate = "java";
         StartDate = "java";
         Time = "java";
        TASK_ID++;

        newTask.add(new Task(TASK_ID, Title, ImageUrl,Description,EndDate,StartDate,Time));


        Title="Homework";
        ImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJ2HCQwNOetaK7RAV-XTjKdwNsOlGce2D_QljqHUMI&s";
        Description = "java";
        EndDate = "java";
        StartDate = "java";
        Time = "java";
        TASK_ID++;

        newTask.add(new Task(TASK_ID, Title, ImageUrl,Description,EndDate,StartDate,Time));

    }
}
