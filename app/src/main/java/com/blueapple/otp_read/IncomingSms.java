package com.blueapple.otp_read;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class IncomingSms extends BroadcastReceiver
{

    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {


        // Retrieves a map of extended data from the intent.



        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null)
            {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                Log.d("pdusObj", String.valueOf(pdusObj.length));

                for (int i = 0; i <pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    //Toast.makeText(context, "message recieved sucessfully from "+phoneNumber , Toast.LENGTH_SHORT).show();


                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody().split(":")[1];




                    Log.d("message_sms", message);

                 //   message = message.substring(0, message.length()-1);

                    Log.d("sms_body_substring",message);

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                    Intent myIntent = new Intent("otp");
                    myIntent.putExtra("message",message);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(myIntent);
                    // Show Alert

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }

    }



}
