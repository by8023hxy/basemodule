@file:Suppress("unused")
package com.baiyu.androidx.basicmodule.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget

/**
 * @author BaiYu
 * @date :2020/9/13 12:02 PM September
 * @version: 1.0
 */
fun ImageView.loadCorner(path: String,corner:Int) {
    Glide.with(this.context)
        .load(path)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(corner)))
        .into(object : DrawableImageViewTarget(this) {
            override fun setResource(resource: Drawable?) {
                super.setResource(resource)
                this.view.background = null
            }
        })
}

fun ImageView.loadCorner(res: Int,corner:Int) {
    Glide.with(this.context)
        .load(res)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(corner)))
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

fun ImageView.loadDrawable(res: Int) {
    this.setImageDrawable(ContextCompat.getDrawable(this.context, res))
}


