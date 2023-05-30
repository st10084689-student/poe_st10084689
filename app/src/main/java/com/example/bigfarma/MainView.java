package com.example.bigfarma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainView extends AppCompatActivity {
 private RecyclerView categoryRecView;
    private String TAG = "Main View";
private RelativeLayout newTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
         categoryRecView = findViewById(R.id.categoryRecyclerView);
        newTask = findViewById(R.id.NewCard);

        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainView.this, NewTask.class);
                startActivity(intent);


            }
        });

         CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(this);

         categoryRecView.setAdapter(adapter);
         categoryRecView.setLayoutManager(new LinearLayoutManager(this,categoryRecView.HORIZONTAL,false));

//         ArrayList<Category> categories = new ArrayList<>();
//         categories.add(new Category("HomeWork","https://www.bing.com/ck/a?!&&p=cb7ddccb76127e66JmltdHM9MTY4NTQwNDgwMCZpZ3VpZD0xMjU5ZDZiZS1mMTk1LTY3YTAtMmJmYS1jNDc0ZjA2ODY2ZTMmaW5zaWQ9NTUyNQ&ptn=3&hsh=3&fclid=1259d6be-f195-67a0-2bfa-c474f06866e3&u=a1L2ltYWdlcy9zZWFyY2g_cT1JTUFHRVMgT0YgSE9NRVdPUksmRk9STT1JUUZSQkEmaWQ9ODIxRjUyNjAwQ0E5NDlCRTZFQkQ0OEQ4RDQ1RkE1QkFFMTFGQzdCRA&ntb=1"));
//        categories.add(new Category("WeekEnd","https://www.google.com/imgres?imgurl=https%3A%2F%2Fectutoring.com%2Fwp-content%2Fuploads%2F2022%2F02%2FADHD-Homework-Help-at-ectutoring.com_.png&tbnid=iENCQNbdZJF5TM&vet=12ahUKEwiC67jz_Zz_AhW9pycCHXbYD0oQMygCegUIARDkAQ..i&imgrefurl=https%3A%2F%2Fectutoring.com%2Fadhd-homework&docid=gELNzoII70chwM&w=1200&h=628&q=homework&ved=2ahUKEwiC67jz_Zz_AhW9pycCHXbYD0oQMygCegUIARDkAQ"));
//        categories.add(new Category("It Job ","C:\\Users\\jelid\\OneDrive\\Pictures\\Saved Pictures\\anime-graffiti-cars-lime-green-nsx.webp"));
//        categories.add(new Category("HomeWork","C:\\Users\\jelid\\OneDrive\\Pictures\\Saved Pictures\\anime-graffiti-cars-lime-green-nsx.webp"));

        Utility util = new Utility();
        ArrayList<Category> categories = new ArrayList<>();
        categories = util.getAllCategories();
         adapter.setCategories(categories);
    }

}

