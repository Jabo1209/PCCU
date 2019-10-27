package com.example.pccu.Student_Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pccu.FirebaseBean;
import com.example.pccu.R;
import com.example.pccu.ViewPagerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SS_Fragment_search_houseinfo extends Fragment {

    private String title,room,parkingspace,pet,money,address,waterfee,electricityfee,internet,remark;
    private TextView htitle,hroom,hparkingspace,hpet,hmoney,haddress,hwaterfee,helectricityfee,hinternet,hremark;
    private Button back;
    String sroom,sparkingspace,spet,swaterfee,selectricityfee,sinternet;
    private FirebaseFirestore db;
    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private ImageView[] dots;
    private int dotscount;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<FirebaseBean> imageList1=new ArrayList<>();

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

        imageList1=new ArrayList<>();

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
        viewPager=(ViewPager)v.findViewById(R.id.picture1);
        sliderDotspanel = (LinearLayout) v.findViewById(R.id.sliderdots1);
        back=(Button)v.findViewById(R.id.back);

        //照片
        db= FirebaseFirestore.getInstance();
        imageList1=new ArrayList<>();
        Query query=db.collection(title);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                QuerySnapshot querySnapshot = task.isSuccessful() ? task.getResult() : null;
                for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                    imageList1.add(documentSnapshot.toObject(FirebaseBean.class));
                }
            }
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                viewPagerAdapter=new ViewPagerAdapter(imageList1,getActivity().getApplicationContext());
                viewPager.setAdapter(viewPagerAdapter);
                dotscount=imageList1.size();
                Log.i("長度", String.valueOf(imageList1.size()));
                dots = new ImageView[dotscount];

                for(int i = 0; i < dotscount; i++){

                    dots[i] = new ImageView(getActivity());
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    params.setMargins(8, 0, 8, 0);

                    sliderDotspanel.addView(dots[i], params);

                }

                dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));

                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        for(int i = 0; i< dotscount; i++){
                            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));
                        }

                        dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }
        });


        //資料設定
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

        //返回列表
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
