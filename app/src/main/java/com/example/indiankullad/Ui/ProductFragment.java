package com.example.indiankullad.Ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indiankullad.R;

public  class ProductFragment extends Fragment {

    RecyclerView rc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.product_frag, container, false);

        rc = view.findViewById(R.id.recycler);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;


    }
}