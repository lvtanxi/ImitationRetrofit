package com.lv.retrofit.proxy.converter

import java.lang.reflect.Type

/**
 * Date: 2017-07-27
 * Time: 10:06
 * Description: 转换器的代理工厂
 */
interface ConverterFactory {
    /**
     * 获取转换器的方法
     */
    fun getConverter(type: Type): Converter<*>
}