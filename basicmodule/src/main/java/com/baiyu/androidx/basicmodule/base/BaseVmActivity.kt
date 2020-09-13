@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author Baiyu
 * @date :2020/9/13 11:56 AM September
 * @version: 1.0
 */
abstract class BaseVMActivity : AppCompatActivity() {

    protected inline fun <reified T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy {
        DataBindingUtil.setContentView<T>(this, resId).apply {
            lifecycleOwner = this@BaseVMActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObserve()
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun setObserve()

}