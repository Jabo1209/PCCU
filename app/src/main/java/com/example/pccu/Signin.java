package com.example.pccu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;



public class Signin extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private String userUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //取消ActionBar
        getSupportActionBar().hide();
        //取消狀態欄
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    

    public void Bt_back(View v){
        Intent it = new Intent(Signin.this, MainActivity.class);
        startActivity(it);
        finish();
    }
    public void Bt_registered(View v){
        Intent it = new Intent(Signin.this,Registered.class);
        startActivity(it);
        finish();
    }
}