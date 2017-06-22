package com.zysidea.v2ex.network

import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

/**
 * Created by zys on 17-6-21.
 */
class  GsonRequest<T>(method:Int,url:String,private val clazz: Class<T>,private val headers: Map<String, String>?,
                      private val listener:Response.Listener<T>,private val errListener:Response.ErrorListener)
    :Request<T>(method,url,errListener){

    private var mGson:Gson

    init {
        mGson=Gson()
    }

    override fun getHeaders(): Map<String, String> {
        return headers?:super.getHeaders()
    }

    override fun deliverResponse(response: T) {
        listener.onResponse(response)
    }

    @Throws(UnsupportedEncodingException::class,JsonSyntaxException::class)
    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        val json = String(response!!.data, Charset.forName(HttpHeaderParser.parseCharset(response!!.headers)))
        return Response.success(mGson.fromJson(json,clazz),HttpHeaderParser.parseCacheHeaders(response))
    }
}