package com.beta.MoneyballMaster.common;

/***
 * 基础常量
 * @date 2015/06/27
 */
public class Common {
	private static final String TAG = "Common";

	public static final String BASEURL = "http://a.test.menkoudai.com/";

	/**===========================================================================**/
	/**协议返回成功**/
	public static final int ERROR_CODE_SUCC = 1;

	/**协议返回错误,服务器内部异常也会出现这个问题**/
	public static final int ERROR_CODE_PROTOCAL = -100;
	/**网络错误**/
	public static final int ERROR_CODE_NET_ERROR = -101;
	/**发生Exception错误**/
	public static final int ERROR_CODE_EXCEPTION = -102;
	/**Exception UnSupportEncoding**/
	public static final int ERROR_CODE_UNSUPPORT_ENCODING = -103;
	/**服务器返回超时**/
	public static final int ERROR_CODE_TIME_OUT = -105;
}
