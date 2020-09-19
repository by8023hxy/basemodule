@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.ext

import android.util.Log
import com.baiyu.androidx.basicmodule.BuildConfig

const val TAG = "BasicModule: "

var showLog = BuildConfig.DEBUG
var showStackTrace = true

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
    val tagBuilder = StringBuilder()
    tagBuilder.append(tag)
    if (showStackTrace){
        val stackTrace = Thread.currentThread().stackTrace[5]
        tagBuilder.append(" ${stackTrace.methodName}(${stackTrace.fileName}:${stackTrace.lineNumber})")
    }
    when (level) {
        LEVEL.V -> Log.v(tagBuilder.toString(), message)
        LEVEL.D -> Log.d(tagBuilder.toString(), message)
        LEVEL.I -> Log.i(tagBuilder.toString(), message)
        LEVEL.W -> Log.w(tagBuilder.toString(), message)
        LEVEL.E -> Log.e(tagBuilder.toString(), message)
    }
}