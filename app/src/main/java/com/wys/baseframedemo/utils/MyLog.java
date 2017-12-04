package com.wys.baseframedemo.utils;

import android.util.Log;

import com.wys.baseframedemo.common.MConfiger;

/***
 * 日志类
 */
public class MyLog {
	
	public static final String Billy = "Billywen";

	public static final boolean isReportDebugable(){
		return MConfiger.isReportDebug;
	}
	
	/****
	 * Json解析相关开关
	 * @return
	 */
	public static final boolean isJsonDebugable(){
		return MConfiger.isJSONDebug;
	}
	
	/****
	 * 是否debug调试模式
	 * @return
	 */
	public static final boolean isDebugable(){
		return MConfiger.isDebug;
	}

	/****
	 * Img调试开关
	 * @return
	 */
	public static final boolean isImgDebugable(){
		return MConfiger.isImgDebug;
	}

	/***
	 * 下拉刷新调试相关开关
	 * @return
	 */
	public static final boolean isNLRefreshDebugable(){
		return MConfiger.isNLPullDebug;
	}
	
	
	public static final void debug(String TAG, String msg){
		if(!MConfiger.isDebug){
			return;
		}
		Log.d(TAG,msg);
	}
	
	public static final void error(String TAG, String msg, Throwable throwable){
		if(!MConfiger.isDebug){
			return;
		}
		Log.e(TAG, msg, throwable);
	}
	
	public static final void error(String TAG, String msg){
		if(!MConfiger.isDebug){
			return;
		}
		Log.e(TAG, msg);
	}

	public static final void error(String TAG, Throwable throwable){
		if(!MConfiger.isDebug){
			return;
		}
		Log.e(TAG, "", throwable);
	}
}
