package com.demo.joe.radiorv.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by weizijie on 2018/3/21.
 */

public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.demo.joe.radiorv.application.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

}
