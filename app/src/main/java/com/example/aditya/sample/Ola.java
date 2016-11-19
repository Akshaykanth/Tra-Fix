package com.example.aditya.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ola extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ola);
    }

    public void okay(View v){
        Intent intent = new Intent(this, startCheck2.class);
        startActivity(intent);
    }
}
