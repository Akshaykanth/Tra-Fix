package com.example.aditya.sample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static com.example.aditya.sample.NotificationService.*;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startService(new Intent(this, NotificationService.class));
        Log.e("ak","Created");

    }


    public void direct_driver(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void direct_passenger(View view) {
        Intent intent = new Intent(this, passenger_form.class);
        startActivity(intent);
    }





}

