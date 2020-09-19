package com.baiyu.androidx.basicmodule.view.decoration

/**
 * 垂直
 */
class VerticalDecoration : DirectionDecoration() {
    override fun getItemBorder(position: Int, itemBorder: ItemBorder?, count: Int) {
        if (position == 0) {
            if (isShowFirst) {
                itemBorder?.leftBorder?.setColor(color)?.setThickness(size)
                    ?.setPaddingLeft(firstPaddingLeft)?.setPaddingRight(firstPaddingRight)
            }
            itemBorder?.rightBorder?.setColor(color)?.setThickness(size)
                ?.setPaddingLeft(paddingLeft)?.setPaddingRight(paddingRight)
        } else if (position == count - 1) {
            if (isShowLast) {
                itemBorder?.rightBorder?.setColor(color)?.setThickness(size)
                    ?.setPaddingLeft(lastPaddingLeft)?.setPaddingRight(lastPaddingRight)
            }
        } else {
            itemBorder?.rightBorder?.setColor(color)?.setThickness(size)
                ?.setPaddingLeft(paddingLeft)?.setPaddingRight(paddingRight)
        }
    }
}