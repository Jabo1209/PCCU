package com.example.pccu.Landlord_Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pccu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class LS_Fragment_upload_picture extends Fragment {

    private static final int PICK_IMG = 1;
    private ArrayList<Uri> ImageList = new ArrayList<>();
    private int uploads = 0;
    private FirebaseFirestore db;
    private ProgressDialog progressDialog;
    int index = 0;
    TextView textView;
    Button send;
    ImageButton choose;
    String title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_upload_picture, container, false);

        Bundle bundle=getArguments();
        title=bundle.getString("title");

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("上傳中..........");
        textView = view.findViewById(R.id.text);
        choose = view.findViewById(R.id.select_btn);
        send = view.findViewById(R.id.upload);

        initView(view);
        return view;
    }

    public void initView(View view){

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //we will pick images
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, PICK_IMG);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("請稍後...........如果等太久請再按一次上傳按鈕");
                progressDialog.show();
                final StorageReference ImageFolder =  FirebaseStorage.getInstance().getReference().child(title);
                for (uploads=0; uploads < ImageList.size(); uploads++) {
                    Uri Image  = ImageList.get(uploads);
                    final StorageReference imagename = ImageFolder.child("image/"+ Image.getLastPathSegment());

                    imagename.putFile(ImageList.get(uploads)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = String.valueOf(uri);
                                    Log.i("Url", url);
                                    SendLink(url);
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMG) {
            if (resultCode == RESULT_OK) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();

                    int CurrentImageSelect = 0;

                    while (CurrentImageSelect < count) {
                        Uri imageuri = data.getClipData().getItemAt(CurrentImageSelect).getUri();
                        ImageList.add(imageuri);
                        CurrentImageSelect = CurrentImageSelect + 1;
                    }
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("你已經選擇了 "+ ImageList.size() +" 張照片" );
                    choose.setVisibility(View.GONE);
                }

            }

        }

    }

    private void SendLink(String url) {
        db = FirebaseFirestore.getInstance();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ImageLink",url);
        db.collection(title).document().set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                textView.setText("照片上傳成功");
                send.setVisibility(View.GONE);
                ImageList.clear();
            }
        });
    }
}

