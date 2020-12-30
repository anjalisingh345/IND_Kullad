package com.example.indiankullad.Ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.indiankullad.Model;
import com.example.indiankullad.Product_Adapter;
import com.example.indiankullad.R;

import java.util.ArrayList;
import java.util.List;

public  class ProductFragment extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    // String Url="https://anjaliandroid.000webhostapp.com/Fitnessgym/Showdata.php";;

    RequestQueue requestQueue;

    final List<Model> mydata = new ArrayList<>();
    RecyclerView rc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.product_frag, container, false);

        rc = view.findViewById(R.id.recycler);
//

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(layoutManager);


        //initialize connectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get active network info

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //check network status
        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            //when internet is inactive

            //initialize dialog

            Dialog dialog = new Dialog(getContext());
            //set content view

            dialog.setContentView(R.layout.alert_dialog);
            // set outside touch

            dialog.setCanceledOnTouchOutside(false);

            //set dialog width and height
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);

            //set transparent background

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            //set animation

            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

            //Initialize dialog variable

            Button btnTryAgain = dialog.findViewById(R.id.bt_try);

            btnTryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //call recreate method
                    recreate();


                }


            });


            //show dialog
            dialog.show();
        } else {


        }


    }