package com.baiyu.androidx.basicmodule.base.easydatabind

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

/**
 * @author BaiYu
 * @date :8/23/21 7:09 AM August
 * @version: 1.0
 */
abstract class BaseBindingActivity<T : ViewDataBinding> constructor(
    @LayoutRes val contentLayoutId: Int
) : BindingActivity<T>(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        afterViews()
        initObserver()
    }

    abstract fun afterViews()
    abstract fun initObserver()
}