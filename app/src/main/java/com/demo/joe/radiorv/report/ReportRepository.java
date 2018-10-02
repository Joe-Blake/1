package com.demo.joe.radiorv.report;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weizijie on 2018/7/27.
 */

public class ReportRepository {

    public LiveData<List<ReportBean>> getData() {
        final MutableLiveData<List<ReportBean>> data = new MutableLiveData<>();
        List<ReportBean> reasons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            reasons.add(i, new ReportBean("111111111111111999999999998888880000000000", false));
        }
        data.setValue(reasons);
        return data;
    }
}
