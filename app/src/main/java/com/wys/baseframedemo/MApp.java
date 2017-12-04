package com.wys.baseframedemo;

import android.app.Application;

import com.wys.baseframedemo.common.Global;

/**
 * Created by yas on 2017/12/4.
 */

public class MApp extends Application{
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        //init context
        Global.mContext = getApplicationContext();
        Global.init();
        //delayTask相关
        delayTask();
    }

    private void delayTask() {
        Global.postDelay(new Runnable() {
            @Override
            public void run() {
//                Global.loadChannelInfo();
            }
        });
    }
}
