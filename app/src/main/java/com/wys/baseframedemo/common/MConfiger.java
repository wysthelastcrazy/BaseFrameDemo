package com.wys.baseframedemo.common;


/***
 * 配置类
 */
public class MConfiger {

	//debug开关
	public static final boolean isDebug = true;
	//crash buggly上报统计开关
	public static final boolean isBugglyEnable = false;

	public static final boolean isJSONDebug = false;
	//img debug开关
	public static final boolean isImgDebug = false;
	//下拉刷新相关debug开关
	public static final boolean isNLPullDebug = false;
	//统计相关debug开关
	public static final boolean isReportDebug = false;
	
	//闪屏时间 3秒
	public static final int SPLASH_INTERVAL = 1000 * 2;
	//下拉列表个数
	public static final int PAGE_SIZE = 15;
	//cookie过期时间 30天 1000 * 60 * 60 * 24 * 30 2592000000l
	public static final long COOKIE_EXPIRE_TIME = 1000*60*60*24*2;
    //渠道号码
    public static String CHANNEL_ID = "";
}

