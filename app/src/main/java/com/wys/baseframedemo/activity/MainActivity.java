package com.wys.baseframedemo.activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wys.baseframedemo.R;
import com.wys.baseframedemo.activity.base.BaseActivity;
import com.wys.baseframedemo.adapter.AdapterTest;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    private final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ArrayList<String> mList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            mList.add("详情详情详情详情详情详情详情详情详情详情" + i);
        }
        AdapterTest mAdapter = new AdapterTest(this, mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) this.findViewById(R.id.mRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
    }
}
