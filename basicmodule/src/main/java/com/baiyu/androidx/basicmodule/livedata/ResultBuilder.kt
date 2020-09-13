package com.baiyu.androidx.basicmodule.livedata

import com.baiyu.androidx.basicmodule.network.ApiException

class ResultBuilder<T> {
    var onLoading: () -> Unit = {}
    var onSuccess: (data: T?) -> Unit = {}
    var onError: (ApiException?) -> Unit = {}
}