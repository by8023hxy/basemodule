package com.baiyu.androidx.basicframework.ui.main

import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.baiyu.androidx.basicframework.BR
import com.baiyu.androidx.basicframework.R
import com.baiyu.androidx.basicframework.adapter.DemoAdapter
import com.baiyu.androidx.basicframework.view.ProgressDialogFragment

import com.baiyu.androidx.basicmodule.base.DataBindingActivity
import com.baiyu.androidx.basicmodule.base.config.DataBindingConfig
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : DataBindingActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private val dialog: ProgressDialogFragment by lazy { ProgressDialogFragment() }
    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main, BR.vm, mainViewModel)
            .addBindingParam(BR.adapter, DemoAdapter())
    }

    override fun initView() {
        mainViewModel.apply {
            getBannerList()
        }
    }

    override fun initData() {

    }

    override fun setObserve() {
        mainViewModel.isLoading().observe(this, {
            operateDialog(it)
        })
    }

    private fun operateDialog(isShow: Boolean) {
        val fm: FragmentManager = supportFragmentManager
        val ft = fm.beginTransaction()
        if (isShow) {
            ft.add(dialog, null)
            ft.commitAllowingStateLoss()
        } else {
            lifecycleScope.launchWhenResumed {
                delay(1000)
                dialog.dismissAllowingStateLoss()
            }
        }
    }
}