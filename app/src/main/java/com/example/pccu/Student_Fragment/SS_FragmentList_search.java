package com.example.pccu.Student_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pccu.R;

public class SS_FragmentList_search extends Fragment {
    private Spinner spinner1,spinner2,spinner3;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search, container, false);

        initView(view);
        initData();
        return view;
    }

    private void initView(View view){
        spinner1=(Spinner)view.findViewById(R.id.spinner);
        spinner2=(Spinner)view.findViewById(R.id.spinner2);
        spinner3=(Spinner)view.findViewById(R.id.spinner3);

        final String[] water={"不限","包水","不包水"};
        ArrayAdapter<String> waterList=new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                water);
        spinner1.setAdapter(waterList);

        final String[] electricity={"不限","包電","不包電"};
        ArrayAdapter<String> electricityList=new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                electricity);
        spinner2.setAdapter(electricityList);

        final String[] internet={"不限","包網路","不包網路"};
        ArrayAdapter<String> internetList=new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                internet);
        spinner3.setAdapter(internetList);
    }

    private void initData(){

    }
}
