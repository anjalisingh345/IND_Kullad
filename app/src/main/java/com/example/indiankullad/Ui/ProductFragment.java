package com.example.indiankullad.Ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.indiankullad.EndPoints;
import com.example.indiankullad.Model;
import com.example.indiankullad.Product_Adapter;
import com.example.indiankullad.R;
import com.example.indiankullad.Slider_Activity;
import com.example.indiankullad.Util;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public  class ProductFragment extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    // String Url="https://anjaliandroid.000webhostapp.com/Fitnessgym/Showdata.php";;

    RequestQueue requestQueue;

    final List<Model> mydata = new ArrayList<>();
    RecyclerView rc;

    ViewPager viewPager;
    private   List<Integer> imagelist = new ArrayList<>();

    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 500;
    //delay in miliseconds before task is to be executed

    final long PERIOD_MS = 3000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.product_frag, container, false);

        rc = view.findViewById(R.id.recycler);
        // toolbar = findViewById(R.id.toolbar_actionbar);
        //back = findViewById(R.id.back);

        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(layoutManager);

//        viewPager =view.findViewById(R.id.bannerviewpager);
//
//        imagelist.add(R.drawable.kullad3);
//        imagelist.add(R.drawable.kullad2);
//        imagelist.add(R.drawable.kullad);
//
//        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
//        tabLayout.setupWithViewPager(viewPager, true);
//
//
//        Slider_Activity slider_activirty = new Slider_Activity(imagelist);
//        viewPager.setAdapter(slider_activirty);
//        final Handler handler = new Handler();
//        final  Runnable Update = new Runnable() {
//            @Override
//            public void run() {
//                if (currentPage==imagelist.size()){
//                    currentPage = 0;
//                }
//                viewPager.setCurrentItem(currentPage++,true);
//            }
//        };
//
//        timer = new Timer();
//        //This will crete a new thread
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        },DELAY_MS,PERIOD_MS);



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
                    //recreate();


                }
            });

            //show dialog
            dialog.show();
        }
        else {

            //for getting place information

            Util.showProgressDialog(getActivity(),false);
            requestQueue = Volley.newRequestQueue(getContext());
            StringRequest request = new StringRequest(Request.Method.POST,
                    EndPoints.show_api, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Util.dismisProgressDialog();
                    JSONArray jsonArray = null;
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        jsonArray = (JSONArray) jsonObject.get("data");

                        for (int i = 0; i < jsonArray.length(); i++)
                        {

                            JSONObject obj = jsonArray.getJSONObject(i);


//                            String[] separated = obj.getString("package").split(" ");
//                            int myNum = 0;

                            try {
//                                myNum = Integer.parseInt(separated[0]);
//                                Date c = Calendar.getInstance().getTime();
//                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
////                                String formattedDate = df.format(c);
//                                if (dateDiff(obj.getString("joining_date"),formattedDate)>30*myNum)
//                                {
                                String kullad_name = obj.getString("kullad_name");
                                //  String surname = obj.getString("surname");
                                String kullad_code = obj.getString("kullad_code");
                                String price = obj.getString("price");
                                String description = obj.getString("description");
                                //  String feepaid1=obj.getString("feepaid");
                                Model model = new Model();
                                model.setKullad_name(kullad_name);
                                model.setKullad_code(kullad_code);
                                model.setPrice(price);
                                model.setDescription(description);

                                mydata.add(model);






                            } catch(NumberFormatException nfe) {
                                Toast.makeText(getContext(),""+mydata.size(),Toast.LENGTH_LONG).show();


                            }




                        }
                    } catch (JSONException e) {
                        Util.dismisProgressDialog();
                        Toast.makeText(getContext(),"catch"+e.toString(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    Product_Adapter adapter = new Product_Adapter(getContext(), mydata);
                    rc.setAdapter(adapter);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Util.dismisProgressDialog();
                   // Log.d("hhh", error.getMessage());

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("action", "readAll");
                    return params;
                }
            };
            requestQueue.add(request);




        }


        return view;
    }
}