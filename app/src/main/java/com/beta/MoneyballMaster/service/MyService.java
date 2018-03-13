package com.beta.MoneyballMaster.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.beta.MoneyballMaster.utils.MyLog;

/**
 * Created by yas on 2018/3/12.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private void methodInMyService(){
        MyLog.debug("MyService","invokeMethodInMyService");
    }
    private class MyBinder extends Binder implements IMyBinder{
        @Override
        public void invokeMethodInMyService() {
            methodInMyService();
        }
    }
    public interface IMyBinder{
        void invokeMethodInMyService();
    }
}
