package com.example.pccu.Landlord_Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LS_Fragment_houseinfo extends Fragment {

    private String title,room,parkingspace,pet,money,address,waterfee,electricityfee,internet,remark;
    private TextView htitle,hroom,hparkingspace,hpet,hmoney,haddress,hwaterfee,helectricityfee,hinternet,hremark;
    private Button back,delete;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private ImageView[] dots;
    private int dotscount;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<FirebaseBean> imageList=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lshouseinfo, container, false);


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

        imageList=new ArrayList<>();

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
        delete=(Button)v.findViewById(R.id.delete);
        viewPager=(ViewPager)v.findViewById(R.id.picture);
        sliderDotspanel = (LinearLayout) v.findViewById(R.id.sliderdots);


        //照片
        db= FirebaseFirestore.getInstance();
        imageList=new ArrayList<>();
        Query query=db.collection(title);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                QuerySnapshot querySnapshot = task.isSuccessful() ? task.getResult() : null;
                for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                    imageList.add(documentSnapshot.toObject(FirebaseBean.class));
                }
            }
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                viewPagerAdapter=new ViewPagerAdapter(imageList,getActivity().getApplicationContext());
                viewPager.setAdapter(viewPagerAdapter);
                dotscount=imageList.size();
                Log.i("長度", String.valueOf(imageList.size()));
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
                LS_Fragment_houseinfo_list fragment = new LS_Fragment_houseinfo_list();
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right,
                                R.anim.enter_right_to_left, R.anim.exit_right_to_left)
                        .replace(R.id.frame_lshouseinfo, fragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });

        //刪除資料
        user= FirebaseAuth.getInstance().getCurrentUser();
        db= FirebaseFirestore.getInstance();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//刪除確認
                AlertDialog.Builder ad=new AlertDialog.Builder(getActivity());
                ad.setTitle("刪除");
                ad.setMessage("確定要刪除此筆房屋資訊嗎?");
                ad.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//確認刪除
                        db.collection(user.getUid()).document(title)
                                .delete();

                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        LS_Fragment_houseinfo_list fragment = new LS_Fragment_houseinfo_list();
                        manager.beginTransaction()
                                .setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right,
                                        R.anim.enter_right_to_left, R.anim.exit_right_to_left)
                                .replace(R.id.frame_lshouseinfo, fragment)
                                .addToBackStack(null)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();
                    }
                });
                ad.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //不刪除不執行任何動作
                    }
                });
                ad.show();
            }
        });
    }
}
