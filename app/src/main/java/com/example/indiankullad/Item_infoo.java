package com.example.indiankullad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.HashMap;
import java.util.Map;

import me.himanshusoni.quantityview.QuantityView;

public class Item_infoo extends AppCompatActivity implements QuantityView.OnQuantityChangeListener {
    TextView order,quantity;
    AlertDialog.Builder builder;
    private int pricePerProduct = 180;
    RequestQueue requestQueue;
    EasyFlipView easyFlipView;

    TextView k_name,k_code,price,box_capacity,address,contact,name;
    ImageView image,edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_infoo);
        order = findViewById(R.id.order);
        quantity = findViewById(R.id.quantity);
        k_name = findViewById(R.id.kname);
        k_code = findViewById(R.id.kcode);
        price = findViewById(R.id.price2);
        box_capacity = findViewById(R.id.box_capa);
        address = findViewById(R.id.address2);
        image = findViewById(R.id.kullad_img);
        contact = findViewById(R.id.contact1);
        name = findViewById(R.id.name2);
        edit = findViewById(R.id.edit_profile);
        easyFlipView = findViewById(R.id.easyflip);

     //   image.setRotationY(180); //horizontal




        builder = new AlertDialog.Builder(this);
/// quantity
        final QuantityView quantityViewDefault = (QuantityView) findViewById(R.id.quantityView_custom_1);
        quantityViewDefault.setOnQuantityChangeListener(this);
        quantityViewDefault.setQuantityClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Item_infoo.this);
                builder.setTitle("Change Quantity");

                View inflate = LayoutInflater.from(Item_infoo.this).inflate(R.layout.custom_quantity_dailog, null, false);
                final EditText et = (EditText) inflate.findViewById(R.id.et_qty);
                final TextView tvPrice = (TextView) inflate.findViewById(R.id.tv_price);
                final TextView tvTotal = (TextView) inflate.findViewById(R.id.tv_total);

                et.setText(String.valueOf(quantityViewDefault.getQuantity()));
                tvPrice.setText(" " + pricePerProduct);
                tvTotal.setText(" " + quantityViewDefault.getQuantity() * pricePerProduct);


                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (TextUtils.isEmpty(s)) return;
                        if (QuantityView.isValidNumber(s.toString())) {
                            int intNewQuantity = Integer.parseInt(s.toString());
                            tvTotal.setText("$ " + intNewQuantity * pricePerProduct);
                        } else {
                            Toast.makeText(Item_infoo.this, "Enter valid integer", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                builder.setView(inflate);
                builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newQuantity = et.getText().toString();
                        if (TextUtils.isEmpty(newQuantity)) return;

                        int intNewQuantity = Integer.parseInt(newQuantity);

                        quantityViewDefault.setQuantity(intNewQuantity);
                    }
                }).setNegativeButton("Cancel", null);
                builder.show();
            }
        });

        ///custom_ address edit
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Item_infoo.this);
                builder.setTitle("Edit Profile");

                DialogFragment  dialogFragment = new Edit_Profile();


//                View inflate = LayoutInflater.from(Item_infoo.this).inflate(R.layout.custom_quantity_dailog, null, false);
//                final EditText name = (EditText) inflate.findViewById(R.id.edt_name);
              //  final EditText mobile1 = (EditText) inflate.findViewById(R.id.edt_mobile);
               // final EditText address1 = (EditText) inflate.findViewById(R.id.edt_address);


                Toast.makeText(Item_infoo.this,"Profile",Toast.LENGTH_LONG).show();

            }

        });
        ///easy flip
        easyFlipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView easyFlipView, EasyFlipView.FlipState newCurrentSide) {

            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Item_infoo.this);
                builder.setTitle("Change Quantity");

                View inflate = LayoutInflater.from(Item_infoo.this).inflate(R.layout
                        .custom_quantity_dailog, null, false);
                final EditText et = (EditText) inflate.findViewById(R.id.et_qty);
                final TextView tvPrice = (TextView) inflate.findViewById(R.id.tv_price);
                final TextView tvTotal = (TextView) inflate.findViewById(R.id.tv_total);

                et.setText(String.valueOf(quantityViewDefault.getQuantity()));
                tvPrice.setText("$ " + pricePerProduct);
                tvTotal.setText("$ " + quantityViewDefault.getQuantity() * pricePerProduct);


                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (TextUtils.isEmpty(s)) return;
                        if (QuantityView.isValidNumber(s.toString())) {
                            int intNewQuantity = Integer.parseInt(s.toString());
                            tvTotal.setText("$ " + intNewQuantity * pricePerProduct);
                        } else {
                            Toast.makeText(Item_infoo.this, "Enter valid integer", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                builder.setView(inflate);
                builder.setPositiveButton("Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newQuantity = et.getText().toString();
                        if (TextUtils.isEmpty(newQuantity)) return;

                        int intNewQuantity = Integer.parseInt(newQuantity);

                        quantityViewDefault.setQuantity(intNewQuantity);

                        //initialize connectivityManager
                        ConnectivityManager connectivityManager = (ConnectivityManager)
                                getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

                        //Get active network info

                        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                        //check network status


                        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
                            //when internet is inactive

                            //initialize dialog

                            Dialog dialog1 = new Dialog(Item_infoo.this);
                            //set content view

                            dialog1.setContentView(R.layout.alert_dialog);
                            // set outside touch

                            dialog1.setCanceledOnTouchOutside(false);

                            //set dialog width and height
                            dialog1.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                                    WindowManager.LayoutParams.WRAP_CONTENT);

                            //set transparent background

                            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                            //set animation

                            dialog1.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                            //Initialize dialog variable

                            Button btnTryAgain = dialog1.findViewById(R.id.bt_try);

                            btnTryAgain.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //call recreate method
                                    //  recreate();


                                }
                            });

                            //show dialog
                            dialog1.show();
                        } else {

//


                            if (k_name.getText().toString().equals("")) {
                                Toast.makeText(Item_infoo.this, "Enter kullad name", Toast.LENGTH_LONG).show();
                            } else if (k_code.getText().toString().equals("")) {
                                Toast.makeText(Item_infoo.this, "Enter kullad code", Toast.LENGTH_LONG).show();
                            } else if (address.getText().toString().equals("")) {
                                Toast.makeText(Item_infoo.this, "Enter address", Toast.LENGTH_LONG).show();
                            } else if (contact.getText().toString().equals("")) {
                                Toast.makeText(Item_infoo.this, "Enter contact", Toast.LENGTH_LONG).show();
                            }
                            else if (price.getText().toString().equals("")) {
                                Toast.makeText(Item_infoo.this, "Enter  price", Toast.LENGTH_LONG).show();
                            } else if (quantity.getText().toString().equals("")) {
                                Toast.makeText(Item_infoo.this, "Enter quantity", Toast.LENGTH_LONG).show();
                            } else if (contact.getText().toString().equals("")) {
                                Toast.makeText(Item_infoo.this, "Enter contact", Toast.LENGTH_LONG).show();
                            }
                            else if (name.getText().toString().equals("")) {
                                Toast.makeText(Item_infoo.this, "Enter user name", Toast.LENGTH_LONG).show();
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
                    }}
                }).setNegativeButton("Cancel", null);
                builder.show();
            }
        });


