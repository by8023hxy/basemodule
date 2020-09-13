@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.base

import androidx.lifecycle.ViewModel
import com.baiyu.androidx.basicmodule.livedata.EventMutableLiveData

/**
 * @description：ViewModel的基类 使用ViewModel类
 */
open class BaseViewModel : ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框，目前不需要loading的情况可忽略
     */
    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { EventMutableLiveData<String>() }

        //隐藏
        val dismissDialog by lazy { EventMutableLiveData<Boolean>() }
    }
}