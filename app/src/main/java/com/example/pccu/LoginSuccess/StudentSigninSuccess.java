package com.example.pccu.LoginSuccess;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.pccu.More.FragmentMore_main;
import com.example.pccu.R;
import com.example.pccu.Student_Fragment.SS_Fragment_chateroom;
import com.example.pccu.Student_Fragment.SS_Fragment_homepage;
import com.example.pccu.Student_Fragment.SS_Fragment_search_main;

import java.util.ArrayList;
import java.util.List;



public class StudentSigninSuccess extends AppCompatActivity {

    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private int[] IconResID = {R.drawable.home1,R.drawable.search,R.drawable.conference,R.drawable.menu1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentsigninsuccess);

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
        SS_Fragment_homepage myFragment0 = new SS_Fragment_homepage();
        SS_Fragment_search_main myFragment1 = new SS_Fragment_search_main();
        SS_Fragment_chateroom myFragment2 = new SS_Fragment_chateroom();
        FragmentMore_main myFragment3 = new FragmentMore_main();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(myFragment0);
        fragmentList.add(myFragment1);
        fragmentList.add(myFragment2);
        fragmentList.add(myFragment3);


        ViewPagerFragmentAdapter myFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragmentList);
        myViewPager.setAdapter(myFragmentAdapter);
    }
}
