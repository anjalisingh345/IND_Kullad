package com.example.indiankullad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Welcome_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);
        final
        SharedPreferences sharedPreferences=getSharedPreferences("check",MODE_PRIVATE);
        final String condition =sharedPreferences.getString("con","");
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (condition.equals("TRUE"))
                {
                    Intent i=new Intent(Welcome_Screen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i=new Intent(Welcome_Screen.this,Login.class);
                    startActivity(i);
                    finish();
                }

            }
        },1);
    }
    }
