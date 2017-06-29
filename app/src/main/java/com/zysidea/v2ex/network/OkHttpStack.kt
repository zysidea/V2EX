package com.zysidea.v2ex.network

import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.HurlStack
import com.zysidea.v2ex.util.VLogger

import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.ProtocolVersion
import org.apache.http.StatusLine
import org.apache.http.entity.BasicHttpEntity
import org.apache.http.message.BasicHeader
import org.apache.http.message.BasicHttpResponse
import org.apache.http.message.BasicStatusLine

import java.io.IOException
import java.util.concurrent.TimeUnit

import okhttp3.Call
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor


/**
 * Created by zys on 17-6-21.
 */

class OkHttpStack() : HurlStack() {

    @Throws(IOException::class, AuthFailureError::class)
    override fun performRequest(request: Request<*>, additionalHeaders: Map<String, String>): HttpResponse {

        val timeoutMs = request.timeoutMs
        val client = OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> VLogger.LogInfo(message) }))
                .connectTimeout(timeoutMs.toLong(), TimeUnit.MILLISECONDS)
                .readTimeout(timeoutMs.toLong(), TimeUnit.MILLISECONDS)
                .writeTimeout(timeoutMs.toLong(), TimeUnit.MILLISECONDS)
                .build()

        val okHttpRequestBuilder = okhttp3.Request.Builder()
        okHttpRequestBuilder.url(request.url)

        val headers = request.headers
        for (name in headers.keys) {
            okHttpRequestBuilder.addHeader(name, headers[name])
        }
        for (name in additionalHeaders.keys) {
            okHttpRequestBuilder.addHeader(name, additionalHeaders[name])
        }

        setConnectionParametersForRequest(okHttpRequestBuilder, request)

        val okHttpRequest = okHttpRequestBuilder.build()
        val okHttpCall = client.newCall(okHttpRequest)
        val okHttpResponse = okHttpCall.execute()

        val responseStatus = BasicStatusLine(parseProtocol(okHttpResponse.protocol()), okHttpResponse.code(), okHttpResponse.message())
        val response = BasicHttpResponse(responseStatus)
        response.entity = entityFromOkHttpResponse(okHttpResponse)

        val responseHeaders = okHttpResponse.headers()
        var i = 0
        val len = responseHeaders.size()
        while (i < len) {
            val name = responseHeaders.name(i)
            val value = responseHeaders.value(i)
            if (name != null) {
                response.addHeader(BasicHeader(name, value))
            }
            i++
        }

        return response
    }

    @Throws(IOException::class)
    private fun entityFromOkHttpResponse(r: Response): HttpEntity {
        val entity = BasicHttpEntity()
        val body = r.body()

        entity.content = body!!.byteStream()
        entity.contentLength = body.contentLength()
        entity.setContentEncoding(r.header("Content-Encoding"))

        if (body.contentType() != null) {
            entity.setContentType(body.contentType()!!.type())
        }
        return entity
    }

    @Throws(IOException::class, AuthFailureError::class)
    private fun setConnectionParametersForRequest(builder: okhttp3.Request.Builder, request: Request<*>) {
        when (request.method) {
            Request.Method.DEPRECATED_GET_OR_POST -> {
                // Ensure backwards compatibility.  Volley assumes a request with a null body is a GET.
                val postBody = request.postBody
                if (postBody != null) {
                    builder.post(RequestBody.create(MediaType.parse(request.postBodyContentType), postBody))
                }
            }
            Request.Method.GET -> builder.get()
            Request.Method.DELETE -> builder.delete()
            Request.Method.POST -> builder.post(createRequestBody(request)!!)
            Request.Method.PUT -> builder.put(createRequestBody(request)!!)
            Request.Method.HEAD -> builder.head()
            Request.Method.OPTIONS -> builder.method("OPTIONS", null)
            Request.Method.TRACE -> builder.method("TRACE", null)
            Request.Method.PATCH -> builder.patch(createRequestBody(request)!!)
            else -> throw IllegalStateException("Unknown method type.")
        }
    }

    private fun parseProtocol(p: Protocol): ProtocolVersion {
        when (p) {
            Protocol.HTTP_1_0 -> return ProtocolVersion("HTTP", 1, 0)
            Protocol.HTTP_1_1 -> return ProtocolVersion("HTTP", 1, 1)
            Protocol.SPDY_3 -> return ProtocolVersion("SPDY", 3, 1)
            Protocol.HTTP_2 -> return ProtocolVersion("HTTP", 2, 0)
        }

        throw IllegalAccessError("Unkwown protocol")
    }

    @Throws(AuthFailureError::class)
    private fun createRequestBody(r: Request<*>): RequestBody? {
        val body = r.body ?: return null

        return RequestBody.create(MediaType.parse(r.bodyContentType), body)
    }
}
