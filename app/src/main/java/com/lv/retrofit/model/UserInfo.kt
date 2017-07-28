package com.lv.retrofit.model

/**
 * Date: 2017-07-10
 * Time: 10:27:22
 * Description: UserInfo
 */

data class UserInfo(
        val avatar: String,
        val phone: String,
        val sex: String,
        val nickName: String,
        val age: Int? = 0,
        val createDate: String,
        val updateDate: String,
        val passWord: String,
        val id: Int? = 0
)