package com.example.aditya.sample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.parseDouble;

public class startCheck extends Activity {
   MyReceiver myReceiver;
    long n =0;
    double avgSpeed = 0;
    double meanSpeed=0;
    double meanTot=0;
    double tot =0;
    int flag =0;
    double uspeed =35;
    double lspeed =25;
    double latitude[] = new double[2];
    double longitude[] = new double[2];
    Date date[] = new Date[2];
    //Intent ser = new Intent(this,LocationService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_check);
       myReceiver = new MyReceiver();
       IntentFilter intentFilter = new IntentFilter("Location");
      registerReceiver(myReceiver, intentFilter);
       //startService(ser);
    }




    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        unregisterReceiver(myReceiver);
        super.onStop();
    }


    private double calculate(double inLat ,double inLong ,double fiLat ,double fiLong,long delTime){

        double delLat =Math.toRadians (fiLat - inLat);
        double delLongi =Math.toRadians (fiLong - inLong);

        final double R = 6371e3;
        inLat = Math.toRadians(inLat);
        inLong = Math.toRadians(inLong);
        fiLat = Math.toRadians(fiLat);
        fiLong = Math.toRadians(fiLong);
        double a = Math.sin(delLat/2) * Math.sin(delLat/2) + Math.cos(inLat) * Math.cos(fiLat) * Math.sin(delLongi/2) * Math.sin(delLongi/2);
        double c   = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R*c;
        double speed = (d/delTime)*3.6;
        return speed;
    }

    private void updateSpeed(double currSpeed){
        if(flag > 4)
        {

            Log.e("Caught" ,"Rash");
           stopService(new Intent(this,LocationService.class));
            String phoneNumber = "9663052146";
            String message = "Your user is OverSpeeding..!!";

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        }
        meanTot+= currSpeed;
        meanSpeed = meanTot /n;

        tot += currSpeed;
        avgSpeed = tot/(n%10 +1);

        double temp = avgSpeed;

        Log.d("Mean Speed", Double.toString(meanSpeed));
        Log.d("Avg Speed", Double.toString(avgSpeed));
        Log.d("U Speed", Double.toString(uspeed));
        Log.d("L Speed", Double.toString(lspeed));

        if(meanSpeed >30) {

            if (avgSpeed > uspeed || avgSpeed < lspeed) {
                flag++;

            }


            if (avgSpeed > meanSpeed) {


                uspeed =  ((avgSpeed / meanSpeed - 0.4) * uspeed);
                lspeed =  ((avgSpeed / meanSpeed - 0.4) * lspeed);

            }

            if (avgSpeed < meanSpeed) {

                uspeed =  ((avgSpeed / meanSpeed) * uspeed);
                lspeed =  ((avgSpeed / meanSpeed) * lspeed);

            }


            if (n % 5 == 0) {

                tot = 0;


            }


        }



    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent intent) {
            // TODO Auto-generated method stub
            //long time = Long.parseLong(intent.getStringExtra("timeStamp"));

            if (latitude[0] == 0 && longitude[0] == 0) {

                longitude[0] = parseDouble(intent.getStringExtra("longitude"));
                latitude[0] = parseDouble(intent.getStringExtra("latitude"));
                date[0] = Calendar.getInstance().getTime();
                Log.e("Time",Long.toString( date[0].getTime()));

            } else if (latitude[1] == 0) {

                longitude[1] = parseDouble(intent.getStringExtra("longitude"));
                latitude[1] = parseDouble(intent.getStringExtra("latitude"));
                date[1] = Calendar.getInstance().getTime();
                Log.e("Time",Long.toString( date[0].getTime()));

            } else {

                longitude[0] = longitude[1];
                latitude[0] = latitude[1];
                date[0] = date[1];

                longitude[1] = parseDouble(intent.getStringExtra("longitude"));
                latitude[1] = parseDouble(intent.getStringExtra("latitude"));
                date[1] = Calendar.getInstance().getTime();


                //  Date temp = date[0];
                // Date now = Calendar.getInstance().getTime();

                long diff = date[1].getTime() - date[0].getTime();
                long seconds = TimeUnit.MILLISECONDS.toSeconds(diff+1000);
//                Log.e("Temp [0]",Long.toString( date[1].getTime()));

                double radLat[] = new double[2];
                double radLong[] = new double[2];

                radLat[0] = Math.toRadians(latitude[0]);
                radLat[1] = Math.toRadians(latitude[1]);
                radLong[0] = Math.toRadians(longitude[0]);
                radLong[1] = Math.toRadians(longitude[1]);


                Log.e("Diff", Long.toString(seconds));
                double speed = calculate(latitude[0], longitude[0], latitude[1], longitude[1], seconds);
                if (speed < 200) {
                    tot += speed;
                    n++;
                    updateSpeed(speed);
                }
            }

        }
    }
}


