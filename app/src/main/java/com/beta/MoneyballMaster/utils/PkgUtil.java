package com.beta.MoneyballMaster.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.beta.MoneyballMaster.common.Global;

/***
 * package公共工具类
 * @date 2015/07/24
 */
public class PkgUtil {

	private static final String TAG = "PkgUtil";

	/***
	 * 获取VersionCode
	 * @param context
	 * @return
	 */
	public static final int getVersionCode(Context context){
		int versionCode = 0;
		try {
			String pkgName = context.getPackageName();
			PackageInfo pkgInfo = context.getPackageManager().getPackageInfo(pkgName,0);
			if(pkgInfo != null){
				versionCode = pkgInfo.versionCode;
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		return versionCode;
	}


	/****
	 * 获取VersionName
	 * @return
	 */
	public static final String getVersionName(){
		String verName = "";
		try{
			Context mContext = Global.mContext;
		 	String pkgName = mContext.getPackageName();
			PackageInfo pkgInfo = mContext.getPackageManager().getPackageInfo(pkgName, 0);
			if(pkgInfo != null){
				verName = pkgInfo.versionName;
			}
		}catch(Exception ee){
			MyLog.error(TAG, ee);
		}
		return verName;
	}

	/**
	 * 获取报名
	 * @return
	 */
	public static String getPackageName(){
		return Global.getContext().getPackageName();
	}
}
