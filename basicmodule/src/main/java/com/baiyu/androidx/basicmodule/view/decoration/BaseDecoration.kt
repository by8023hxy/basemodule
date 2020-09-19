package com.baiyu.androidx.basicmodule.view.decoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Baiyu
 * @date :2020/9/19 9:45 AM September
 * @version: 1.0
 */
open class BaseDecoration : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    private val itemBorder = ItemBorder()

    val rectF = RectF()

    fun BaseDecoration() {
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount) {
            //子View
            val view = parent.getChildAt(i)
            //reset itemBorder
            itemBorder.reset()
            //给ItemBorder赋值
            getItemBorder(parent.getChildAdapterPosition(view), itemBorder, state.itemCount)
            val layoutParams = view.layoutParams as RecyclerView.LayoutParams
            //左边缘-leftBorder
            rectF[view.left - layoutParams.leftMargin - itemBorder.leftBorder!!.thickness.toFloat(), view.top - layoutParams.topMargin - itemBorder.topBorder!!.thickness + itemBorder.leftBorder!!.paddingRight.toFloat(), view.left - layoutParams.leftMargin.toFloat()] =
                view.bottom + layoutParams.bottomMargin + itemBorder.bottomBorder!!.thickness - itemBorder.leftBorder!!.paddingLeft.toFloat()
            paint.color = itemBorder.leftBorder!!.color
            c.drawRect(rectF, paint)
            //上边缘-topBorder
            rectF[view.left - layoutParams.leftMargin - itemBorder.leftBorder!!.thickness + itemBorder.topBorder!!.paddingLeft.toFloat(), view.top - layoutParams.topMargin - itemBorder.topBorder!!.thickness.toFloat(), view.right + layoutParams.rightMargin + itemBorder.rightBorder!!.thickness - itemBorder.topBorder!!.paddingRight.toFloat()] =
                view.top - layoutParams.topMargin.toFloat()
            paint.color = itemBorder.topBorder!!.color
            c.drawRect(rectF, paint)
            //右边缘-rightBorder
            rectF[view.right + layoutParams.rightMargin.toFloat(), view.top - layoutParams.topMargin - itemBorder.topBorder!!.thickness + itemBorder.rightBorder!!.paddingLeft.toFloat(), view.right + layoutParams.rightMargin + itemBorder.rightBorder!!.thickness.toFloat()] =
                view.bottom + layoutParams.bottomMargin + itemBorder.bottomBorder!!.thickness - itemBorder.rightBorder!!.paddingRight.toFloat()
            paint.color = itemBorder.rightBorder!!.color
            c.drawRect(rectF, paint)
            //下边缘-bottomBorder
            rectF[view.left - layoutParams.leftMargin - itemBorder.leftBorder!!.thickness + itemBorder.bottomBorder!!.paddingLeft.toFloat(), view.bottom + layoutParams.bottomMargin.toFloat(), view.right + layoutParams.rightMargin + itemBorder.rightBorder!!.thickness - itemBorder.bottomBorder!!.paddingRight.toFloat()] =
                view.bottom + +layoutParams.bottomMargin + itemBorder.bottomBorder!!.thickness.toFloat()
            paint.color = itemBorder.bottomBorder!!.color
            c.drawRect(rectF, paint)
        }
    }


   override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        //reset itemBorder
        itemBorder.reset()
        //给ItemBorder赋值
        getItemBorder(parent.getChildAdapterPosition(view), itemBorder, state.itemCount)
        outRect[itemBorder.leftBorder!!.thickness, itemBorder.topBorder!!.thickness, itemBorder.rightBorder!!.thickness] =
            itemBorder.bottomBorder!!.thickness
    }

    /**
     * @param position   当前视图的位置
     * @param itemBorder 当前itemView的边缘
     * @param count      元素总数
     */
    open fun getItemBorder(position: Int, itemBorder: ItemBorder?, count: Int) {}
}