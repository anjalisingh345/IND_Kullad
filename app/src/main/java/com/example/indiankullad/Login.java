package com.example.indiankullad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.indiankullad.Ui.ProductFragment;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signup(View view) {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    public void LoginWithEmail(View view) {

        Intent intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);

    }
}