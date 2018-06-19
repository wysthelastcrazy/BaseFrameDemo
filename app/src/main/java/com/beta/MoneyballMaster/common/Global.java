package com.beta.MoneyballMaster.common;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

import com.beta.MoneyballMaster.utils.MyLog;


/**
 * 全局常量，变量
 */
public class Global {
	private static final String TAG = "Global";
	
	public static Context mContext;
	private static HandlerThread mHandlerThread = null;
	private static Looper mLooper = null;
	private static Handler bizHandler = null;
	private static Handler uiHandler = null;
	public static String APP_NAME = "";
	private static Toast mToast;
	private static String KZUA = null;
	public static int PKG_VER = 0;
	public static boolean isFromSplash = false;
	public static String JPUSH_ID = "";
	
	static{
		initHandlerThread();
		uiHandler = new Handler(Looper.getMainLooper());
		//应用名称
		APP_NAME = "门口贷";
	}
	
	private static final void initHandlerThread(){
		mHandlerThread = new HandlerThread(TAG, Process.THREAD_PRIORITY_BACKGROUND);
		mHandlerThread.start();
		mLooper = mHandlerThread.getLooper();
		bizHandler = new Handler(mLooper);
	}
	
	/***
	 * 初始化操作
	 */
	public static void init(){
		String pkgName = Global.mContext.getPackageName();
		try {
			PackageInfo pkgInfo = Global.mContext.getPackageManager().getPackageInfo(pkgName,0);
			if(pkgInfo != null){
				int version = pkgInfo.versionCode;
				Global.PKG_VER = version;
			}
		} catch (NameNotFoundException e) {
			MyLog.INSTANCE.error(TAG, e);
		}
	}
	
	public static final Context getContext(){
		return mContext;
	}

	public static final void postDelay(Runnable r){
		if(mHandlerThread != null && !mHandlerThread.isAlive()){
			initHandlerThread();
		}
		if(r != null){
			bizHandler.post(r);
		}
	}

	public static final void post2UI(Runnable r){
		uiHandler.post(r);
	}

	public static final void post2UIDelay(Runnable r, long delayMillis){
		uiHandler.postDelayed(r,delayMillis);
	}

	/****
	 * 非UI操作，delay
	 * @param r
	 * @param delayMillis
	 */
	public static final void postDelay(Runnable r, long delayMillis){
		bizHandler.postDelayed(r, delayMillis);
	}

	/***
	 * 移除任务
	 * @param r
	 */
	public static final void removeDelay(Runnable r){
		bizHandler.removeCallbacks(r);
		uiHandler.removeCallbacks(r);
	}
	
	/***
	 * 获得QUA信息
	 * @return
	 */
	public static final String getQUA(){
		return "";
	}
	
	public static void releaseAll(){
		bizHandler.removeCallbacksAndMessages(null);
		mHandlerThread.quit();
	}
	
	public static final void toast(String msg){
		if(mToast != null){
			mToast.cancel();
		}
		mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
		mToast.show();
	}
	
	/***
	 * 加载渠道信息
	 */
	public static final void loadChannelInfo(){
		Context mContext = Global.mContext;
		ApplicationInfo appInfo = null;
		try {
			appInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
			if(appInfo != null && appInfo.metaData != null){
				String val = appInfo.metaData.getInt("UMENG_CHANNEL")+"";
				MConfiger.CHANNEL_ID = val;
			}
		} catch (NameNotFoundException e) {
			MyLog.INSTANCE.error(TAG, e);
		}
	}
}
