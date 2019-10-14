package com.example.pccu.LoginSuccess;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.pccu.Landlord_Fragment.LS_Fragment_chateroom;
import com.example.pccu.Landlord_Fragment.LS_Fragment_homepage;
import com.example.pccu.Landlord_Fragment.LS_Fragment_houseinfo_main;
import com.example.pccu.Landlord_Fragment.LS_Fragment_upload_main;
import com.example.pccu.More.FragmentMore_main;
import com.example.pccu.R;

import java.util.ArrayList;
import java.util.List;



public class LandlordSigninSuccess extends AppCompatActivity {

    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private int[] IconResID = {R.drawable.home1,R.drawable.rent,R.drawable.pencil,R.drawable.conference,R.drawable.menu1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlordsigninsuccess);

        //取消ActionBar
        getSupportActionBar().hide();

        myViewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        setViewPager();
        tabLayout.setupWithViewPager(myViewPager);
        setTabLayoutIcon();
    }
    public void setTabLayoutIcon(){
        for(int i =0; i < IconResID.length;i++){
            tabLayout.getTabAt(i).setIcon(IconResID[i]);
        }
    }

    private void setViewPager(){
        LS_Fragment_homepage myFragment0 = new LS_Fragment_homepage();
        LS_Fragment_houseinfo_main myFragment1 = new LS_Fragment_houseinfo_main();
        LS_Fragment_upload_main myFragment2 = new LS_Fragment_upload_main();
        LS_Fragment_chateroom myFragment3 = new LS_Fragment_chateroom();
        FragmentMore_main myFragment4 = new FragmentMore_main();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(myFragment0);
        fragmentList.add(myFragment1);
        fragmentList.add(myFragment2);
        fragmentList.add(myFragment3);
        fragmentList.add(myFragment4);


        ViewPagerFragmentAdapter myFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragmentList);
        myViewPager.setAdapter(myFragmentAdapter);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.ECLAIR) {
                event.startTracking();
            } else {
                onBackPressed();
            }
        }
        return false;
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }
}
