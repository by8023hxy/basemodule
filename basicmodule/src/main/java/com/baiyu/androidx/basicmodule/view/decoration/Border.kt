package com.baiyu.androidx.basicmodule.view.decoration

import android.graphics.Color

/**
 * 每个Item的边缘，a border, with thickness, color and left/right side padding
 */
class Border {
    var color: Int
        private set
    var thickness //边缘的厚度
            : Int
        private set
    var paddingLeft //边缘的左间距
            : Int
        private set
    var paddingRight //边缘的右间距
            : Int
        private set

    fun reset() {
        color = Color.TRANSPARENT
        thickness = 0
        paddingLeft = 0
        paddingRight = 0
    }

    fun setThickness(thickness: Float): Border {
        this.thickness = Math.round(thickness) //减小失真
        return this
    }

    fun setThickness(thickness: Int): Border {
        this.thickness = thickness
        return this
    }

    fun setPaddingLeft(paddingLeft: Float): Border {
        this.paddingLeft = Math.round(paddingLeft) //减小失真
        return this
    }

    fun setPaddingLeft(paddingLeft: Int): Border {
        this.paddingLeft = paddingLeft
        return this
    }

    fun setPaddingRight(paddingRight: Float): Border {
        this.paddingRight = Math.round(paddingRight) //减小失真
        return this
    }

    fun setPaddingRight(paddingRight: Int): Border {
        this.paddingRight = paddingRight
        return this
    }

    fun setColor(color: Int): Border {
        this.color = color
        return this
    }

    init {
        color = Color.TRANSPARENT
        thickness = 0
        paddingLeft = 0
        paddingRight = 0
    }
}