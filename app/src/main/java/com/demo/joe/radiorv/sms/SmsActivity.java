package com.demo.joe.radiorv.sms;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.joe.radiorv.R;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

public class SmsActivity extends AppCompatActivity {

    private EditText code;

    private SmsContent mSmsContent;

    private SMSBroadcastReceiver mSMSBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        checkPermission();
        code = (EditText) findViewById(R.id.code);
        mSmsContent = new SmsContent(new Handler(), this, new SMSBroadcastReceiver.MessageListener() {
            @Override
            public void onReceived(String message) {
                code.requestFocus();
                code.setText(message);
                code.setSelection(message.length());
            }
        });
        this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, mSmsContent);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mSMSBroadcastReceiver = new SMSBroadcastReceiver();
//        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
//        intentFilter.setPriority(Integer.MAX_VALUE);
//        this.registerReceiver(mSMSBroadcastReceiver, intentFilter);
//        mSMSBroadcastReceiver.setOnReceivedMessageListener(new SMSBroadcastReceiver.MessageListener() {
//            @Override
//            public void onReceived(String message) {
//                code.setText(message);
//            }
//        });
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.getContentResolver().unregisterContentObserver(mSmsContent);
//        this.unregisterReceiver(mSMSBroadcastReceiver);
//        mSMSBroadcastReceiver = null;
    }

    private void checkPermission() {
        boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            Toast.makeText(this, "no权限", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},1);
        } else {
            Toast.makeText(this, "has权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "获取权限成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "获取权限失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
