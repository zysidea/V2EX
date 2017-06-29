package com.zysidea.v2ex.network

import android.content.Context

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.zysidea.v2ex.util.VLogger

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Created by zys on 17-6-21.
 */

class NetRequest private constructor(private val mContext: Context) {

    init {
        mRequestQueue = requestQueue
    }


    companion object {

        private var mRequestQueue: RequestQueue? = null
        private var mNetRequest: NetRequest? = null

        @Synchronized fun getInstance(context: Context): NetRequest {
            if (mNetRequest == null) {
                mNetRequest = NetRequest(context)
            }
            return mNetRequest!!
        }
    }

    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                synchronized(NetRequest::class.java) {
                    if (mRequestQueue == null) {
                        mRequestQueue = Volley.newRequestQueue(mContext.applicationContext, OkHttpStack())
                    }
                }
            }
            return mRequestQueue!!
        }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}
