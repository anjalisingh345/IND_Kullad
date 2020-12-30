package com.example.indiankullad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.himanshusoni.quantityview.QuantityView;

public class Item_infoo extends AppCompatActivity implements QuantityView.OnQuantityChangeListener {

    private int pricePerProduct = 180;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_infoo);

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
            }
        });

    }

    @Override
    public void onQuantityChanged(int oldQuantity, int newQuantity, boolean programmatically) {

    }

    @Override
    public void onLimitReached() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}