package com.baiyu.androidx.basicmodule.view.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridSpacingItemDecoration(private val spanCount: Int, private val horizontal: Int, private val vertical: Int, private val includeEdge: Boolean) : ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        if (includeEdge) {
            outRect.left = vertical - column * vertical / spanCount
            outRect.right = (column + 1) * vertical / spanCount
            if (position < spanCount) {
                outRect.top = horizontal
            }
            outRect.bottom = horizontal
        } else {
            outRect.left = column * vertical / spanCount
            outRect.right = vertical - (column + 1) * vertical / spanCount
            if (position >= spanCount) {
                outRect.top = horizontal
            }
        }
    }

}