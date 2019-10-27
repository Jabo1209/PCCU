package com.example.pccu;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<FirebaseBean> firebaseBeans;

    public ViewPagerAdapter(ArrayList<FirebaseBean> imageList, Context context) {
        this.context = context;
        firebaseBeans= imageList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return firebaseBeans.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout,container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.get().load(firebaseBeans.get(position).getImageLink()).into(imageView);


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}