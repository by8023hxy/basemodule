package com.baiyu.androidx.basicmodule.view.decoration

import android.graphics.Color

/**
 * 跟方向相关
 */
open class DirectionDecoration : BaseDecoration() {
    var isShowFirst = true
        private set
    var isShowLast = true
        private set
    var color = Color.TRANSPARENT
        private set
    var size = 15f
        private set
    var paddingLeft = 15f
        private set
    var paddingRight = 15f
        private set
    var firstPaddingLeft = 0f
        private set
    var firstPaddingRight = 0f
        private set
    var lastPaddingLeft = 0f
        private set
    var lastPaddingRight = 0f
        private set

    fun setShowFirst(showFirst: Boolean): DirectionDecoration {
        isShowFirst = showFirst
        return this
    }

    fun setShowLast(showLast: Boolean): DirectionDecoration {
        isShowLast = showLast
        return this
    }

    fun setColor(color: Int): DirectionDecoration {
        this.color = color
        return this
    }

    fun setSize(size: Float): DirectionDecoration {
        this.size = size
        return this
    }

    fun setPaddingLeft(paddingLeft: Float): DirectionDecoration {
        this.paddingLeft = paddingLeft
        return this
    }

    fun setPaddingRight(paddingRight: Float): DirectionDecoration {
        this.paddingRight = paddingRight
        return this
    }

    fun setFirstPaddingLeft(firstPaddingLeft: Float): DirectionDecoration {
        this.firstPaddingLeft = firstPaddingLeft
        return this
    }

    fun setFirstPaddingRight(firstPaddingRight: Float): DirectionDecoration {
        this.firstPaddingRight = firstPaddingRight
        return this
    }

    fun setLastPaddingLeft(lastPaddingLeft: Float): DirectionDecoration {
        this.lastPaddingLeft = lastPaddingLeft
        return this
    }

    fun setLastPaddingRight(lastPaddingRight: Float): DirectionDecoration {
        this.lastPaddingRight = lastPaddingRight
        return this
    }

}