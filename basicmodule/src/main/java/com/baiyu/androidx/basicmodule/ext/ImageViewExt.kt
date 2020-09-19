package com.baiyu.androidx.basicmodule.ext

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget

/**
 * @author Baiyu
 * @date :2020/9/13 12:02 PM September
 * @version: 1.0
 */
fun ImageView.loadCornerSix(path: String) {
    Glide.with(this.context)
        .load(path)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(6)))
        .into(object : DrawableImageViewTarget(this) {
            override fun setResource(resource: Drawable?) {
                super.setResource(resource)
                this.view.background = null
            }
        })
}

fun ImageView.loadCornerSix(res: Int) {
    Glide.with(this.context)
        .load(res)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(6)))
        .into(object : DrawableImageViewTarget(this) {
            override fun setResource(resource: Drawable?) {
                super.setResource(resource)
                this.view.background = null
            }
        })
}

fun ImageView.loadCircle(path: String) {
    Glide.with(this.context)
        .load(path)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .into(object : DrawableImageViewTarget(this) {
            override fun setResource(resource: Drawable?) {
                super.setResource(resource)
                this.view.background = null
            }
        })
}

fun ImageView.loadCircle(res: Int) {
    Glide.with(this.context)
        .load(res)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(6)))
        .into(object : DrawableImageViewTarget(this) {
            override fun setResource(resource: Drawable?) {
                super.setResource(resource)
                this.view.background = null
            }
        })
}

fun ImageView.loadNoDefault(path: String) {
    Glide.with(this.context)
        .load(path)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(6)))
        .into(object : DrawableImageViewTarget(this) {
            override fun setResource(resource: Drawable?) {
                super.setResource(resource)
                this.view.background = null
            }
        })
}

fun ImageView.loadDrawable(res: Int) {
    this.setImageDrawable(ContextCompat.getDrawable(this.context, res))
}

fun ImageView.setGray(isGray: Boolean) {
    val cm = ColorMatrix()
    cm.setSaturation(0f) // 设置饱和度
    val grayColorFilter = ColorMatrixColorFilter(cm)
    if (isGray)
        this.colorFilter = grayColorFilter
    else
        this.colorFilter = null
}

