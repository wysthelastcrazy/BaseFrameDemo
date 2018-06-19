package com.beta.MoneyballMaster.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.base.BaseActivity;
import com.beta.MoneyballMaster.service.MyService;
import com.beta.MoneyballMaster.utils.MyLog;
import com.beta.MoneyballMaster.utils.SDCardUtil;

/**
 * Created by yas on 2018/3/12.
 */

public class ServiceTestActivity extends BaseActivity{
    private MyService.IMyBinder mBinder;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_service_test;
    }

    @Override
    protected void initLayout() {
        MyLog.INSTANCE.debug(TAG,"[initLayout] path:"+ SDCardUtil.INSTANCE.getSavePath());
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_startService:
                start();
                break;
            case R.id.btnMethod:
                if (mBinder!=null){
                    mBinder.invokeMethodInMyService();
                }
                break;
        }
    }

    private void start() {
        Intent intent=new Intent(this, MyService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mBinder= (MyService.IMyBinder) iBinder;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        },BIND_AUTO_CREATE);
    }
}
