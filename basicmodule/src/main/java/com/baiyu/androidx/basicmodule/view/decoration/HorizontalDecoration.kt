package com.baiyu.androidx.basicmodule.view.decoration;

/**
 * 水平
 */
public class HorizontalDecoration extends DirectionDecoration {

    @Override
    public void getItemBorder(int position, ItemBorder itemBorder, int count) {
        if (position == 0) {
            if (isShowFirst()) {
                itemBorder.getTopBorder().setColor(getColor()).setThickness(getSize()).setPaddingLeft(getFirstPaddingLeft()).setPaddingRight(getFirstPaddingRight());
            }
            itemBorder.getBottomBorder().setColor(getColor()).setThickness(getSize()).setPaddingLeft(getPaddingLeft()).setPaddingRight(getPaddingRight());
        } else if (position == count - 1) {
            if (isShowLast()) {
                itemBorder.getBottomBorder().setColor(getColor()).setThickness(getSize()).setPaddingLeft(getLastPaddingLeft()).setPaddingRight(getLastPaddingRight());
            }
        } else {
            itemBorder.getBottomBorder().setColor(getColor()).setThickness(getSize()).setPaddingLeft(getPaddingLeft()).setPaddingRight(getPaddingRight());
        }
    }


}
