package com.example.pccu.Student_Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.pccu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SS_Fragment_search extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<FirebaseBean> arrayList=new ArrayList<>();
    private FirebaseFirestore db;
    private ImageButton search;
    private AdapterSearch adapterSearch;
    private EditText editText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        search=(ImageButton)view.findViewById(R.id.search_btn);
        recyclerView=(RecyclerView)view.findViewById(R.id.houseinfo);
        editText=(EditText)view.findViewById(R.id.keyword);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db=FirebaseFirestore.getInstance();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String keyword=editText.getText().toString();
                arrayList=new ArrayList<>();
                Query query=db.collection("houseinfo").whereEqualTo("Title",keyword);
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot querySnapshot = task.isSuccessful() ? task.getResult() : null;
                        for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                            arrayList.add(documentSnapshot.toObject(FirebaseBean.class));
                        }
                    }
                }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        adapterSearch=new AdapterSearch(arrayList,getActivity().getApplicationContext());
                        recyclerView.setAdapter(adapterSearch);
                    }
                });
            }
        });
    }
}


