package com.beta.MoneyballMaster.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager.NameNotFoundException

import com.beta.MoneyballMaster.common.Global

/***
 * package公共工具类
 * @date 2015/07/24
 */
object PkgUtil {

    private val TAG = "PkgUtil"


    /****
     * 获取VersionName
     * @return
     */
    val versionName: String
        get() {
            var verName = ""
            try {
                val mContext = Global.mContext
                val pkgName = mContext.packageName
                val pkgInfo = mContext.packageManager.getPackageInfo(pkgName, 0)
                if (pkgInfo != null) {
                    verName = pkgInfo.versionName
                }
            } catch (ee: Exception) {
                MyLog.error(TAG, ee)
            }

            return verName
        }

    /**
     * 获取报名
     * @return
     */
    val packageName: String
        get() = Global.getContext().packageName

    /***
     * 获取VersionCode
     * @param context
     * @return
     */
    fun getVersionCode(context: Context): Int {
        var versionCode = 0
        try {
            val pkgName = context.packageName
            val pkgInfo = context.packageManager.getPackageInfo(pkgName, 0)
            if (pkgInfo != null) {
                versionCode = pkgInfo.versionCode
            }
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
        }

        return versionCode
    }
}
