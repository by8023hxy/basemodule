package com.baiyu.androidx.basicframework.ui.main

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.baiyu.androidx.basicframework.R
import com.baiyu.androidx.basicframework.adapter.DemoAdapter
import com.baiyu.androidx.basicframework.databinding.ActivityMainBinding
import com.baiyu.androidx.basicframework.view.ProgressDialogFragment
import com.baiyu.androidx.basicmodule.base.BaseVMActivity
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseVMActivity() {

    private val mainBinding: ActivityMainBinding by binding(R.layout.activity_main)

    private val mainViewModel: MainViewModel by viewModel()

    private val dialog: ProgressDialogFragment by lazy { ProgressDialogFragment() }

    override fun initView() {
        mainBinding.apply {
            lifecycleOwner = this@MainActivity
            adapter = DemoAdapter()
            vm = mainViewModel.apply {
                getBannerList()
            }
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