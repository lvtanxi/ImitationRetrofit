package com.lv.retrofit.proxy.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Date: 2017-07-27
 * Time: 10:09
 * Description: 利用Gson实现一个转换器
 */
class GsonConverter<out T>(gson: Gson, type: Type) : Converter<T> {
    private val adapter: TypeAdapter<*> = gson.getAdapter(TypeToken.get(type))
    override fun convert(json: String): T = adapter.fromJson(json) as T
}