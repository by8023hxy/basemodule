@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.baiyu.androidx.basicmodule.base.config.DataBindingConfig
import com.baiyu.androidx.basicmodule.ext.logD

/**
 * @author Baiyu
 * @date :2020/9/13 12:02 PM September
 * @version: 1.0
 * 适用于dataBinding的Fragment基类
 */
abstract class DataBindingFragment : Fragment() {

    lateinit var appCompatActivity: AppCompatActivity

    protected abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appCompatActivity = context as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initDataBinding(inflater, container)
    }

    /**
     * dataBinding绑定处理
     */
    private fun initDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        "${javaClass.simpleName} initDataBinding START".logD("DataBindingFragment")
        with(getDataBindingConfig()) {
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            binding.apply {
                lifecycleOwner = this@DataBindingFragment
                setVariable(getVariableId(), getViewModel())
                getBindingParams()?.forEach { key: Int, any: Any ->
                    setVariable(key, any)
                }
                "${javaClass.simpleName} initDataBinding END".logD("DataBindingFragment")
                return root
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserve()
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun setObserve()

}