package com.example.pccu.Student_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pccu.R;

public class SS_Fragment_search extends Fragment {

    Button search;
    Spinner spinner,spinner1,spinner2,spinner3,spinner4,spinner5;
    String sroom,sparkingspace,spet,swaterfee,selectricityfee,sinternet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search, container, false);

        initView(view);
        return view;
    }

    public void initView(View v){
        spinner=(Spinner) v.findViewById(R.id.spinner);
        spinner1=(Spinner) v.findViewById(R.id.spinner1);
        spinner2=(Spinner) v.findViewById(R.id.spinner2);
        spinner3=(Spinner) v.findViewById(R.id.spinner3);
        spinner4=(Spinner) v.findViewById(R.id.spinner4);
        spinner5=(Spinner) v.findViewById(R.id.spinner5);
        search=(Button)v.findViewById(R.id.button);

        //房型選單
        final String[] room={"單人房","雙人房","三人房","四人房","家庭式","其他"};
        final ArrayAdapter<String> roomList = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                room);
        spinner.setAdapter(roomList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sroom=room[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(),"請選擇內容",Toast.LENGTH_SHORT);
            }
        });

        //機車停車位選單
        final String[] parkingspace={"有","無"};
        final ArrayAdapter<String> parkingspaceList=new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                parkingspace);
        spinner1.setAdapter(parkingspaceList);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sparkingspace=parkingspace[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(),"請選擇內容",Toast.LENGTH_SHORT);
            }
        });

        //寵物選單
        final String[] pet={"可以養","不能養"};
        final ArrayAdapter<String> petList=new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                pet);
        spinner2.setAdapter(petList);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spet=pet[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(),"請選擇內容",Toast.LENGTH_SHORT);
            }
        });

        //水費
        final String[] WaterFee={"包水","不包水"};
        final ArrayAdapter<String> WaterFeeList=new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                WaterFee);
        spinner3.setAdapter(WaterFeeList);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                swaterfee=WaterFee[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(),"請選擇內容",Toast.LENGTH_SHORT);
            }
        });

        //電費
        final String[] ElectricityFee={"包電","不包電"};
        final ArrayAdapter<String> ElectricityFeeList=new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                ElectricityFee);
        spinner4.setAdapter(ElectricityFeeList);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectricityfee=ElectricityFee[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(),"請選擇內容",Toast.LENGTH_SHORT);
            }
        });

        //網路
        final String[] internet={"包網路","不包網路"};
        final ArrayAdapter<String> internetList=new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                internet);
        spinner5.setAdapter(internetList);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sinternet=internet[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(),"請選擇內容",Toast.LENGTH_SHORT);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                SS_Fragment_search_result fragment = new SS_Fragment_search_result();
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                        .replace(R.id.frame_search, fragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

                Bundle bundle=new Bundle();
                bundle.putString("room",sroom);
                bundle.putString("parkingspace",sparkingspace);
                bundle.putString("pet",spet);
                bundle.putString("waterfee",swaterfee);
                bundle.putString("electricityfee",selectricityfee);
                bundle.putString("internet",sinternet);
                fragment.setArguments(bundle);
            }
        });
    }
}
