package com.demo.joe.radiorv.footprints;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by weizijie on 2018/8/3.
 */

public interface DynamicApiService<T> {

    @GET
    Observable<ResponseBody> getDynamicData(@Url String Url);
}
