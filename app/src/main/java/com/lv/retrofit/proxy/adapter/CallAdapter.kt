package com.lv.retrofit.proxy.adapter

import com.lv.retrofit.proxy.HttpCall

/**
 * Date: 2017-07-27
 * Time: 10:28
 * Description: 结果适配器
 */
interface CallAdapter<out T> {
    fun adapt(httpCall: HttpCall<*>): T
}