package com.wumart.warehouse.util

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription



/**
 * Date: 2017-06-21
 * Time: 17:08
 * Description:
 */
fun <T> Observable<T>.io_main(): Observable<T> {
    return this.compose({ tObservable -> tObservable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) })
}

fun Subscription.addComposite(compositeSubscription: CompositeSubscription?) = compositeSubscription?.add(this)

fun <T : View> Context.inflate(@LayoutRes layoutId: Int): T {
    return LayoutInflater.from(this).inflate(layoutId, null) as T
}

fun Context.dp2px(dpVal: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, resources.displayMetrics)

fun View?.setVisibility(show: Boolean) {
    this?.visibility = if (show) View.VISIBLE else View.GONE
}


fun MutableList<*>?.isNull() = this == null || this.isEmpty()

fun Context.getColorInt(@ColorRes color: Int) = ContextCompat.getColor(this, color)


