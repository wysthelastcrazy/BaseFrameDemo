package com.beta.MoneyballMaster.utils;

import android.os.Environment;

import com.beta.MoneyballMaster.common.Global;

import java.io.File;

public class SDCardUtil {
	private static final String TAG = "SDCardUtil";
	
	private static final String FileName = "/menkoudai_2b/pic";
	private static final String FileNameCamera = "/menkoudai_2b/camera";
	private static final String FileNameSave = "/menkoudai_2b/save";
	
	static{
		//创建pic目录
		String filePath = getSDCardPath_Pic();
		File file = new File(filePath);
		if(file != null && !file.exists()){
			file.mkdirs();
		}
		//创建cammer目录
		filePath = getSDCardCamerPath();
		file = new File(filePath);
		if(file != null && !file.exists()){
			file.mkdirs();
		}
		//创建save目录
		filePath = getSavePath();
		file = new File(filePath);
		if(file != null && !file.exists()){
			file.mkdirs();
		}
	}
	
	public static boolean isSDCardExist(){
		boolean isExits = false;
		isExits = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		isExits = true;
		return isExits;
	}
	
	public static boolean isSDCardExistReal(){
		boolean isExits = false;
		isExits = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		MyLog.debug("dd","[initLayout] isExits:"+isExits);
		return isExits;
	}
	
	/***
	 * 得到SD卡路径
	 * @return
	 */
	private static String getSDCardPath(){
		String path = null;
		if(isSDCardExistReal()){
			path = Environment.getExternalStorageDirectory().toString();
		}else{
			path = Global.getContext().getFilesDir().getAbsolutePath();
		}
		return path;
	}
	
	
	public static String getSDCardPath_Pic(){
		String path = getSDCardPath() + FileName;
		return path;
	}
	
	/***
	 * 获取carmerpath路径
	 * @return
	 */
	public static final String getSDCardCamerPath(){
		String path = getSDCardPath() + FileNameCamera;
		return path;
	}

	/***
	 * 获取文件保存路径
	 * @return
	 */
	public static final String getSavePath(){
		String path = getSDCardPath() + FileNameSave;
		return path;
	}
	
	/***
	 * 判断SD卡是否可用
	 * @return
	 */
	public static final boolean isSDCardEnable(){
		File file = new File(getSDCardCamerPath());
		if(file.exists()){
			return true;
		}
		return false;
	}
}
