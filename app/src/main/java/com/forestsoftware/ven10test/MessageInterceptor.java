package com.forestsoftware.ven10test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormatSymbols;

import static android.content.ContentValues.TAG;

/**
 * Created by HP-PC on 6/6/2018.
 */

public class MessageInterceptor extends BroadcastReceiver {
    private Util util;

//    private MainActivity myActivity;
//
//    public MessageInterceptor(MainActivity myActivity) {
//        this.myActivity= myActivity;
//    }


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdus[0]);
        Log.wtf("Error ", messages.getMessageBody());


        processMessage(context, intent);

    }

    public void processMessage(final Context c, Intent inte) {
        if (inte.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = inte.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];

                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);


                        msg_from = msgs[i].getOriginatingAddress();


                        if (msg_from.equals("Ven10")) {

                            PackageManager pm = c.getPackageManager();

                            String msgBody = msgs[i].getMessageBody();

                            int index = msgBody.indexOf(':');
                            String stringValue1 = msgBody.substring(0, index);
                            String stringValue2 = msgBody.substring(1, index);
                            String stringValue3 = msgBody.substring(2, index
                            );
                            String[] lines = msgBody.split("\\n");
                            String linesOfText[] = msgBody.split("\\r?\\n");
                            util = new Util(c);
                            util.setMessage(linesOfText[1]);


                            String firstLine = linesOfText[0];
                            String codedMessage = linesOfText[1];
                            String thirdLine = linesOfText[2];

                            String[] splitter = firstLine.split(":");

                            String Date = splitter[1];
                            String Hour = splitter[2];
                            String Minute = splitter[3];

                            String[] dateSplitter = Date.split("/");
                            String Month = dateSplitter[0];
                            String thisDate = dateSplitter[1];
                            String thisYear = dateSplitter[2];

                            String[] splitThirdLine = lines[2].split(" ");

                            String[] SplitSecond = splitThirdLine[1].split("w");

                            String ScreenWidth = SplitSecond[0];
                            String[] splitLength = SplitSecond[1].split("l");
                            String ScreenLength = SplitSecond[0];
//                            Log.e(TAG, "Width  1: "+ScreenWidth );
//                            Log.e(TAG, "Length 2: "+ScreenLength);



                            String[] SplitColorCodes = splitThirdLine[3].split("-");
                            String leftColorCode = SplitColorCodes[0];
                            String rightColorCode = SplitColorCodes[1];

//                            Log.e(TAG, "Left color codes: "+leftColorCode );
//                            Log.e(TAG, "Right: "+rightColorCode );


                            final Intent launchIntent = pm.getLaunchIntentForPackage("com.forestsoftware.ven10test");
                            launchIntent.putExtra("msg", "" + linesOfText[1]);
                            launchIntent.putExtra(Const.MONTH, getMonth(Integer.valueOf(Month)));
                            launchIntent.putExtra(Const.DAY, thisDate);
                            launchIntent.putExtra(Const.YEAR, thisYear);
                            launchIntent.putExtra(Const.MONTH, getMonth(Integer.valueOf(Month)));
                            launchIntent.putExtra(Const.DECENT_TIME, "" + Hour.trim() + ":" + Minute.trim());

                            launchIntent.putExtra(Const.WIDTH, ScreenWidth);
                            launchIntent.putExtra(Const.HEIGHT, ScreenLength);


                            launchIntent.putExtra(Const.FIRST_COLOR_CODES, leftColorCode);
                            launchIntent.putExtra(Const.SECOND_COLOR_CODES, rightColorCode);


                            c.startActivity(launchIntent);

                        } else {
                            Log.e(TAG, "intercept: We are not getting what we want");
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(c, " Toasting ", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }

    private static int countLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        return lines.length;
    }

    ;

    private static String StartsWith(String sw) {
        if (sw.matches("(DT:).*")) {
            return sw;
        } else Log.e(TAG, "StartsWith: Specification not met");
        ;

        return sw;
    }

    ;

    private static String StartsWithSZ(String sw) {
        if (sw.matches("(SZ:).*")) {
            return sw;
        } else Log.e(TAG, "StartsWith: Specification not met");
        ;

        return sw;
    }

    ;

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }
}
