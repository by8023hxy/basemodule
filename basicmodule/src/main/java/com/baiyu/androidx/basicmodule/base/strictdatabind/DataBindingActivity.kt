@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.base.strictdatabind

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.baiyu.androidx.basicmodule.ext.logD

/**
 * @author Baiyu
 * @date :2020/9/13 11:56 AM September
 * @version: 1.0
 * 适用于dataBinding的Activity基类
 */
abstract class DataBindingActivity : AppCompatActivity() {

    protected abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        setObserve()
        initView()
        initData()
    }

    /**
     * dataBinding绑定处理
     */
    private fun initDataBinding() {
        "${javaClass.simpleName} initDataBinding START".logD("DataBindingActivity")
        with(getDataBindingConfig()) {
            val binding: ViewDataBinding =
                DataBindingUtil.setContentView(this@DataBindingActivity, getLayoutId())
            binding.apply {
                lifecycleOwner = this@DataBindingActivity
                setVariable(getVariableId(), getViewModel())
                getBindingParams()?.forEach { key: Int, any: Any ->
                    setVariable(key, any)
                }
            }
        }
        "${javaClass.simpleName} initDataBinding END".logD("DataBindingActivity")
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun setObserve()

}