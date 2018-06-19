package com.beta.MoneyballMaster.utils

import android.util.Log

import com.beta.MoneyballMaster.common.MConfiger

/***
 * 日志类
 */
object MyLog {


    val isReportDebugable: Boolean
        get() = MConfiger.isReportDebug

    /****
     * Json解析相关开关
     * @return
     */
    val isJsonDebugable: Boolean
        get() = MConfiger.isJSONDebug

    /****
     * 是否debug调试模式
     * @return
     */
    val isDebugable: Boolean
        get() = MConfiger.isDebug

    /****
     * Img调试开关
     * @return
     */
    val isImgDebugable: Boolean
        get() = MConfiger.isImgDebug

    /***
     * 下拉刷新调试相关开关
     * @return
     */
    val isNLRefreshDebugable: Boolean
        get() = MConfiger.isNLPullDebug


    fun debug(TAG: String, msg: String) {
        if (!MConfiger.isDebug) {
            return
        }
        Log.d(TAG, msg)
    }

    fun error(TAG: String, msg: String, throwable: Throwable) {
        if (!MConfiger.isDebug) {
            return
        }
        Log.e(TAG, msg, throwable)
    }

    fun error(TAG: String, msg: String) {
        if (!MConfiger.isDebug) {
            return
        }
        Log.e(TAG, msg)
    }

    fun error(TAG: String, throwable: Throwable) {
        if (!MConfiger.isDebug) {
            return
        }
        Log.e(TAG, "", throwable)
    }
}
