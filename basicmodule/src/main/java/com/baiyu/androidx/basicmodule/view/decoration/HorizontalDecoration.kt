package com.baiyu.androidx.basicmodule.view.decoration

/**
 * 水平
 */
class HorizontalDecoration : DirectionDecoration() {
    override fun getItemBorder(position: Int, itemBorder: ItemBorder?, count: Int) {
        if (position == 0) {
            if (isShowFirst) {
                itemBorder?.topBorder?.setColor(color)?.setThickness(size)
                    ?.setPaddingLeft(firstPaddingLeft)?.setPaddingRight(firstPaddingRight)
            }
            itemBorder?.bottomBorder?.setColor(color)?.setThickness(size)
                ?.setPaddingLeft(paddingLeft)?.setPaddingRight(paddingRight)
        } else if (position == count - 1) {
            if (isShowLast) {
                itemBorder?.bottomBorder?.setColor(color)?.setThickness(size)
                    ?.setPaddingLeft(lastPaddingLeft)?.setPaddingRight(lastPaddingRight)
            }
        } else {
            itemBorder?.bottomBorder?.setColor(color)?.setThickness(size)
                ?.setPaddingLeft(paddingLeft)?.setPaddingRight(paddingRight)
        }
    }
}