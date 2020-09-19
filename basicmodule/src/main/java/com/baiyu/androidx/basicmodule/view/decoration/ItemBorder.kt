@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.view.decoration

/**
 * 每个视图项的四个边，the four borders of itemView
 */
class ItemBorder {
    //四个边缘
    private val borders = arrayOfNulls<Border>(4) //左上右下

    val leftBorder: Border?
        get() = borders[0]

    val topBorder: Border?
        get() = borders[1]

    val rightBorder: Border?
        get() = borders[2]

    val bottomBorder: Border?
        get() = borders[3]

    fun setBorder(left: Border?, top: Border?, right: Border?, bottom: Border?) {
        if (left != null) {
            borders[0] = left
        }
        if (top != null) {
            borders[1] = top
        }
        if (right != null) {
            borders[2] = right
        }
        if (bottom != null) {
            borders[3] = bottom
        }
    }

    fun setLeftBorder(border: Border?): ItemBorder {
        if (border != null) {
            borders[0] = border
        }
        return this
    }

    fun setTopBorder(border: Border?): ItemBorder {
        if (border != null) {
            borders[1] = border
        }
        return this
    }

    fun setRightBorder(border: Border?): ItemBorder {
        if (border != null) {
            borders[2] = border
        }
        return this
    }

    fun setBottomBorder(border: Border?): ItemBorder {
        if (border != null) {
            borders[3] = border
        }
        return this
    }

    fun setThickness(left: Int, top: Int, right: Int, bottom: Int): ItemBorder {
        borders[0]!!.setThickness(left)
        borders[1]!!.setThickness(top)
        borders[2]!!.setThickness(right)
        borders[3]!!.setThickness(bottom)
        return this
    }

    fun setLeftBorderPadding(l: Int, r: Int): ItemBorder {
        borders[0]!!.setPaddingLeft(l)
        borders[0]!!.setPaddingRight(r)
        return this
    }

    fun setTopBorderPadding(l: Int, r: Int): ItemBorder {
        borders[1]!!.setPaddingLeft(l)
        borders[1]!!.setPaddingRight(r)
        return this
    }

    fun setRightBorderPadding(l: Int, r: Int): ItemBorder {
        borders[2]!!.setPaddingLeft(l)
        borders[2]!!.setPaddingRight(r)
        return this
    }

    fun setBottomBorderPadding(l: Int, r: Int): ItemBorder {
        borders[3]!!.setPaddingLeft(l)
        borders[3]!!.setPaddingRight(r)
        return this
    }

    fun setLeftRightBorderPadding(l: Int, r: Int): ItemBorder {
        setLeftBorderPadding(l, r)
        setRightBorderPadding(l, r)
        return this
    }

    fun setTopBottomBorderPadding(l: Int, r: Int): ItemBorder {
        setTopBorderPadding(l, r)
        setBottomBorderPadding(l, r)
        return this
    }

    fun setColor(left: Int, top: Int, right: Int, bottom: Int): ItemBorder {
        leftBorder!!.setColor(left)
        topBorder!!.setColor(top)
        rightBorder!!.setColor(right)
        bottomBorder!!.setColor(bottom)
        return this
    }

    fun setAllColor(color: Int): ItemBorder {
        for (border in borders) {
            border!!.setColor(color)
        }
        return this
    }

    fun setAllPaddingLeft(paddingLeft: Float): ItemBorder {
        for (border in borders) {
            border!!.setPaddingLeft(paddingLeft)
        }
        return this
    }

    fun setAllPaddingRight(paddingRight: Float): ItemBorder {
        for (border in borders) {
            border!!.setPaddingRight(paddingRight)
        }
        return this
    }

    fun setAllThickness(thickness: Float): ItemBorder {
        for (border in borders) {
            border!!.setThickness(thickness)
        }
        return this
    }

    fun reset() {
        for (i in borders.indices) {
            borders[i]!!.reset()
        }
    }

    init {
        for (i in borders.indices) {
            borders[i] = Border()
        }
    }
}