package com.nutan.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class LaunchApp extends AppCompatActivity {
    AppCompatButton sign_up,sign_in;
    LottieAnimationView lottie_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_app);

        getSupportActionBar().hide();

        sign_up=findViewById(R.id.sign_up);
        sign_in=findViewById(R.id.sign_in);
        lottie_img=findViewById(R.id.lottie_img);

        lottie_img.animate().setDuration(5000).setStartDelay(1000);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LaunchApp.this,LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LaunchApp.this,RegistrationScreen.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}