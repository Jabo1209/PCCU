package com.example.pccu.Landlord_Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pccu.R;
import com.example.pccu.UploadListAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class LS_FragmentList_upload extends Fragment {

    private Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6;
    private EditText et_title,et_money,et_address,et_remark;
    private Button bt_send;
    private String db_title,db_room,db_parkingspace,db_pet,db_money,db_address,db_waterfee,db_electricityfee,db_internet,db_remark;
    private FirebaseFirestore db;
    private FirebaseUser user;

    private static final int RESULT_LOAD_IMAGE = 1;

    private Button mSelectBtn;
    private RecyclerView mUploadList;

    private List<String> fileNameList;
    private List<String> fileDoneList;

    private UploadListAdapter uploadListAdapter;

    private StorageReference mStorage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_upload, container, false);

        initView(view);
        initData();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK){

            if(data.getClipData() != null){

                int totalItemsSelected = data.getClipData().getItemCount();

                for(int i = 0; i < totalItemsSelected; i++){

                    Uri fileUri = data.getClipData().getItemAt(i).getUri();

                    String fileName = getFileName(fileUri);

                    db_title=et_title.getText().toString();

                    fileNameList.add(fileName);
                    fileDoneList.add("uploading");
                    uploadListAdapter.notifyDataSetChanged();

                    StorageReference fileToUpload = mStorage.child(db_title).child(fileName);

                    final int finalI = i;
                    fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            fileDoneList.remove(finalI);
                            fileDoneList.add(finalI, "done");

                            uploadListAdapter.notifyDataSetChanged();

                        }
                    });

                }

                //Toast.makeText(MainActivity.this, "Selected Multiple Files", Toast.LENGTH_SHORT).show();

            } else if (data.getData() != null){

                Toast.makeText(getActivity(), "Selected Single File", Toast.LENGTH_SHORT).show();

            }

        }
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void initData(){
        mStorage = FirebaseStorage.getInstance().getReference();
    }

    private void initView(View view){
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
        bt_send=(Button)view.findViewById(R.id.send);

        mSelectBtn = (Button)view.findViewById(R.id.select_btn);
        mUploadList = (RecyclerView)view.findViewById(R.id.upload_list);


        fileNameList = new ArrayList<>();
        fileDoneList = new ArrayList<>();

        uploadListAdapter = new UploadListAdapter(fileNameList, fileDoneList);

        //RecyclerView

        mUploadList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mUploadList.setHasFixedSize(true);
        mUploadList.setAdapter(uploadListAdapter);

        mSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"選擇照片"), RESULT_LOAD_IMAGE);
            }
        });

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
                        .document(user.getUid())
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
                Toast.makeText(getActivity(),"上傳成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

