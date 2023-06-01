package com.example.bigfarma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NewTask extends AppCompatActivity {
        EditText Title;
        EditText Desc;
        EditText StartDate;
        EditText EndDate;
        EditText Time;

        ImageButton camera;

    private static final String TAG = "NewTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        innit();


    }

    public void innit(){
        Title = findViewById(R.id.titeInput);
        Desc= findViewById(R.id.DecriptionInput);
        StartDate= findViewById(R.id.StartDateEditTxt);
        EndDate= findViewById(R.id.endDateEditTxt);
        Time= findViewById(R.id.TimeInput);

        camera = findViewById(R.id.cameraPrompt);
        Title.getText().toString();

    }

    public void AddNewTask(View view){


        Utility util = new Utility();
        int id =  util.getTaskId();
//        ArrayList<Task> This = new ArrayList<>();
//        This.add(new Task(id, Title.getText().toString(), Desc.getText().toString(),Desc.getText().toString(),EndDate.getText().toString(),StartDate.getText().toString(),Time.getText().toString()));
        util.setNewTask(id, Title.getText().toString(), Desc.getText().toString(),Desc.getText().toString(),EndDate.getText().toString(),StartDate.getText().toString(),Time.getText().toString());

        for (Task task : util.getNewTask()) {
            System.out.println("Task ID: " + task.getId());
            System.out.println("Title: " + task.getTitle());
            System.out.println("Description: " + task.getDesc());
            System.out.println("End Date: " + task.getEndDate());
            System.out.println("Start Date: " + task.getStartDate());
            System.out.println("Time: " + task.getTime());
            System.out.println("-------------------------");
        }

             Intent ToMain = new Intent(NewTask.this, MainView.class);
             startActivity(ToMain);

    }
}