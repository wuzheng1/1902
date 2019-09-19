package com.example.liguixiao.day00_1;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by liguixiao on 2019/9/18.
 */

public interface MyServer {
    @GET("yunxue_app_api/content/getData/30/66597/1/10")
    Observable<RootBeans> getPerson();
}
