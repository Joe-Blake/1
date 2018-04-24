package com.demo.joe.radiorv.sms;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by weizijie on 2018/4/24.
 */

public class SmsContent extends ContentObserver {
    private Cursor cursor = null;
    private Context context;
    private EditText setText;
    private String smsID = "";

    public SmsContent(Handler handler, Context context, EditText codeEditText) {
        super(handler);
        this.context = context;
        this.setText = codeEditText;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        //读取收件箱中指定号码的短信
//        cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"_id", "address", "read", "body"},
//                " address=? and read=?", new String[]{"10086", "0"}, "_id desc");//按id排序，如果按date排序的话，修改手机时间后，读取的短信就不准了

        cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"_id", "address",
                        "read", "body"},
                null, null, "date desc");
        Log.i("SMSTest","cursor.isBeforeFirst(): " + cursor.isBeforeFirst() + " cursor.getCount():  " + cursor.getCount());
        if (cursor != null && cursor.getCount() > 0) {
            Log.i("SMSTest", "smsID--" + smsID);
            cursor.moveToFirst();
            if (cursor.getString(cursor.getColumnIndex("_id")).equals(smsID)) {
                int smsbodyColumn = cursor.getColumnIndex("body");
                String smsBody = cursor.getString(smsbodyColumn);
                Log.i("SMSTest","smsBody = " + smsBody);
                setText.requestFocus();
                setText.setText(getDynamicPassword(smsBody));
                setText.setSelection(setText.getText().length());
            }
            smsID = cursor.getString(cursor.getColumnIndex("_id"));
        }
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
