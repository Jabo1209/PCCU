package com.example.pccu.Landlord_Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pccu.AdapterSearch;
import com.example.pccu.FirebaseBean;
import com.example.pccu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class LS_Fragment_houseinfo_list extends Fragment{

    private SwipeRefreshLayout laySwipe;
    private RecyclerView recyclerView;
    private ArrayList<FirebaseBean> arrayList=new ArrayList<>();
    private FirebaseFirestore db;
    private AdapterSearch adapterSearch;
    private FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lshouselist, container, false);

        arrayList=new ArrayList<>();
        initView(view);
        return view;
    }

    public void initView(View view){
        recyclerView=(RecyclerView)view.findViewById(R.id.houseinfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        laySwipe = (SwipeRefreshLayout) view.findViewById(R.id.laySwipe);
        laySwipe.setOnRefreshListener(onSwipeToRefresh);
        laySwipe.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

        user= FirebaseAuth.getInstance().getCurrentUser();
        db=FirebaseFirestore.getInstance();
        arrayList=new ArrayList<>();
        Query query=db.collection(user.getUid());
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
                        LS_Fragment_houseinfo fragment = new LS_Fragment_houseinfo();
                        manager.beginTransaction()
                                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                                        R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                                .replace(R.id.frame_lshouseinfo, fragment)
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
                        fragment.setArguments(bundle);
                    }
                });
            }
        });
    }

    private void research(){
        user= FirebaseAuth.getInstance().getCurrentUser();
        db=FirebaseFirestore.getInstance();
        arrayList=new ArrayList<>();
        Query query=db.collection(user.getUid());
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
                        LS_Fragment_houseinfo fragment = new LS_Fragment_houseinfo();
                        manager.beginTransaction()
                                .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                                        R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                                .replace(R.id.frame_lshouseinfo, fragment)
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
                        fragment.setArguments(bundle);
                    }
                });
            }
        });
    }

    //下拉更新頁面資料
    private OnRefreshListener onSwipeToRefresh = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            laySwipe.setRefreshing(true);
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    laySwipe.setRefreshing(false);
                    research();
                    Toast.makeText(getActivity().getApplicationContext(), "更新完成!", Toast.LENGTH_SHORT).show();
                }
            }, 3000);
        }
    };
}
