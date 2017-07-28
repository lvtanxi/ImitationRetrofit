package com.lv.retrofit.proxy.converter

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * Date: 2017-07-27
 * Time: 10:18
 * Description: GsonConverter 的代理工厂
 */
class GsonConverterFactory(val gson: Gson = Gson()) : ConverterFactory {
    override fun getConverter(type: Type): Converter<*> = GsonConverter<Any>(gson, type)
}