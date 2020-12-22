package com.example.indiankullad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.indiankullad.Ui.ProductFragment;
import com.example.indiankullad.Ui.QuantityFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);



        // viewpager supporte


        ViewpagerAdapter view = new ViewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(view);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        view.addfragment(new ProductFragment(),"Product");
        view.addfragment(new OrderFragment(),"Order");
       // view.addfragment(new QuantityFragment(),"Quantity");



        viewPager.setAdapter(view);
        tabLayout.setupWithViewPager(viewPager);



    }
    class ViewpagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewpagerAdapter(FragmentManager fm){
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }




        public void addfragment(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }}}