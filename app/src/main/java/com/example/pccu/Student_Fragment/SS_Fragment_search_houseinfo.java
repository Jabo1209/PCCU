package com.example.pccu.Student_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pccu.R;

public class SS_Fragment_search_houseinfo extends Fragment {

    private String title,room,parkingspace,pet,money,address,waterfee,electricityfee,internet,remark;
    private TextView htitle,hroom,hparkingspace,hpet,hmoney,haddress,hwaterfee,helectricityfee,hinternet,hremark;
    private Button back;
    String sroom,sparkingspace,spet,swaterfee,selectricityfee,sinternet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_houseinfo, container, false);

        Bundle bundle=getArguments();
        title=bundle.getString("title");
        room=bundle.getString("room");
        parkingspace=bundle.getString("parkingspace");
        pet=bundle.getString("pet");
        money=bundle.getString("money");
        address=bundle.getString("address");
        waterfee=bundle.getString("waterfee");
        electricityfee=bundle.getString("electricityfee");
        internet=bundle.getString("internet");
        remark=bundle.getString("remark");


        sroom=bundle.getString("room");
        sparkingspace=bundle.getString("parkingspace");
        spet=bundle.getString("pet");
        swaterfee=bundle.getString("waterfee");
        selectricityfee=bundle.getString("electricityfee");
        sinternet=bundle.getString("internet");


        initView(view);
        return view;
    }

    public void initView(View v){
        htitle=(TextView)v.findViewById(R.id.title);
        hroom=(TextView)v.findViewById(R.id.room);
        hparkingspace=(TextView)v.findViewById(R.id.parkspace);
        hpet=(TextView)v.findViewById(R.id.pet);
        hmoney=(TextView)v.findViewById(R.id.money);
        haddress=(TextView)v.findViewById(R.id.address);
        hwaterfee=(TextView)v.findViewById(R.id.waterfee);
        helectricityfee=(TextView)v.findViewById(R.id.electricityfee);
        hinternet=(TextView)v.findViewById(R.id.internet);
        hremark=(TextView)v.findViewById(R.id.remark);

        back=(Button)v.findViewById(R.id.back);


        htitle.setText(title);
        hroom.setText(room);
        hparkingspace.setText(parkingspace);
        hpet.setText(pet);
        hmoney.setText(money);
        haddress.setText(address);
        hwaterfee.setText(waterfee);
        helectricityfee.setText(electricityfee);
        hinternet.setText(internet);
        hremark.setText(remark);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                SS_Fragment_search_houselist fragment = new SS_Fragment_search_houselist();
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right,
                                R.anim.enter_right_to_left, R.anim.exit_right_to_left)
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
