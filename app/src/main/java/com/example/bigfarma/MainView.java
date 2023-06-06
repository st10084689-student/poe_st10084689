package com.example.bigfarma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
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

    private TextView List,About,Main;

    private String TAG = "Main View";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        innit();

    }

    public void innit(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MainViewFragment fragment = new MainViewFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();

        List = findViewById(R.id.listText);
        About = findViewById(R.id.AboutTxt);
        Main = findViewById(R.id.MainTxt);

        List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                TaskFragment ToList= new TaskFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, ToList);
                fragmentTransaction.commit();
                Main.setBackground(null);
                About.setBackground(null);
                List.setBackgroundResource(R.drawable.green_circle);
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                AboutFragment ToAbout= new AboutFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, ToAbout);
                fragmentTransaction.commit();
                Main.setBackground(null);
                List.setBackground(null);
                About.setBackgroundResource(R.drawable.green_circle);
            }
        });

        Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                MainViewFragment ToMain= new MainViewFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, ToMain);
                fragmentTransaction.commit();
                About.setBackground(null);
                List.setBackground(null);
                Main.setBackgroundResource(R.drawable.green_circle);
            }
        });
    }
}

