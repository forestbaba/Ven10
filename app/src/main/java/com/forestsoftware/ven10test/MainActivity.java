package com.forestsoftware.ven10test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0;
    public static final String TAG = MainActivity.class.getSimpleName();
    private TextView dateTextView,timeTextView,messageTextview,dimensionText;
    private CardView cardView;
    private Util util;
    private  Intent launchIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateTextView = (TextView)findViewById(R.id.date_textview);
        timeTextView = (TextView)findViewById(R.id.time_textview);
        dimensionText = (TextView)findViewById(R.id.dimension_text);
        messageTextview = (TextView)findViewById(R.id.message_textview);
        cardView = (CardView)findViewById(R.id.cardview);
        util = new Util(MainActivity.this);
        init();


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            //if the permission is not been granted then check if the user has denied the permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS))
            {
                //Do nothing as user has denied
            }
            else
            {
                //a pop up will appear asking for required permission i.e Allow or Deny
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }


         launchIntent = getIntent();
        String decentTime = launchIntent.getStringExtra(Const.DECENT_TIME);
        String message = launchIntent.getStringExtra(Const.MESSAGE);

        Log.e(TAG, "Before crash: "+launchIntent.getStringExtra(Const.HEIGHT) );
        Log.e(TAG, "Message: "+  launchIntent.getStringExtra("msg"));
        Log.e(TAG, "Month: "+ launchIntent.getStringExtra(Const.MONTH));
        Log.e(TAG, "DAY: "+   launchIntent.getStringExtra(Const.DAY));
        Log.e(TAG, "YEAR: "+launchIntent.getStringExtra(Const.YEAR));
        Log.e(TAG, "Month: "+launchIntent.getStringExtra(Const.MONTH));
        Log.e(TAG, "DECENT Time: " + launchIntent.getStringExtra(Const.DECENT_TIME));
        Log.e(TAG, "WIDTH: "+ launchIntent.getStringExtra(Const.WIDTH));
        Log.e(TAG, "HEIGHT: "+  launchIntent.getStringExtra(Const.HEIGHT));
        Log.e(TAG, "FIRST COLOR: "+launchIntent.getStringExtra(Const.FIRST_COLOR_CODES));
        Log.e(TAG, "SECOND OOLOR: "+ launchIntent.getStringExtra(Const.SECOND_COLOR_CODES));



//        if(null != launchIntent &&
//                !TextUtils.isEmpty(launchIntent.getStringExtra("msg"))) {
//
//
//            String changeText = launchIntent.getStringExtra("msg");
//            // Extracting sent text from intent
//
//            messageTextview.setText(changeText);
//            // Setting received text on Button
//
//        }


      //  messageTextview.setText(launchIntent.getStringExtra("msg") != null ? launchIntent.getStringExtra("msg").toString() : "No Content");
//        Log.e(TAG, "Vvalue of new msg: "+util.getMessage());
//        dateTextView.setText(launchIntent.getStringExtra(Const.DAY) + "th " +
//              launchIntent.getStringExtra(Const.MONTH) +" "+launchIntent.getStringExtra(Const.YEAR));
//        timeTextView.setText(launchIntent.getStringExtra(decentTime));






    }

    public void onResume() {
        super.onResume();

        if(null != launchIntent &&
                !TextUtils.isEmpty(launchIntent.getStringExtra("msg"))) {

            int cardHeight = 0;
            int cardWidth = 200;
            if(Integer.parseInt(launchIntent.getStringExtra(Const.HEIGHT)) >0){
                cardHeight = Integer.parseInt(launchIntent.getStringExtra(Const.HEIGHT));
            }

//            if ( launchIntent.getStringExtra(Const.HEIGHT) != null){
//                try {
//                    cardHeight = Integer.valueOf(launchIntent.getStringExtra(Const.HEIGHT));
//
//                }catch (NumberFormatException e){
//                    cardHeight = 0;
//                }
//                return;
//            }
//            if ( launchIntent.getStringExtra(Const.WIDTH) != null){
//                try {
//                    cardWidth = Integer.valueOf(launchIntent.getStringExtra(Const.WIDTH));
//
//                }catch (NumberFormatException e){
//                    cardWidth = 0;
//                }
//                return;
//            }

            LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) cardView.getLayoutParams();
            cardView.setLayoutParams(layoutParams);

            dateTextView.setText(launchIntent.getStringExtra(Const.DAY) + "th " +
                    launchIntent.getStringExtra(Const.MONTH) +" "+launchIntent.getStringExtra(Const.YEAR));
            timeTextView.setText(launchIntent.getStringExtra(Const.DECENT_TIME));
            dimensionText.setText( launchIntent.getStringExtra(Const.WIDTH) + "x" +launchIntent.getStringExtra(Const.HEIGHT));


            String changeText = launchIntent.getStringExtra("msg");

            messageTextview.setText(changeText);

        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        //will check the requestCode
        switch(requestCode)
        {
            case MY_PERMISSIONS_REQUEST_RECEIVE_SMS:
            {
                //check whether the length of grantResults is greater than 0 and is equal to PERMISSION_GRANTED
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    //Now broadcastreceiver will work in background
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, "Permission is required", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private void init(){
        util.getDecentTime();
        util.getDecentTime();
        util.getHeight();
        util.getMonth();
        util.getDay();
        util.getYear();
        util.getWidth();
        util.getHeight();
        util.getFirstColorCodes();
        util.getSecondColorCdes();
    }


}
