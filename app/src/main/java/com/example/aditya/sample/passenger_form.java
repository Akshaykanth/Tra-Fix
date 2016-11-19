package com.example.aditya.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class passenger_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_form);
    }

    public void redirect(View v) {
        Spinner m=(Spinner)findViewById(R.id.spinner);
        String s= m.getSelectedItem().toString();
        if(s.equals("Others(KSRTC or other Private)")) {
            Intent intent = new Intent(this, passenger_form2.class);
            startActivity(intent);
        }
        else if(s.equals("Ola")){
            Intent intent = new Intent(this, Ola.class);
            startActivity(intent);
        }
        else if(s.equals("Uber")){
            Intent intent = new Intent(this, Uber.class);
            startActivity(intent);

        }
    }

}
