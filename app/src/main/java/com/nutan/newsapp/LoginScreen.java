package com.nutan.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton sign_in_2;
    private AppCompatEditText edt_user_name;
    private TextInputEditText edt_password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        getSupportActionBar().hide();

        sign_in_2=(AppCompatButton) findViewById(R.id.sign_in_2);
        sign_in_2.setOnClickListener(this);

        edt_user_name=(AppCompatEditText) findViewById(R.id.user_name);
        edt_password=(TextInputEditText) findViewById(R.id.password);

        mAuth=FirebaseAuth.getInstance();
        /*sign_in_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        sign_in_2();
    }

    private void sign_in_2() {
        String email=edt_user_name.getText().toString().trim();
        String password=edt_password.getText().toString().trim();

        if(email.isEmpty()){
            edt_user_name.setError("Email is required!");
            edt_user_name.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_user_name.setError("Please provide valid email!");
            edt_user_name.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edt_password.setError("Password is required!");
            edt_password.requestFocus();
            return;
        }

        if(password.length() < 6){
            edt_password.setError("Minimum password length should be 6 characters!");
            edt_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    //redirecting to News Page
                        startActivity(new Intent(LoginScreen.this,MainActivity.class));
                }else{
                        Toast.makeText(LoginScreen.this, "Failed to Login! Please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}