package com.example.aditya.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Uber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uber);
    }

    public void okay(View v){
        Intent intent = new Intent(this, startCheck2.class);
        startActivity(intent);
    }
}
