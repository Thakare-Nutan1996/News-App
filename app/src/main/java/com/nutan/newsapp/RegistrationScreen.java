package com.nutan.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText edtfname,edtlname,edtemail,edtpassword,edtconfirm_password;

    //private Spinner spinner;
    FirebaseDatabase database;
    DatabaseReference reference;
    Member member;
    int maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        //Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        AppCompatButton sign_up_2=findViewById(R.id.sign_up_2);
        sign_up_2.setOnClickListener(this);

        edtfname=(EditText)findViewById(R.id.fname);
        edtlname=(EditText)findViewById(R.id.lname);
        edtemail=(EditText)findViewById(R.id.email);
        edtpassword=(EditText)findViewById(R.id.password);
        edtconfirm_password=(EditText)findViewById(R.id.confirm_password);

        //Spinner
        member=new Member();
        reference=database.getInstance().getReference().child("Spinner");

        //Hiding Action Bar
        getSupportActionBar().hide();

        //Spinner Dropdownlist
        Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.education, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text=adapterView.getItemAtPosition(i).toString();
/*
        Toast.makeText(this, "text", Toast.LENGTH_SHORT).show();
*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

  /*  reference.addValueEventListener(new ValueEventListener(){
      @Override
      public void onDataChange(DataSnapshot datasnapshot){

        }

        @Override
        public void onCancelled(DatabaseError databaseError){

        }
    });*/

    @Override
    public void onClick(View view) {
        sign_up_2();
    }

    private void sign_up_2() {
        String fname=edtfname.getText().toString().trim();
        String lname=edtlname.getText().toString().trim();
        String email=edtemail.getText().toString().trim();
        String password=edtpassword.getText().toString().trim();
        String confirm_password=edtconfirm_password.getText().toString().trim();

        if(fname.isEmpty()){
            edtfname.setError("First name is required!");
            edtfname.requestFocus();
            return;
        }

        if(lname.isEmpty()){
            edtlname.setError("Last name is required!");
            edtlname.requestFocus();
            return;
        }

        if(email.isEmpty()){
            edtemail.setError("Email is required!");
            edtemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtemail.setError("Please provide valid email!");
            edtemail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edtpassword.setError("Password is required!");
            edtpassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            edtpassword.setError("Minimum password length should be 6 characters!");
            edtpassword.requestFocus();
            return;
        }

        if(confirm_password.isEmpty()){
            edtconfirm_password.setError("Confirm Password is required!");
            edtconfirm_password.requestFocus();
            return;
        }

       /* if(confirm_password!=password){
            edtconfirm_password.setError("Confirm Password does not match Password!");
            edtconfirm_password.requestFocus();
            return;
        }
*/
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(fname, lname, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegistrationScreen.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();

                                                //Progressbar redirect to login layout
                                            } else {
                                                Toast.makeText(RegistrationScreen.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }else{
                            Toast.makeText(RegistrationScreen.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}