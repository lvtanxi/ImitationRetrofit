package com.lv.retrofit.proxy

import com.lv.retrofit.proxy.adapter.CallAdapter
import com.lv.retrofit.proxy.adapter.RxCallAdapter
import com.lv.retrofit.proxy.converter.Converter
import com.lv.retrofit.proxy.converter.ConverterFactory
import com.lv.retrofit.proxy.converter.GsonConverterFactory
import okhttp3.OkHttpClient
import java.lang.reflect.Proxy
import java.lang.reflect.Type


/**
 * Date: 2017-07-27
 * Time: 13:02
 * Description: Retrofit
 */
class ImitationRetrofit(
        val adapter: CallAdapter<*>,
        val converterFactory: ConverterFactory,
        val httpClient: OkHttpClient,
        val baseUrl: String) {
    fun <T> create(clazz: Class<T>): T {
        if (!clazz.isInterface)
            throw RuntimeException("$clazz not interface")
        return Proxy.newProxyInstance(clazz.classLoader, arrayOf(clazz), ServiceProxy(this)) as T
    }

    fun converter(returnType: Type): Converter<*> = converterFactory.getConverter(returnType)


    class Builder {
        private var adapter: CallAdapter<*>? = null
        private var converterFactory: ConverterFactory? = null
        private var httpClient: OkHttpClient? = null
        private var baseUrl: String = ""

        fun callAdapter(callAdapter: CallAdapter<*>): Builder {
            this.adapter = callAdapter
            return this
        }

        fun baseUrl(url: String): Builder {
            this.baseUrl = url
            return this
        }

        fun httpClient(httpClient: OkHttpClient): Builder {
            this.httpClient = httpClient
            return this
        }

        fun converter(converterFactory: ConverterFactory): Builder {
            this.converterFactory = converterFactory
            return this
        }

        fun build(): ImitationRetrofit {
            if (baseUrl.isNullOrEmpty())
                throw IllegalArgumentException("this base url is null")

            if (!baseUrl.endsWith("/"))
                throw IllegalArgumentException("this base url must end with /")

            if (adapter == null)
                adapter = RxCallAdapter<Any>()

            if (converterFactory == null)
                converterFactory = GsonConverterFactory()

            if (httpClient == null)
                httpClient = OkHttpClient()

            return ImitationRetrofit(adapter!!, converterFactory!!, httpClient!!,baseUrl)
        }
    }

}