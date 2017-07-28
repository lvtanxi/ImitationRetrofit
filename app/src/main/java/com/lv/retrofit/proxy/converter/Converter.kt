package com.lv.retrofit.proxy.converter

import java.io.IOException

/**
 * Date: 2017-07-27
 * Time: 10:04
 * Description: 转换器
 */
interface Converter<out T> {

    @Throws(IOException::class)
    fun convert(json: String): T
}