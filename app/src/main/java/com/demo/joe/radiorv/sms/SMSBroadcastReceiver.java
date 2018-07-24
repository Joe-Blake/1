package com.demo.joe.radiorv.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * R9s收不到广播
 * Created by weizijie on 2018/5/14.
 */

public class SMSBroadcastReceiver extends BroadcastReceiver {

    private MessageListener mMessageListener;

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            for(Object pdu:pdus) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte [])pdu);
                String content = smsMessage.getMessageBody();
                String code = getDynamicPassword(content);
                Log.i("zj---content", content);
                Log.i("zj---code", code);
                mMessageListener.onReceived(code);
            }
//        }
    }

    public interface MessageListener {
        void onReceived(String message);
    }

    public void setOnReceivedMessageListener(MessageListener messageListener) {
        this.mMessageListener = messageListener;
    }

    public static String getDynamicPassword(String str) {
        Pattern continuousNumberPattern = Pattern.compile("[0-9\\.]+");
        Matcher m = continuousNumberPattern.matcher(str);
        String dynamicPassword = "";
        while(m.find()){
            if(m.group().length() == 4) {
                dynamicPassword = m.group();
            }
        }
        return dynamicPassword;
    }
}
