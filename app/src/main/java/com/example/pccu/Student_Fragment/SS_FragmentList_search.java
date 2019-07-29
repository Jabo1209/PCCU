package com.example.pccu.Student_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pccu.R;

public class SS_FragmentList_search extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search, container, false);

        initView(view);
        initData();
        return view;
    }

    private void initView(View view){

    }

    private void initData(){

    }
}
