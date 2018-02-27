package com.demo.joe.radiorv.footprints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.joe.radiorv.R;

import java.util.ArrayList;
import java.util.List;

public class FootprintsActivity extends AppCompatActivity {

    Button dialog;
    FootprintsDialog mFootprintsDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footprints);
        mFootprintsDialog = new FootprintsDialog(this, getList());
        dialog = (Button) findViewById(R.id.dialog);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFootprintsDialog.show();
            }
        });
    }

    private List<FootprintBean> getList() {
        List<FootprintBean> beanList = new ArrayList<>();
        String url = "http://img5.ph.126.net/6NHiP2WgCjVnd42P3BWFeQ==/2612932208822079285.jpg";
        for (int i = 0; i < 6; i++) {
            FootprintBean bean = new FootprintBean("￥399", url, "上衣" + i, "url地址：" + i);
            beanList.add(bean);
        }
        return beanList;
    }
}
