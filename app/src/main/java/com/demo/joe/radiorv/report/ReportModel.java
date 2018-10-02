package com.demo.joe.radiorv.report;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

/**
 * Created by weizijie on 2018/7/27.
 */

public class ReportModel extends AndroidViewModel {

    private LiveData<List<ReportBean>> mData = new MutableLiveData<>();
    private ReportRepository mReportRepository;

    public ReportModel(@NonNull Application application) {
        super(application);
        mReportRepository = new ReportRepository();
    }

    public void init() {
        mData = mReportRepository.getData();
    }

    public LiveData<List<ReportBean>> getData() {
        return mData;
    }

    @Override
    protected void onCleared() {
        Log.e("ReportModel", "==========onCleared()==========");
        super.onCleared();
    }
}
