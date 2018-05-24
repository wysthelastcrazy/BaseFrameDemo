package com.beta.MoneyballMaster.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.beta.MoneyballMaster.R;
import java.util.HashMap;

/**
 * Created by yas on 2018/3/13.
 * CollapsingToolbarLayout 布局
 */

public class CollapsingToolbarActivity extends Activity{
    private final String TAG="CollapsingToolbarActivity";
    private HashMap<String,String> mMap=new HashMap<>();
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
    }
}
