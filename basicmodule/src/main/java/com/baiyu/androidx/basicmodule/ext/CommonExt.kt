@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.ext

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.SparseArray
import android.view.View
import androidx.annotation.RequiresApi

/**
 * 获取屏幕宽度
 */
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

/**
 * 获取屏幕高度
 */
val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

/**
 * 判断是否为空 并传入相关操作
 */
inline fun <reified T> T?.notNull(notNullAction: (T) -> Unit, nullAction: () -> Unit) {
    if (this != null) {
        notNullAction.invoke(this)
    } else {
        nullAction.invoke()
    }
}

/**
 * dp值转换为px
 */
fun Context.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

/**
 * px值转换成dp
 */
fun Context.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

/**
 * dp值转换为px
 */
fun View.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

/**
 * px值转换成dp
 */
fun View.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}


fun Context.isNetworkAvailable(): Boolean {
    val info = connectivityManager?.activeNetworkInfo
    return !(null == info || !info.isConnected)
}


/**
 * 设置点击事件
 * @param views 需要设置点击事件的view
 * @param onClick 点击触发的方法
 */
fun setOnclick(vararg views: View?, onClick: (View) -> Unit) {
    views.forEach {
        it?.setOnClickListener { view ->
            onClick.invoke(view)
        }
    }
}

/**
 * 设置防止重复点击事件
 * @param views 需要设置点击事件的view集合
 * @param interval 时间间隔 默认0.5秒
 * @param onClick 点击触发的方法
 */
fun setOnclickNoRepeat(vararg views: View?, interval: Long = 500, onClick: (View) -> Unit) {
    views.forEach {
        it?.clickNoRepeat(interval = interval) { view ->
            onClick.invoke(view)
        }
    }
}

fun View.isOperate(isDisable: Boolean) {
    this.isClickable = !isDisable
    this.isEnabled = !isDisable
}

@RequiresApi(Build.VERSION_CODES.N)
fun String.toHtml(flag: Int = Html.FROM_HTML_MODE_LEGACY): Spanned {
    return Html.fromHtml(this, flag)
}

@Suppress("UNCHECKED_CAST")
fun <T : View> View.findViewOften(viewId: Int): T {
    val viewArray: SparseArray<View> = tag as? SparseArray<View> ?: SparseArray()
    tag = viewArray
    var childView: View? = viewArray.get(viewId)
    if (null == childView) {
        childView = findViewById(viewId)
        viewArray.put(viewId, childView)
    }
    return childView as T
}

/**
 * whether string only contains digits
 */
fun String.areDigitsOnly() = matches(Regex("[0-9]+"))




