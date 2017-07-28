package com.lv.retrofit.api

import android.support.v4.util.ArrayMap
import com.lv.retrofit.model.UpdateBean
import com.lv.retrofit.model.User
import com.lv.retrofit.model.UserInfo
import com.lv.retrofit.proxy.annotation.*
import rx.Observable

/**
 * Date: 2017-07-27
 * Time: 14:23
 * Description:
 */
interface UserApi {
    @GET("user/kkmike999.json")
    fun loadUser(@Query("key") value: String): User

    @GET("user/kkmike999.json")
    fun loadUser2(@Query("key") value: String): Observable<User>

    @POST("app/getNewest") //http://10.13.3.19:16000/wmapp-server/app/getNewest
    fun getNewest(@Url url: String, param: Map<String, String>): Observable<UpdateBean>

    @GET("login/getVerifycode")
    fun getVerifycode(@Query("phone") phone: String): Observable<ArrayMap<String, String>>


    @POST("login/login")
    fun login(@Body param: Map<String, String>): Observable<UserInfo>

    @POST("app/getNewest")
    fun user(@Body user: User): Observable<UpdateBean>
}