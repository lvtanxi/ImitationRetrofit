package com.lv.retrofit.proxy

import com.lv.retrofit.proxy.annotation.GET
import com.lv.retrofit.proxy.annotation.POST
import com.lv.retrofit.proxy.annotation.Query
import com.lv.retrofit.proxy.annotation.Url
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Date: 2017-07-27
 * Time: 10:29
 * Description: 获取api接口中方法与属性
 */
class ServiceMethod(val method: Method) {
    /**
     * 获取方法的注解
     */
    private val methodAnnotations = method.declaredAnnotations
    /**
     * 获取方法的参数注解
     */
    private val argumentAnnotations = method.parameterAnnotations
    /**
     * 获取参数的类型
     */
    internal val argumentTypes = method.parameterTypes

    /**
     * 返回值的类型
     */
    internal val returnType = method.genericReturnType

    /**
     * 转换器的类型
     */
    internal val actualType: Type


    init {
        /**
         * 判断是不是泛型参数，如果是则获取泛型。否则就跟返回值得类型一致
         */
        actualType = if (returnType is ParameterizedType) returnType.actualTypeArguments[0] else returnType
    }


    fun getQueryKey(index: Int): String {
        var result = ""
        argumentAnnotations[index].filter { it is Query }
                .forEach { result = (it as Query).value }
        return result
    }

    fun getHttpUrl(): String {
        for (annotation in methodAnnotations) {
            if (annotation is GET)
                return annotation.value
            else if (annotation is POST)
                return annotation.value
        }
        return ""
    }

    fun isHttpGet(): Boolean = (method.getAnnotation(GET::class.java) != null)

    fun getRelativeUrl(args: Array<out Any>?): String {
        args ?: return ""
        argumentAnnotations.withIndex().forEach {
            if (it.value[0] is Url)
                return args[it.index].toString()
        }
        return ""
    }


}