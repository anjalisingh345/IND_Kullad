package com.example.indiankullad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.indiankullad.Ui.ProductFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    RequestQueue requestQueue;
    TextInputEditText name,mobile,address,pass;
    Button login;
    String mobile1,pass1;
    ImageView show;
    boolean conn=false;

    String url = "https://androiddevlopment.000webhostapp.com/Kullad/Login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile = findViewById(R.id.edtmobile);
        pass = findViewById(R.id.edtPassword);
        login = findViewById(R.id.login);
        show = findViewById(R.id.show);

        ///show hide password

        if (conn==false) {
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    show.setImageResource(R.drawable.hide);
                    int start = pass.getSelectionStart();
                    int end = pass.getSelectionEnd();
                    pass.setTransformationMethod(null);
                    pass.setSelection(start, end);
                    conn = true;


                    if (conn == true) {
                        show.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                show.setImageResource(R.drawable.show);
                                int start = pass.getSelectionStart();
                                int end = pass.getSelectionEnd();
                                pass.setTransformationMethod(new PasswordTransformationMethod());;                                pass.setSelection(start, end);
                                conn = false;

                            }
                        });

                    }


                }
            });

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobile.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Enter Mobile No.", Toast.LENGTH_LONG).show();
                } else if (pass.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_LONG).show();
                } else {

                    //  insertdata();

                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Please wait.....");
                    progressDialog.show();
                    progressDialog.show();
                    mobile1=mobile.getText().toString().trim();
                    pass1=pass.getText().toString().trim();


                    StringRequest stringRequest = new StringRequest(Request
                            .Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    progressDialog.dismiss();

                                    Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();

                                    if(response.equals("Logged in successfully"))
                                    {
                                        progressDialog.dismiss();
                                        SharedPreferences sharedPreferences=getSharedPreferences("check",MODE_PRIVATE);
                                        SharedPreferences.Editor e;
                                        e=sharedPreferences.edit();
                                        e.putString("con","TRUE");
                                        e.putString("mobile",mobile1);
                                        e.commit();
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();


                                    }
                                    else
                                    {
                                        progressDialog.dismiss();
                                        mobile.setText("");
                                        pass.setText("");
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();

                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();

                            params.put("mobile",mobile.getText().toString());
                            params.put("password",pass.getText().toString());
                            return params;

                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                    requestQueue.add(stringRequest);
                }
            }
        });
    }


    public void signup(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

//        Intent intent = new Intent(Login.this,MainActivity.class);
//        startActivity(intent);

    }
