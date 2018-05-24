package com.beta.MoneyballMaster;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.beta.MoneyballMaster.common.Global;
import com.beta.MoneyballMaster.widget.recycler.RefreshTopView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Created by yas on 2017/12/4.
 */

public class MApp extends Application{
    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new RefreshTopView(context);
            }
        });
    }
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
