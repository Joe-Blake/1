package com.demo.joe.radiorv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.joe.radiorv.footprints.FootprintsActivity;
import com.demo.joe.radiorv.report.RVActivity;
import com.demo.joe.radiorv.linegridview.CustomGirdActivity;
import com.demo.joe.radiorv.sms.SmsActivity;
import com.demo.joe.radiorv.translucent.TranslucentActivity;

public class MainActivity extends AppCompatActivity {

    private Button radioRV;
    private Button footprints;
    private Button lineGridView;
    private Button transslucent;
    private Button code;

    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioRV = (Button) findViewById(R.id.radio);
        radioRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RVActivity.class);
                startActivity(i);
            }
        });
        footprints = (Button) findViewById(R.id.complex);
        footprints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FootprintsActivity.class);
                startActivity(i);
            }
        });

        lineGridView = (Button) findViewById(R.id.lineGridView);
        lineGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CustomGirdActivity.class);
                startActivity(i);
            }
        });

        transslucent = (Button) findViewById(R.id.translucent);
        transslucent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TranslucentActivity.class);
                startActivity(i);
            }
        });

        code = (Button) findViewById(R.id.code);
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SmsActivity.class);
                startActivity(i);
            }
        });

        t = (TextView) findViewById(R.id.test);
        int data = 15001;
        double a = data/1000;
        double floor = Math.floor(a)/10;
        String result = String.valueOf(floor);
        t.setText("2.1.1.0");
    }
}
