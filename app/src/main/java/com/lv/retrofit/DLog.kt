package com.lv.retrofit

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * Date: 2017-06-21
 * Time: 14:55
 * Description: 扩展方法
 */
object DLog {

    fun init() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("lvtanxi").build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }

    fun d(any: Any?) {
        if (BuildConfig.DEBUG) {
            val message = any ?: "打印了空消息"
            Logger.d(message.toString())
        }
    }

    fun e(any: Any?) {
        if (BuildConfig.DEBUG) {
            val message = any ?: "打印了空消息"
            Logger.e(message.toString())
        }
    }

    fun json(any: Any?) {
        if (BuildConfig.DEBUG) {
            val message = any ?: "打印了空消息"
            Logger.json(message.toString())
        }
    }
}