@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.base

import androidx.lifecycle.viewModelScope
import com.baiyu.androidx.basicmodule.event.RequestState
import com.baiyu.androidx.basicmodule.ext.logE
import com.baiyu.androidx.basicmodule.livedata.StatefulMutableLiveData
import com.baiyu.androidx.basicmodule.livedata.postEventValue
import com.baiyu.androidx.basicmodule.network.ApiException
import com.baiyu.androidx.basicmodule.util.ExceptionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @ProjectName:AgentHmi
 * @Author:BaiYu
 * @Email:baiyu@autoai.com
 * @Time:2020/7/27 9:12
 * @description：Flow
 */

@ExperimentalCoroutinesApi
fun <T> BaseViewModel.flowRequest(
    request: suspend () -> Flow<T>,
    resultState: StatefulMutableLiveData<T>,
    isShowDialog: Boolean = true,
    loadingMessage: String = "数据加载中..."
) {
    viewModelScope.launch(Dispatchers.IO) {
        request().onStart {
            if (isShowDialog) loadingChange.showDialog.postEventValue(loadingMessage)
        }.onCompletion {
            loadingChange.dismissDialog.postEventValue(false)
        }.catch {
            loadingChange.dismissDialog.postEventValue(false)
            resultState.value = RequestState.Error(ExceptionUtil.getApiException(it))
            it.toString().logE("flowRequest catch")
        }.collectLatest {
            resultState.value = RequestState.Success(it)
        }
    }
}

@ExperimentalCoroutinesApi
fun <T> BaseViewModel.flowRequestNoCheck(
    request: suspend () -> Flow<T>,
    success: (T) -> Unit,
    error: (ApiException) -> Unit = {},
    isShowDialog: Boolean = true,
    loadingMessage: String = "数据加载中..."
) {
    viewModelScope.launch(Dispatchers.IO) {
        request().onStart {
            if (isShowDialog) loadingChange.showDialog.postEventValue(loadingMessage)
        }.onCompletion {
            loadingChange.dismissDialog.postEventValue(false)
        }.catch {
            loadingChange.dismissDialog.postEventValue(false)
            error(ExceptionUtil.getApiException(it))
            it.toString().logE("flowRequest catch")
        }.collectLatest {
            success(it)
        }
    }
}
