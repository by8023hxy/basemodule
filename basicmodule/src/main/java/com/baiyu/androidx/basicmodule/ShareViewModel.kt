package com.baiyu.androidx.basicmodule

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData


/**
 * @description：全局ViewModel
 * */
class ShareViewModel : ViewModel() {

    val requestFailed: UnPeekLiveData<Boolean> = UnPeekLiveData()

}