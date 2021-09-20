package com.baiyu.androidx.basicmodule.base.easydatabind

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseBindingFragment<T : ViewDataBinding> constructor(
    @LayoutRes val contentLayoutId: Int
) : BindingFragment<T>(contentLayoutId) {

    private var mActivity: AppCompatActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterViews()
        initObserver()
    }

    abstract fun afterViews()
    abstract fun initObserver()

}