package com.example.indiankullad.Ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
import com.example.indiankullad.Util;

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
                //    recreate();


                }


            });


            //show dialog
            dialog.show();
        } else {

            //for getting place information

            Util.showProgressDialog(getActivity(), false);
            requestQueue = Volley.newRequestQueue(getActivity());
            StringRequest request = new StringRequest(Request.Method.POST,
                    EndPoints.REGISTER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Util.dismisProgressDialog();
                    JSONArray jsonArray = null;
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        jsonArray = (JSONArray) jsonObject.get("data");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject obj = jsonArray.getJSONObject(i);


//                            String[] separated = obj.getString("package").split(" ");
//                            int myNum = 0;

                            try {
//                                myNum = Integer.parseInt(separated[0]);
//                                Date c = Calendar.getInstance().getTime();
//                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
//                                String formattedDate = df.format(c);
                                 String kullad_name = obj.getString("kullad_name");
                                    //  String surname = obj.getString("surname");
                                    String kullad_code = obj.getString("kullad_code");
                                    String price = obj.getString("price");
                                    String des = obj.getString("des");
                                    String img=obj.getString("feepaid");
                                    Model model = new Model();
                                    model.setKullad_name(kullad_name);
                                    model.setKullad_code(kullad_code);
                                    model.setPrice(price);
                                    model.setDescription(des);
                                    model.setImage(dob);
                                    model.setGender(gender);
                                    model.setAddress(address);
                                    model.setJoindate(joindate);
                                    model.setMid(mid);
                                    model.setContact(contact);
                                    model.setTotalfee(totalfee);
                                    //model.setGettoknow(gettoknow);
                                    model.setImage(img);
                                    model.setFeepaid(feepaid);

                                    mydata.add(model);




                            } catch (NumberFormatException nfe) {

                            }


                        }
                    } catch (JSONException e) {
                        Util.dismisProgressDialog();
                        Toast.makeText(Duration_Recycler.this, "catch" + e.toString(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    Duration_Adapter adapter = new Duration_Adapter(Duration_Recycler
                            .this, mydata);
                    rc.setAdapter(adapter);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Util.dismisProgressDialog();
                    Log.d("hhh", error.getMessage());

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