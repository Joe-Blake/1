package com.demo.joe.radiorv.report;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class RVActivity extends AppCompatActivity {

    protected View rootView;
    private RecyclerView mRecyclerView;
    private Button ok;
    private ReportModel mReportModel;
    ReportAdapter mReportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        initView();
//        List<ReportBean> reasons = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            reasons.add(i, new ReportBean("111111111111111999999999998888880000000000", false));
//        }

        try {
            Class c = Class.forName("com.demo.joe.radiorv.report.ReportBean");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mReportAdapter = new ReportAdapter(mRecyclerView);
        mRecyclerView.setAdapter(mReportAdapter);
        mReportAdapter.setOnItemClickListener(new ReportAdapter.OnItemClickListener() {

            @Override
            public void onItemClickListener(ReportBean reportBean) {
                ok.setEnabled(true);
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
        mReportModel = ViewModelProviders.of(this).get(ReportModel.class);
        mReportModel.init();
        mReportModel.getData().observe(this, new Observer<List<ReportBean>>() {
            @Override
            public void onChanged(@Nullable List<ReportBean> reportBeans) {
                mReportAdapter.setReasons(reportBeans);
                mReportAdapter.notifyDataSetChanged();
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
