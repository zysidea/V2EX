package com.zysidea.v2ex.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by zys on 17-6-21.
 */

public class VLogger {
    private static String TAG="V2EX";
    private static boolean mDebug;

    public static void LogInfo(String message){
        if (mDebug || TextUtils.isEmpty(message)) {
            return;
        }
        Log.d(TAG, message);
    }
}
