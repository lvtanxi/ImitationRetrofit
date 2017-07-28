package com.lv.retrofit.proxy

import android.support.v4.util.ArrayMap
import com.google.gson.Gson
import com.lv.retrofit.proxy.converter.Converter
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException
import java.net.URLEncoder


/**
 * Date: 2017-07-27
 * Time: 13:08
 * Description: 真正发送网络请求与解析的类
 */
class HttpCall<out T>(
        val serviceMethod: ServiceMethod,
        val converter: Converter<*>,
        val arge: Array<out Any>?,
        val httpClient: OkHttpClient,
        val baseUrl: String,
        val relativeUrl: String) {

    fun request(): T {
        try {
            val argumentCount = arge?.size ?: 0
            if (argumentCount != serviceMethod.argumentTypes.size)
                throw IllegalArgumentException("Argument count ($argumentCount) doesn't match expected count (${serviceMethod.argumentTypes.size})")
            val targetUrl = if (relativeUrl.isNullOrEmpty()) serviceMethod.getHttpUrl() else relativeUrl
            if (targetUrl.isNullOrEmpty())
                throw IllegalArgumentException("this @Url or method not url")
            val json = httpCall(targetUrl, getHttpParam(argumentCount))
            return converter.convert(json) as T
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException(e.message)
        }
    }

    private fun getHttpParam(argumentCount: Int): Any {
        val params = ArrayMap<String, Any>()
        if (serviceMethod.isHttpGet()) {
            for (index in 0 until argumentCount)
                params.put(serviceMethod.getQueryKey(index), arge?.get(index))
            return params
        } else if (arge?.get(0) != null) {
            return arge[0]
        }
        return params
    }

    private fun httpCall(path: String, params: Any): String {
        val builder = Request.Builder().url(requestUrl(path, params))
        if (!serviceMethod.isHttpGet())
            builder.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), Gson().toJson(params)))
        val response = httpClient.newCall(builder.build()).execute()
        response.body() ?: throw RuntimeException("this response body is null")
        return response.body()!!.string()
    }

    /**
     * okHttp get同步请求
     * @param actionUrl  接口地址
     * *
     * @param paramsMap   请求参数
     */
    private fun requestUrl(actionUrl: String, paramsMap: Any): String {
        val tempParams = StringBuilder()
        try {
            if (serviceMethod.isHttpGet()&& paramsMap is Map<*,*>) {
                //处理参数
                for ((pos, key) in paramsMap.keys.withIndex()) {
                    if (pos > 0) {
                        tempParams.append("&")
                    }
                    //对参数进行URLEncoder
                    tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap[key].toString(), "utf-8")))
                }
                //补全请求地址
                return String.format("%s%s?%s", baseUrl, actionUrl, tempParams.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return baseUrl + actionUrl
    }
}