package com.example.pccu.Student_Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pccu.AdapterSearch;
import com.example.pccu.FirebaseBean;
import com.example.pccu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SS_Fragment_search_houselist extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<FirebaseBean> arrayList=new ArrayList<>();
    private FirebaseFirestore db;
    private String room,parkingspace,pet,waterfee,electricityfee,internet;
    private AdapterSearch adapterSearch;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_houselist, container, false);

        arrayList=new ArrayList<>();

        Bundle bundle=getArguments();
        room=bundle.getString("room");
        parkingspace=bundle.getString("parkingspace");
        pet=bundle.getString("pet");
        waterfee=bundle.getString("waterfee");
        electricityfee=bundle.getString("electricityfee");
        internet=bundle.getString("internet");


        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView=(RecyclerView)view.findViewById(R.id.houseinfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db=FirebaseFirestore.getInstance();
        arrayList=new ArrayList<>();
        Query query=db.collection("houseinfo")
                .whereEqualTo("Room",room)
                .whereEqualTo("Parkspace",parkingspace)
                .whereEqualTo("Pet",pet)
                .whereEqualTo("WaterFee",waterfee)
                .whereEqualTo("ElectricityFee",electricityfee)
                .whereEqualTo("Internet",internet);
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
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
                adapterSearch.setOnItemClickListener(new AdapterSearch.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, FirebaseBean data) {
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        SS_Fragment_search_houseinfo fragment = new SS_Fragment_search_houseinfo();
                        manager.beginTransaction()
                                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                                        R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                                .replace(R.id.frame_search, fragment)
                                .addToBackStack(null)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();

                        Bundle bundle=new Bundle();
                        bundle.putString("title",data.getTitle());
                        bundle.putString("room",data.getRoom());
                        bundle.putString("parkingspace",data.getParkspace());
                        bundle.putString("pet",data.getPet());
                        bundle.putString("money",data.getMoney());
                        bundle.putString("address",data.getAddress());
                        bundle.putString("waterfee",data.getWaterFee());
                        bundle.putString("electricityfee",data.getElectricityFee());
                        bundle.putString("internet",data.getInternet());
                        bundle.putString("remark",data.getRemark());


                        bundle.putString("room",room);
                        bundle.putString("parkingspace",parkingspace);
                        bundle.putString("pet",pet);
                        bundle.putString("waterfee",waterfee);
                        bundle.putString("electricityfee",electricityfee);
                        bundle.putString("internet",internet);
                        fragment.setArguments(bundle);
                    }
                });
            }
        });
    }
}


