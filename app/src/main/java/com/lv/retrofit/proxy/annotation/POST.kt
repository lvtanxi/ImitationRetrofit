package com.lv.retrofit.proxy.annotation

/**
 * Date: 2017-07-27
 * Time: 09:55
 * Description: POST请求的注解
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class POST(val value: String = "")