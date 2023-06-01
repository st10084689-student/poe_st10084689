package com.example.bigfarma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private FirebaseAuth sAuth;

    EditText UserEmail;

    EditText UserPassword;

    EditText UserConfirmPassword;

    private static final String TAG = "SignIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        innit();
        sAuth= FirebaseAuth.getInstance();
    }

    public void innit(){
        UserEmail = findViewById(R.id.edtTxtEmail);

        UserPassword = findViewById(R.id.SignUpPassword);

        UserConfirmPassword = findViewById(R.id.SignUpConfirmPassword);

    }

    public void SignUpUser(View view ) {

        Log.d(TAG, "SignUpUser: Working");
        try {
            String email = UserEmail.getText().toString().trim();
            String password = UserPassword.getText().toString().trim();

            Log.d(TAG, "SignUpUser: "+ email);
            Log.d(TAG, "SignUpUser: "+ password);
        /*    String passwordConfirm = UserPassword.getText().toString().trim();*/

            //check...not allow --> blank



            if (TextUtils.isEmpty(email)) {
                Log.d(TAG, "making a toast ");
                Toast.makeText(this, "Entered email slot is empty", Toast.LENGTH_LONG).show();


            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Entered password slot is empty", Toast.LENGTH_SHORT).show();
            }
//            if (password!=passwordConfirm) {
//                Toast.makeText(this, "Entered kjlhjlh password slot is empty", Toast.LENGTH_SHORT).show();
//            }

            //add an onCompleteListener

            sAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d(TAG, "onComplete: create");
                    if(task.isSuccessful()){
                        Log.d(TAG, "is successful");
                        Intent toMainView =new Intent(SignIn.this, MainView.class);
                        SignIn.this.startActivity(toMainView);
                        //optional to reset all fields
                    }
                    else{
                        Toast.makeText(SignIn.this,"Login failed",Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Exception e){
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }

    }
}