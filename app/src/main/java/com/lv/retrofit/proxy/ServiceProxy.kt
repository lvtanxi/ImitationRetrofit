package com.lv.retrofit.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * Date: 2017-07-27
 * Time: 14:01
 * Description:
 */
class ServiceProxy(val imitationRetrofit: ImitationRetrofit) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        if (method?.declaringClass == Any::class.java)
            return method.invoke(this, args)
        method ?: throw RuntimeException("method is null")
        val serviceMethod = ServiceMethod(method)
        val relativeUrl = serviceMethod.getRelativeUrl(args)
        val converter = imitationRetrofit.converter(serviceMethod.actualType)
        val call = HttpCall<Any>(serviceMethod, converter, args, imitationRetrofit.httpClient, imitationRetrofit.baseUrl, relativeUrl)
        return imitationRetrofit.adapter.adapt(call)
    }

}