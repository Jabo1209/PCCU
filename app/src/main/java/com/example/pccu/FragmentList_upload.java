package com.example.pccu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class FragmentList_upload extends Fragment {

    Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6;
    EditText et_title,et_money,et_address,et_remark;
    Button picture_upload,bt_send;
    String db_title,db_room,db_parkingspace,db_pet,db_money,db_address,db_waterfee,db_electricityfee,db_internet,db_remark;
    FirebaseFirestore db;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_upload, container, false);

        spinner1=(Spinner)view.findViewById(R.id.spinner1);
        spinner2=(Spinner)view.findViewById(R.id.spinner2);
        spinner3=(Spinner)view.findViewById(R.id.spinner3);
        spinner4=(Spinner)view.findViewById(R.id.spinner4);
        spinner5=(Spinner)view.findViewById(R.id.spinner5);
        spinner6=(Spinner)view.findViewById(R.id.spinner6);
        et_title=(EditText)view.findViewById(R.id.title);
        et_money=(EditText)view.findViewById(R.id.money);
        et_address=(EditText)view.findViewById(R.id.address);
        et_remark=(EditText)view.findViewById(R.id.remark);
        picture_upload=(Button)view.findViewById(R.id.picture);
        bt_send=(Button)view.findViewById(R.id.send);

        //房型選單
        final String[] room={"單人房","雙人房","三人房","四人房","家庭式","其他"};
        ArrayAdapter<String> roomList = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                room);
        spinner1.setAdapter(roomList);
        db_room=spinner1.getSelectedItem().toString();

        //機車停車位選單
        final String[] parkingspace={"有","無"};
        ArrayAdapter<String> parkingspaceList=new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                parkingspace);
        spinner2.setAdapter(parkingspaceList);
        db_parkingspace=spinner2.getSelectedItem().toString();

        //寵物選單
        final String[] pet={"可以養","不能養"};
        ArrayAdapter<String> petList=new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                pet);
        spinner3.setAdapter(petList);
        db_pet=spinner3.getSelectedItem().toString();

        //水費
        final String[] WaterFee={"包水","不包水"};
        ArrayAdapter<String> WaterFeeList=new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                WaterFee);
        spinner4.setAdapter(WaterFeeList);
        db_waterfee=spinner4.getSelectedItem().toString();

        //電費
        final String[] ElectricityFee={"包電","不包電"};
        ArrayAdapter<String> ElectricityFeeList=new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                ElectricityFee);
        spinner5.setAdapter(ElectricityFeeList);
        db_electricityfee=spinner5.getSelectedItem().toString();

        //網路
        final String[] internet={"包網路","不包網路"};
        ArrayAdapter<String> internetList=new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                internet);
        spinner6.setAdapter(internetList);
        db_internet=spinner6.getSelectedItem().toString();

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseFirestore.getInstance();
                user= FirebaseAuth.getInstance().getCurrentUser();

                db_title=et_title.getText().toString();
                db_money=et_money.getText().toString();
                db_address=et_address.getText().toString();
                db_remark=et_remark.getText().toString();

                Map<String, Object> upload = new HashMap<>();
                upload.put("Tittle",db_title);
                upload.put("Room",db_room);
                upload.put("Parkspace",db_parkingspace);
                upload.put("Pet",db_pet);
                upload.put("Money",db_money);
                upload.put("Address",db_address);
                upload.put("WaterFee",db_waterfee);
                upload.put("ElectricityFee",db_electricityfee);
                upload.put("Internet",db_internet);
                upload.put("Remark",db_remark);
                upload.put("Date",new Date());

                db.collection("houseinfo")
                        .document(user.getUid().toString())
                        .set(upload)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.i("資料庫新增", "新增成功");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("資料庫新增", "新增失敗");
                            }
                        });
            }
        });
        return view;
    }
}

