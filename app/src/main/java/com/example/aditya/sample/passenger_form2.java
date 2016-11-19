package com.example.aditya.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class passenger_form2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_form2);
        startService(new Intent(this, NotificationService.class));
    }

    public void okay(View v){
        Intent intent = new Intent(this, startCheck2.class);
        startActivity(intent);
    }
}
