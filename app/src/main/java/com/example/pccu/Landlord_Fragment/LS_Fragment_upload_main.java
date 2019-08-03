package com.example.pccu.Landlord_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pccu.R;

public class LS_Fragment_upload_main extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_upload_frame, container, false);

        initView(view);
        return view;
    }

    private void initView(View view){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        LS_Fragment_upload fragment = new LS_Fragment_upload();
        transaction.add(R.id.frame1, fragment);
        transaction.commit();
    }
}
