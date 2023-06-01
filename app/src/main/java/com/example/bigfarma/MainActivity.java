package com.example.bigfarma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView RegisterTxt;
    private FirebaseAuth mAuth;

    TextView UserName;
    TextView UserPassword;
    RelativeLayout LoginBtn;
    private String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: created" );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mAuth = FirebaseAuth.getInstance();
        Innit();

    }

    public void Innit(){
        Log.d(TAG, "Innit: Initialized");
        RegisterTxt = (TextView) findViewById(R.id.txtRegister);
        LoginBtn = (RelativeLayout) findViewById(R.id.imgEnterBtn);
        UserName = findViewById(R.id.edtTxtEmail);
        UserPassword = findViewById(R.id.edtTxtPassword);



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

            }
        });


    }

    public void loginClick(View view){
        Log.d(TAG, "onClick: To Main");
        loginUser();
    }

    public void loginUser() {

        Log.d(TAG, "loginUser: Working");
        try {
            String email = UserName.getText().toString().trim();
            String password = UserPassword.getText().toString().trim();

            //check...not allow --> blank



            if (TextUtils.isEmpty(email)) {
                Log.d(TAG, "making a toast ");
                Toast.makeText(MainActivity.this, "Entered email slot is empty", Toast.LENGTH_LONG).show();


            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity.this, "Entered password slot is empty", Toast.LENGTH_SHORT).show();
            }

            //add an onCompleteListener

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.d(TAG, "ContentIntent");
                        Intent toMainView =new Intent(MainActivity.this, MainView.class);
                        MainActivity.this.startActivity(toMainView);
                        //optional to reset all fields
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Exception e){
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }

    }

}

