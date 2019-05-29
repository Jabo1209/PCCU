package com.example.pccu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class FragmentList_upload extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_upload, container, false);
        return view;
    }

    static class Upload extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_upload);

            //取消ActionBar
            getSupportActionBar().hide();

            Spinner spinner1=(Spinner)findViewById(R.id.spinner1);
            Spinner spinner2=(Spinner)findViewById(R.id.spinner2);
            Spinner spinner3=(Spinner)findViewById(R.id.spinner3);
            Spinner spinner4=(Spinner)findViewById(R.id.spinner4);
            Spinner spinner5=(Spinner)findViewById(R.id.spinner5);
            Spinner spinner6=(Spinner)findViewById(R.id.spinner6);

            //房型選單
            final String[] room={"單人房","雙人房","三人房","四人房","家庭式","其他"};
            ArrayAdapter<String> roomList=new ArrayAdapter<>(Upload.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    room);
            spinner1.setAdapter(roomList);

            //機車停車位選單
            final String[] parkingspace={"有","無"};
            ArrayAdapter<String> parkingspaceList=new ArrayAdapter<>(Upload.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    parkingspace);
            spinner2.setAdapter(parkingspaceList);

            //寵物選單
            final String[] pet={"可以養","不能養"};
            ArrayAdapter<String> petList=new ArrayAdapter<>(Upload.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    pet);
            spinner3.setAdapter(petList);

            //水費
            final String[] WaterFee={"包水","不包水"};
            ArrayAdapter<String> WaterFeeList=new ArrayAdapter<>(Upload.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    WaterFee);
            spinner4.setAdapter(WaterFeeList);

            //電費
            final String[] ElectricityFee={"包電","不包電"};
            ArrayAdapter<String> ElectricityFeeList=new ArrayAdapter<>(Upload.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    ElectricityFee);
            spinner5.setAdapter(ElectricityFeeList);

            //網路
            final String[] internet={"包網路","不包網路"};
            ArrayAdapter<String> internetList=new ArrayAdapter<>(Upload.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    internet);
            spinner6.setAdapter(internetList);

        }
    }
}

