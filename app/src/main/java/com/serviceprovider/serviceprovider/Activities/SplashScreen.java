package com.serviceprovider.serviceprovider.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.serviceprovider.serviceprovider.R;

public class SplashScreen extends AppCompatActivity {

    private ImageView logo;
    private static int splashTimeOut = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.imageView2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =  new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },splashTimeOut);
        Animation myAnimation  = AnimationUtils.loadAnimation(this,R.anim.splash_anim);
        logo.startAnimation(myAnimation);
    }
}