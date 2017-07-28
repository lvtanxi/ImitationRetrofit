package com.lv.retrofit.proxy.adapter

import com.lv.retrofit.proxy.HttpCall
import rx.Observable



/**
 * Date: 2017-07-27
 * Time: 13:06
 * Description:
 */
class RxCallAdapter<T>:CallAdapter<Observable<T>> {
    override fun adapt(httpCall: HttpCall<*>): Observable<T> {

        return Observable.create {
            try {
                it.onNext(httpCall.request() as T)
                it.onCompleted()
            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

        }
    }
}