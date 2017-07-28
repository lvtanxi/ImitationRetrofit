package com.lv.retrofit.proxy.adapter

import com.lv.retrofit.proxy.HttpCall

/**
 * Date: 2017-07-27
 * Time: 14:09
 * Description:
 */
class SimpleCallAdapter<out T> : CallAdapter<T> {
    override fun adapt(httpCall: HttpCall<*>): T = httpCall.request() as T
}