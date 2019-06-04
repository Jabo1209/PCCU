package com.example.pccu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;



public class landlordsigninsuccess extends AppCompatActivity {

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
        FragmentList_homepage myFragment0 = new FragmentList_homepage();
        FragmentList_houseinfo myFragment1 = new FragmentList_houseinfo();
        FragmentList_upload myFragment2 = new FragmentList_upload();
        FragmentList_chateroom myFragment3 = new FragmentList_chateroom();
        FragmentList_menu myFragment4 = new FragmentList_menu();
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
