package com.demo.joe.radiorv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.joe.radiorv.footprints.FootprintsActivity;
import com.demo.joe.radiorv.report.RVActivity;

public class MainActivity extends AppCompatActivity {

    private Button radioRV;
    private Button footprints;
    private Button lineGridView;


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

    }
}
