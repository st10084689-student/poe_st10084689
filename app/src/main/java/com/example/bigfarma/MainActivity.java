package com.example.bigfarma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView RegisterTxt;
    RelativeLayout LoginBtn;
    private String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Innit();
    }

    public void Innit(){
        RegisterTxt = (TextView) findViewById(R.id.txtRegister);
        LoginBtn = (RelativeLayout) findViewById(R.id.imgEnterBtn);
        RegisterTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: To Sign In");
                Intent intent =new Intent(MainActivity.this, SignIn.class);
               startActivity(intent);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: To Main");
                Intent intent =new Intent(MainActivity.this, MainView.class);
                startActivity(intent);
            }
        });


    }

}

