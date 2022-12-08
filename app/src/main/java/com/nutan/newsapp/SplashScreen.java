package com.nutan.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    ImageView logo,appName,img;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        logo=findViewById(R.id.logo);
        appName=findViewById(R.id.appName);
        img=findViewById(R.id.img);
        lottieAnimationView=findViewById(R.id.lottie);
        /*img.animate().translationY(-3000).setDuration(1000).setStartDelay(3000);
        logo.animate().translationY(2500).setDuration(1000).setStartDelay(3000);
        appName.animate().translationY(2500).setDuration(1000).setStartDelay(3000);*/
        /*lottieAnimationView.animate().translationY(2500).setDuration(1000).setStartDelay(3000);*/

       /* Animation animation1= AnimationUtils.loadAnimation(this,R.anim.img_bg);
        img.setAnimation(animation1);*/
        ObjectAnimator animation1=ObjectAnimator.ofFloat(img,"translationY",-3000f);
        animation1.setDuration(1000);
        animation1.setStartDelay(2000);
        animation1.start();

        ObjectAnimator animation2=ObjectAnimator.ofFloat(logo,"translationY",2500f);
        animation2.setDuration(1000);
        animation2.setStartDelay(2000);
        animation2.start();

        ObjectAnimator animation3=ObjectAnimator.ofFloat(appName,"translationY",2500f);
        animation3.setDuration(1000);
        animation3.setStartDelay(2000);
        animation3.start();

        ObjectAnimator animation4=ObjectAnimator.ofFloat(lottieAnimationView,"translationY",2500f);
        animation4.setDuration(1000);
        animation4.setStartDelay(2000);
        animation4.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,LaunchApp.class));
                finish();
            }
        },3000);

    }
}