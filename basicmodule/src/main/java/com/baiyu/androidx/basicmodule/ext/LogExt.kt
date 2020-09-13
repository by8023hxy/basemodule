@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.ext

import android.util.Log
import com.baiyu.androidx.basicmodule.BuildConfig

const val TAG = "BasicModule: "

var showLog = BuildConfig.DEBUG

private enum class LEVEL {
    V, D, I, W, E
}

fun String.logV(tag: String = TAG) =
        log(LEVEL.V, TAG + tag, this)

fun String.logD(tag: String = TAG) =
        log(LEVEL.D, TAG + tag, this)

fun String.logI(tag: String = TAG) =
        log(LEVEL.I, TAG + tag, this)

fun String.logW(tag: String = TAG) =
        log(LEVEL.W, TAG + tag, this)

fun String.logE(tag: String = TAG) =
        log(LEVEL.E, TAG + tag, this)

private fun log(level: LEVEL, tag: String, message: String) {
    if (!showLog) return
    when (level) {
        LEVEL.V -> Log.v(tag, message)
        LEVEL.D -> Log.d(tag, message)
        LEVEL.I -> Log.i(tag, message)
        LEVEL.W -> Log.w(tag, message)
        LEVEL.E -> Log.e(tag, message)
    }
}