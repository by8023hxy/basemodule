package com.baiyu.androidx.basicmodule.ext

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.baiyu.androidx.basicmodule.view.decoration.GridSpacingItemDecoration
import com.baiyu.androidx.basicmodule.view.decoration.HorizontalDecoration


fun RecyclerView.itemDecoration() {
    this.addItemDecoration(
        HorizontalDecoration().setColor(Color.parseColor("#19FFFFFF"))
            .setSize(1f)
            .setPaddingLeft(0f)
            .setPaddingRight(0f)
            .setFirstPaddingLeft(0f)
            .setFirstPaddingRight(0f)
            .setShowFirst(false)
            .setShowLast(false)
    )
}

fun RecyclerView.itemDecorationGrid(count: Int = 4, horizontal: Int = 20, vertical: Int = 10) {
    this.addItemDecoration(GridSpacingItemDecoration(count, horizontal, vertical, false))
}

