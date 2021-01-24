package com.example.indiankullad;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    TextInputEditText name,mobile,address,pass;
    Button submit;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.edtname);
        mobile = findViewById(R.id.edtmobile);
        address = findViewById(R.id.edtaddress);
        pass = findViewById(R.id.edtPassword);
        submit = findViewById(R.id.signupbutton);
    }

    public void signup(View view) {

        //initialize connectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get active network info

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //check network status


        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            //when internet is inactive

            //initialize dialog

            Dialog dialog = new Dialog(Register.this);
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
                    //  recreate();


                }
            });

            //show dialog
            dialog.show();
        } else {

//


            if (name.getText().toString().equals("")) {
                Toast.makeText(Register.this, "Enter kullad name", Toast.LENGTH_LONG).show();
            } else if (mobile.getText().toString().equals("")) {
                Toast.makeText(Register.this, "Enter kullad code", Toast.LENGTH_LONG).show();
            } else if (address.getText().toString().equals("")) {
                Toast.makeText(Register.this, "Enter price", Toast.LENGTH_LONG).show();
            } else if (pass.getText().toString().equals("")) {
                Toast.makeText(Register.this, "Enter descriptions", Toast.LENGTH_LONG).show();
            }
//
//                    //gender radiobutton checked
//                    else if (checkedId == -1){
//
//                        //No radio button are checked
//
//                        Message.message(getActivity(),"please select gender");
//
//                    }

            else {

                insertdata();
            }
        }
    }
    private void insertdata() {

        Util.showProgressDialog(Register.this,false);
        VolleyMultipartRequest volleyMultipartRequest = new
                VolleyMultipartRequest(Request.Method.POST, EndPoints.REGISTER,
                        new Response.Listener<NetworkResponse>() {
                            @Override
                            public void onResponse(NetworkResponse response) {
                                Util.dismisProgressDialog();

                                Toast.makeText(Register.this,"Data Inserted", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Register.this, MainActivity.class);
                                startActivity(intent);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Util.dismisProgressDialog();
                                Toast.makeText(Register.this, "error"+error.toString(), Toast.LENGTH_SHORT).show();
                                Log.d("mynewerror",error.toString());
                            }
                        }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name.getText().toString().trim());
                        params.put("mobile", mobile.getText().toString().trim());
                        params.put("address", address.getText().toString().trim());
                        params.put("password", pass.getText().toString().trim().trim());
                        return params;


                    }
                };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(volleyMultipartRequest);

    }
    }