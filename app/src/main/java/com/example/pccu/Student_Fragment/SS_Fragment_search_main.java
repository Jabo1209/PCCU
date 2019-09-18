package com.example.pccu.Student_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pccu.R;

public class SS_Fragment_search_main extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_frame, container, false);

        initView(view);
        return view;
    }

    private void initView(View view){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        SS_Fragment_search fragment = new SS_Fragment_search();
        transaction.add(R.id.frame_search, fragment);
        transaction.commit();
    }
}
