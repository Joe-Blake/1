package com.demo.joe.radiorv.linegridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.demo.joe.radiorv.R;
import com.demo.joe.radiorv.linegridview.adapter.GridViewAdapter;

public class CustomGirdActivity extends AppCompatActivity {

    private LineGridView mLineGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_gird);
        mLineGridView = (LineGridView) findViewById(R.id.gridview);
        mLineGridView.init(this, 12);
        GridViewAdapter adapter = new GridViewAdapter(this);
        setHeight(adapter);
        mLineGridView.setAdapter(adapter);
    }

    private void setHeight(GridViewAdapter adapter) {
        int col = 4;
        int totalHeight = 0;

        for (int i = 0; i < adapter.getCount(); i += col) {
            View listItem = adapter.getView(i, null, mLineGridView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mLineGridView.getLayoutParams();
        params.height = totalHeight;
        mLineGridView.setLayoutParams(params);
    }
}
