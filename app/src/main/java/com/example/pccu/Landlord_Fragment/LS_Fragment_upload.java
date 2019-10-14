package com.example.pccu.Landlord_Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pccu.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class LS_Fragment_upload extends Fragment {

    private Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6;
    private EditText et_title,et_money,et_address,et_remark;
    private Button nextStepBtn;
    private String db_title,db_room,db_parkingspace,db_pet,db_money,db_address,db_waterfee,db_electricityfee,db_internet,db_remark;
    private FirebaseFirestore db;
    private FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_upload, container, false);

        initView(view);
        return view;
    }

    private void initView(final View view){
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
        nextStepBtn=(Button)view.findViewById(R.id.next_btn);


        //房型選單
        final String[] room={"單人房","雙人房","三人房","四人房","家庭式","其他"};
        final ArrayAdapter<String> roomList = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                room);
        spinner1.setAdapter(roomList);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                db_room=room[position];
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
        spinner2.setAdapter(parkingspaceList);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                db_parkingspace=parkingspace[position];
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
        spinner3.setAdapter(petList);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                db_pet=pet[position];
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
        spinner4.setAdapter(WaterFeeList);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                db_waterfee=WaterFee[position];
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
        spinner5.setAdapter(ElectricityFeeList);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                db_electricityfee=ElectricityFee[position];
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
        spinner6.setAdapter(internetList);
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                db_internet=internet[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(),"請選擇內容",Toast.LENGTH_SHORT);
            }
        });

        //執行上傳房屋資訊到資料庫&切換到上傳照片
        nextStepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseFirestore.getInstance();
                user= FirebaseAuth.getInstance().getCurrentUser();

                db_title=et_title.getText().toString();
                db_money=et_money.getText().toString();
                db_address=et_address.getText().toString();
                db_remark=et_remark.getText().toString();


                Map<String, Object> upload = new HashMap<>();

                upload.put("Title",db_title);
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

                //上傳房屋資訊到房屋資料庫
                db.collection("houseinfo")
                        .document(db_title)
                        .set(upload)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.i("房屋資料庫新增", "新增成功");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("房屋資料庫新增", "新增失敗");
                            }
                        });
                //上傳房屋資訊到房東個人資料庫
                db.collection(user.getUid())
                        .document(db_title)
                        .set(upload)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.i("房東資料庫新增", "新增成功");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("房東資料庫新增", "新增失敗");
                            }
                        });

                Toast.makeText(getActivity(),"上傳成功", Toast.LENGTH_SHORT).show();


                if (et_title.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "請輸入標題", Toast.LENGTH_SHORT).show();
                } else {
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    LS_Fragment_upload_picture fragment = new LS_Fragment_upload_picture();

                    manager.beginTransaction()
                            .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                                    R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                            .replace(R.id.frame1, fragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();

                    Bundle bundle = new Bundle();
                    bundle.putString("title", et_title.getText().toString());
                    fragment.setArguments(bundle);
                }

                et_title.setText("");
                et_money.setText("");
                et_address.setText("");
                et_remark.setText("");

                spinner1.setAdapter(roomList);
                spinner2.setAdapter(parkingspaceList);
                spinner3.setAdapter(petList);
                spinner4.setAdapter(WaterFeeList);
                spinner5.setAdapter(ElectricityFeeList);
                spinner6.setAdapter(internetList);
            }
        });
    }
}

