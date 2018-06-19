package com.beta.MoneyballMaster.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

import com.beta.MoneyballMaster.activity.LoginActivity
import com.beta.MoneyballMaster.activity.ServiceTestActivity

/**
 * Created by yas on 2017/12/4.
 */

object IntentUtils {
    fun startLoginActivity(mContext: Context) {
        val intent = Intent(mContext, LoginActivity::class.java)
        mContext.startActivity(intent)
    }

    fun startServiceTestActivity(mContext: Activity) {
        val intent = Intent(mContext, ServiceTestActivity::class.java)
        mContext.startActivity(intent)
    }
}
