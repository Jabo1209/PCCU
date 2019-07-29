package com.example.pccu.More;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pccu.R;


public class FragmentMore_main extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_more, container, false);

        initView(view);
        return view;
    }

    private void initView(View view){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment_list fragment = new Fragment_list();
        transaction.add(R.id.frame, fragment);
        transaction.commit();
    }
}
