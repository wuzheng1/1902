package com.example.liguixiao.day00_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Persenter {

    private MainPersenter mainPersenter;
    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainPersenter = new MainPersenter(this);
        mainPersenter.getModel();
    }

    @Override
    public void data(List<RootBeans.BodyBean.ResultBean> result) {
        Log.i("tag", result.toString());
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this, result);
        mRecycler.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPersenter.detach();
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }
}
