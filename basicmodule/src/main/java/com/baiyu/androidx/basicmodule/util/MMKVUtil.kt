package com.baiyu.androidx.basicmodule.util

import com.baiyu.androidx.basicmodule.ext.boolean
import com.baiyu.androidx.basicmodule.ext.logD
import com.baiyu.androidx.basicmodule.ext.string
import com.tencent.mmkv.MMKV

/**
 * @author Baiyu
 * @date :2020/9/13 12:58 PM September
 * @version: 1.0
 */
class MMKVUtil(mmkv: MMKV) {

    companion object {
        @JvmStatic
        val kvUtil: MMKVUtil by lazy { MMKVUtil(MMKV.defaultMMKV()) }

        const val DEFAULT_STRING = ""
        const val DEFAULT_INT = -1
        const val DEFAULT_BOOL = false
        const val LOGIN_STATE = "login_state"

        const val ACCESS_TOKEN = ""
    }

    init {
        "MMKVUtil init ${this.hashCode()}".logD("MMKV--CREATE")
    }

    var loginState by mmkv.boolean(LOGIN_STATE, false)

    var token by mmkv.string(ACCESS_TOKEN)
}