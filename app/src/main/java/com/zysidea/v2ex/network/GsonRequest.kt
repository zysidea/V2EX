package com.zysidea.v2ex.network

import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.apache.http.protocol.HTTP
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

/**
 * Created by zys on 17-6-21.
 */
class GsonRequest<T>(method: Int, url: String, private val clazz: Class<T>,
                     private val listener: Response.Listener<T>, private val errListener: Response.ErrorListener)
    : Request<T>(method, url, errListener) {

    private var mGson: Gson

    init {
        mGson = Gson()
    }

    override fun getHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()
        headers.put("Content-Type", "application/json; charset=utf-8")
        return headers
    }

    override fun deliverResponse(response: T) {
        listener.onResponse(response)
    }

    @Throws(UnsupportedEncodingException::class, JsonSyntaxException::class)
    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        val json = String(response!!.data, Charset.forName("UTF-8"))
        return Response.success(mGson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response))
    }
}