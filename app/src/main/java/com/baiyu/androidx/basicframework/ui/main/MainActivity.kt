package com.baiyu.androidx.basicframework.ui.main

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.baiyu.androidx.basicframework.R
import com.baiyu.androidx.basicframework.adapter.DemoAdapter
import com.baiyu.androidx.basicframework.databinding.ActivityMainBinding
import com.baiyu.androidx.basicframework.view.ProgressDialogFragment
import com.baiyu.androidx.basicmodule.base.easydatabind.BaseBindingActivity
import com.baiyu.androidx.basicmodule.livedata.observeState
import com.baiyu.androidx.basicmodule.livedata.observeStatus
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseBindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModel()

    private val dialog: ProgressDialogFragment by lazy { ProgressDialogFragment() }

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

    override fun afterViews() {
        binding {
            lifecycleOwner = this@MainActivity
            adapter = DemoAdapter()
            vm = mainViewModel.apply {
                getBannerList()
            }
        }
    }

    override fun initObserver() {
        mainViewModel.bannerList.observeState(this, onLoading = {
            operateDialog(true)
        }, onSuccess = {
            mainViewModel.banner.set(it)
            operateDialog(false)
        }, onError = {
            operateDialog(false)
        })

        mainViewModel.bannerList.observeStatus(this) {
            onError = {}
            onLoading = {}
            onSuccess = {}
        }
    }
}