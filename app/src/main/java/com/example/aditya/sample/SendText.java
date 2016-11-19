package com.example.aditya.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

public class SendText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_text);
    }

    public void yes_ido(View V){

        String phoneNumber = "9964997000";
        String message = "Your user is OverSpeeding..!!";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }

    public void no_idont(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
