@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.baiyu.androidx.basicmodule.event.RequestState
import com.baiyu.androidx.basicmodule.network.ApiException

typealias StatefulLiveData<T> = LiveData<RequestState<T>>
typealias StatefulMutableLiveData<T> = MutableLiveData<RequestState<T>>

@MainThread
inline fun <T> StatefulLiveData<T>.observeState(
    owner: LifecycleOwner,
    crossinline onLoading: () -> Unit = {},
    crossinline onSuccess: (T?) -> Unit = {},
    crossinline onError: (ApiException?) -> Unit = {}
) {
    observe(owner) { state ->
        when (state) {
            is RequestState.Loading -> onLoading.invoke()
            is RequestState.Success -> onSuccess(state.data)
            is RequestState.Error -> onError(state.error)
        }
    }
}

@MainThread
inline fun <T> StatefulLiveData<T>.observeState(
    owner: LifecycleOwner,
    init: ResultBuilder<T>.() -> Unit
) {
    val result = ResultBuilder<T>().apply(init)

    observe(owner) { state ->
        when (state) {
            is RequestState.Loading -> result.onLoading.invoke()
            is RequestState.Success -> result.onSuccess(state.data)
            is RequestState.Error -> result.onError(state.error)
        }
    }
}