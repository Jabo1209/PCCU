package com.example.pccu.LoginSuccess;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.pccu.Landlord_Fragment.LS_FragmentList_chateroom;
import com.example.pccu.Landlord_Fragment.LS_FragmentList_homepage;
import com.example.pccu.Landlord_Fragment.LS_FragmentList_houseinfo;
import com.example.pccu.Landlord_Fragment.LS_FragmentList_menu;
import com.example.pccu.Landlord_Fragment.LS_FragmentList_upload;
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
        LS_FragmentList_homepage myFragment0 = new LS_FragmentList_homepage();
        LS_FragmentList_houseinfo myFragment1 = new LS_FragmentList_houseinfo();
        LS_FragmentList_upload myFragment2 = new LS_FragmentList_upload();
        LS_FragmentList_chateroom myFragment3 = new LS_FragmentList_chateroom();
        LS_FragmentList_menu myFragment4 = new LS_FragmentList_menu();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(myFragment0);
        fragmentList.add(myFragment1);
        fragmentList.add(myFragment2);
        fragmentList.add(myFragment3);
        fragmentList.add(myFragment4);


        ViewPagerFragmentAdapter myFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragmentList);
        myViewPager.setAdapter(myFragmentAdapter);
    }
}
