package com.blueapple.otp_read;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {


    BroadcastReceiver receiver;
    public EditText mEditText1;
    public EditText mEditText2;
    public EditText mEditText3;
    public EditText mEditText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();


        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase("otp")) {
                     String message = intent.getStringExtra("message");


                    char[] ch = new char[message.length()];

                    Log.d("charlength", String.valueOf(ch.length));

                    // Copy character by character into array
                    for (int x = 0; x < message.length(); x++) {
                        ch[x] = message.charAt(x);

                        Log.d("data_array", String.valueOf(ch[x]));

                        if (x==0)
                        {
                            Toast.makeText(context, ""+ch[0], Toast.LENGTH_SHORT).show();
                            mEditText1.setText(String.valueOf(ch[0]));
                        }

                        if (x==1)
                        {
                            mEditText2.setText(String.valueOf(ch[1]));
                        }
                        if (x==2)
                        {
                            mEditText3.setText(String.valueOf(ch[2]));
                        }
                        if (x==3)
                        {
                            mEditText4.setText(String.valueOf(ch[3]));
                        }
                    }


                    //Do whatever you want with the code here

                    Toast.makeText(context, "meaasge recieved", Toast.LENGTH_SHORT).show();

                }
            }
        };

    }


    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private void initView() {
        mEditText1 = (EditText) findViewById(R.id.editText1);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        mEditText3 = (EditText) findViewById(R.id.editText3);
        mEditText4 = (EditText) findViewById(R.id.editText4);
    }
}
