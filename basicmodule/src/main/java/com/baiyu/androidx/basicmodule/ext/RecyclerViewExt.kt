package com.baiyu.androidx.basicmodule.ext

import android.graphics.Color
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baiyu.androidx.basicmodule.view.decoration.GridSpacingItemDecoration
import com.baiyu.androidx.basicmodule.view.decoration.HorizontalDecoration
import com.baiyu.androidx.basicmodule.view.decoration.SpaceItemDecoration


/**
 * @ProjectName:AgentHmi
 * @Author:BaiYu
 * @Email:baiyu@autoai.com
 * @Time:2020/8/21 15:51
 * @description：
 */

@BindingAdapter("adapter")
fun RecyclerView.adapter(adapter: RecyclerView.Adapter<*>) {
    setAdapter(adapter)
}

fun RecyclerView.itemDecoration() {
    this.addItemDecoration(
        HorizontalDecoration().setColor(Color.parseColor("#19FFFFFF"))
            .setSize(1f)
            .setPaddingLeft(0f)
            .setPaddingRight(0f)
            .setFirstPaddingLeft(0f)
            .setFirstPaddingRight(0f)
            .setShowFirst(false)
            .setShowLast(false))
}

fun RecyclerView.itemDecorationGrid(count: Int = 4, horizontal: Int = 20, vertical: Int = 10) {
    this.addItemDecoration(GridSpacingItemDecoration(count, horizontal, vertical, false))
}

