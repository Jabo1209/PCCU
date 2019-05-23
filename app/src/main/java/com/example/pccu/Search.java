package com.example.pccu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //取消ActionBar
        getSupportActionBar().hide();
        //取消狀態欄
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Spinner spinner1=(Spinner)findViewById(R.id.spinner);
        Spinner spinner2=(Spinner)findViewById(R.id.spinner2);
        Spinner spinner3=(Spinner)findViewById(R.id.spinner3);

        final String[] water={"不限","包水","不包水"};
        ArrayAdapter<String> waterList=new ArrayAdapter<>(Search.this,
                android.R.layout.simple_spinner_dropdown_item,
                water);
        spinner1.setAdapter(waterList);

        final String[] electricity={"不限","包電","不包電"};
        ArrayAdapter<String> electricityList=new ArrayAdapter<>(Search.this,
                android.R.layout.simple_spinner_dropdown_item,
                electricity);
        spinner2.setAdapter(electricityList);

        final String[] internet={"不限","包網路","不包網路"};
        ArrayAdapter<String> internetList=new ArrayAdapter<>(Search.this,
                android.R.layout.simple_spinner_dropdown_item,
                internet);
        spinner3.setAdapter(internetList);

    }

    public void Bt_back(View v){
        Intent it = new Intent(Search.this, MainActivity.class);
        startActivity(it);
        finish();
    }
}
