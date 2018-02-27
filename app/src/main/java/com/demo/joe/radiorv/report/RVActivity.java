package com.demo.joe.radiorv.report;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.demo.joe.radiorv.R;
import com.demo.joe.radiorv.report.ReportAdapter;
import com.demo.joe.radiorv.report.ReportBean;

import java.util.ArrayList;
import java.util.List;

public class RVActivity extends AppCompatActivity {

    protected View rootView;
    private RecyclerView mRecyclerView;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        initView();

        List<ReportBean> reasons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            reasons.add(i, new ReportBean("111111111111111999999999998888880000000000", false));
        }

        ReportAdapter adapter = new ReportAdapter(reasons, mRecyclerView);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ReportAdapter.OnItemClickListener() {

            @Override
            public void onItemClickListener(ReportBean reportBean) {
                ok.setEnabled(true);
                Log.i("zj", reportBean.getReason());
                ok.setText("ok");
            }

            @Override
            public void noChecked() {
                ok.setEnabled(false);
            }


        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

    }

    private void initView() {
        rootView = findViewById(R.id.activity_report);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ok = (Button) findViewById(R.id.ok);

    }


}
