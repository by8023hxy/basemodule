package com.baiyu.androidx.basicframework.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget

/**
 * @author Baiyu
 * @date :2020/9/13 2:21 PM September
 * @version: 1.0
 */
@BindingAdapter("imageUrl")
fun bindLoadImage(view: ImageView, path: String) {
    Glide.with(view.context)
        .load(path)
        .into(object : DrawableImageViewTarget(view) {
            override fun setResource(resource: Drawable?) {
                super.setResource(resource)
                this.view.background = null
            }
        })
}