//                LayoutInflater layoutInflater = getLayoutInflater();
//                View layout = layoutInflater.inflate(R.layout.custom_quantity_dailog, (ViewGroup) findViewById(R.id.custome_layout));
//                AlertDialog alertDialog = builder.create();
//                alertDialog.setView(layout);
//                alertDialog.show();


//               builder.setMessage("Bill").setCancelable(false)
//
//                       .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialogInterface, int i) {
//                               dialogInterface.cancel();
//                           }
//                       })
//                       .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialogInterface, int i) {
//                               dialogInterface.cancel();
//                           }
//                       });
//

        QuantityView quantityViewCustom1 = (QuantityView) findViewById(R.id.quantityView_custom_1);
        quantityViewCustom1.setOnQuantityChangeListener(this);


        ////final string
        final String kullad_names = getIntent().getExtras().getString("kullad_name","");
        final String kullad_codest = getIntent().getExtras().getString("kullad_code","");
        final String prices = getIntent().getExtras().getString("price","");
        final String addressst = getIntent().getExtras().getString("address","");
        final String names = getIntent().getExtras().getString("name","");
        final String caontcs = getIntent().getExtras().getString("mobile","");

        k_name.setText(kullad_names);
        k_code.setText(kullad_codest);
        price.setText(prices);

    }


    //// insert data in api
    private void insertdata() {

        Util.showProgressDialog(Item_infoo.this,false);
        VolleyMultipartRequest volleyMultipartRequest = new
                VolleyMultipartRequest(Request.Method.POST, EndPoints.order_insert,
                        new Response.Listener<NetworkResponse>() {
                            @Override
                            public void onResponse(NetworkResponse response) {
                                Util.dismisProgressDialog();

                                Toast.makeText(Item_infoo.this,"Data Inserted", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Item_infoo.this, OrderFragment.class);
                                startActivity(intent);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Util.dismisProgressDialog();
                                Toast.makeText(Item_infoo.this, "error"+error.toString(), Toast.LENGTH_SHORT).show();
                                Log.d("mynewerror",error.toString());
                            }
                        }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name.getText().toString().trim());
                        params.put("mobile", contact.getText().toString().trim());
                        params.put("address", address.getText().toString().trim());
                        params.put("price", price.getText().toString().trim());
                        params.put("kullad_name",k_name.getText().toString().trim());
                        params.put("kullad_code",k_code.getText().toString().trim());
                        params.put("quantity",quantity.getText().toString().trim());
                        return params;


                    }
                };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(volleyMultipartRequest);

    }



    @Override
    public void onQuantityChanged(int oldQuantity, int newQuantity, boolean programmatically) {
        QuantityView quantityViewCustom1 = (QuantityView) findViewById(R.id.quantityView_custom_1);
        if (newQuantity == 3) {
            quantityViewCustom1.setQuantity(oldQuantity);
        }
        Toast.makeText(Item_infoo.this, "Quantity: " + newQuantity, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLimitReached() {
        Log.d(getClass().getSimpleName(), "Limit reached");

    }
}
