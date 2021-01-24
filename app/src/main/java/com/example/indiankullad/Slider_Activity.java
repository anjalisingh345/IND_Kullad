package com.example.indiankullad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class Slider_Activity extends PagerAdapter {


    List<Integer> list;
    //String[] text = {"Description one","Description two","Description Three"};

    public Slider_Activity(List<Integer> imagelist){

        this.list =imagelist;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_slider_,container,false);
        ImageView image = view.findViewById(R.id.imageView);
        //TextView text = view.findViewById(R.id.text);
        image.setImageResource(list.get(position));
        // text.setText(text.get[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}
