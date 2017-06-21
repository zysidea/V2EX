package com.zysidea.v2ex.network

import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser

/**
 * Created by zys on 17-6-21.
 */
class  GsonRequest<T>(method:Int,url:String,private val clazz: Class<T>,private val headers: Map<String, String>?,
                      private val listener:Response.Listener<T>,private val errListener:Response.ErrorListener)
    :Request<T>(method,url,errListener){

    override fun getHeaders(): Map<String, String> {
        return headers?:super.getHeaders()
    }

    override fun deliverResponse(response: T) {
        listener.onResponse(response)
    }

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        val json = String
    }
}