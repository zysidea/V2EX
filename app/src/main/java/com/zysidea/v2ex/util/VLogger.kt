package com.zysidea.v2ex.util

import android.text.TextUtils
import android.util.Log

/**
 * Created by zys on 17-6-21.
 */

object VLogger {
    private val TAG = "V2EX"
    private val mDebug: Boolean = false

    fun LogInfo(message: String) {
        if (mDebug || TextUtils.isEmpty(message)) {
            return
        }
        Log.e(TAG, message)
    }
    fun LogInfo(message: Int) {
        Log.e(TAG, message.toString())
    }
}
