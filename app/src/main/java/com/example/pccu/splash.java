package com.example.pccu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.pccu.Login.Signin;

public class splash extends AppCompatActivity{

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //取消ActionBar
        getSupportActionBar().hide();
        //取消狀態欄
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        logo = findViewById(R.id.splash_logo);
        Animation myanim = AnimationUtils.loadAnimation(splash.this, R.anim.fade_in);
        logo.startAnimation(myanim);
        final Intent aftersplash = new Intent(splash.this, Signin.class);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(aftersplash);
                    finish();
                }
            }
        };
        timer.start();
    }
}
