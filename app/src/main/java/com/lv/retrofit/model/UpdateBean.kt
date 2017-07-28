package com.lv.retrofit.model

class UpdateBean {

    var content: String = ""
    var new_version: Int = 0
    var url: String? = null
    var is_force_update: Int = 0
    var version_name: String = ""

    val isIs_force_update: Boolean
        get() = is_force_update != 1 //1表示强制更新

    override fun toString(): String {
        return "UpdateBean(content='$content', new_version=$new_version, url=$url, is_force_update=$is_force_update, version_name='$version_name')"
    }


}
