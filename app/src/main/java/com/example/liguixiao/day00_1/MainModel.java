package com.example.liguixiao.day00_1;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liguixiao on 2019/9/18.
 */

public class MainModel {

    public void getData(final Model m){

        Retrofit build = new Retrofit.Builder()
                .baseUrl("https://api.yunxuekeji.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = build.create(MyServer.class);
        Observable<RootBeans> person = myServer.getPerson();
        person.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RootBeans>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RootBeans rootBeans) {
                        List<RootBeans.BodyBean.ResultBean> result = rootBeans.getBody().getResult();
                        m.data(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("tag", e.getMessage()+"");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
