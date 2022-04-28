@file:Suppress("unused")

package com.baiyu.androidx.basicmodule.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.baiyu.androidx.basicmodule.BaseConstant
import com.baiyu.androidx.basicmodule.ext.logD
import com.baiyu.androidx.basicmodule.ext.logE
import com.baiyu.androidx.basicmodule.livedata.RequestState
import com.baiyu.androidx.basicmodule.livedata.StatefulLiveData
import com.baiyu.androidx.basicmodule.network.ApiException
import com.baiyu.androidx.basicmodule.network.BaseResponse
import com.google.gson.Gson
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.*
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

const val TAG = "NetWorkThread"

/**
 * 使用flow直接返回一个livedata
 */
inline fun <T> flowScope(crossinline request: suspend () -> BaseResponse<T>): LiveData<RequestState<T>> {
    return flow {
        "flowScope onThread=${Thread.currentThread().name}".logD(TAG)
        val response = request()
        "flowScope response=${Gson().toJson(response)}".logD(TAG)
        if (response.isSuccess()) {
            emit(RequestState.Success(response.getResponseData()))
        } else {
            emit(
                RequestState.Error(
                    ApiException(
                        response.getResponseMsg(),
                        response.getResponseCode()
                    )
                )
            )
        }
    }.onStart {
        emit(RequestState.Loading)
    }.commonCatch {
        emit(RequestState.Error(it))
    }.asLiveData()
}


/**
 * 使用liveData作用域返回一个StatefulLiveData
 */
inline fun <T> liveDataScope(crossinline request: suspend () -> BaseResponse<T>): StatefulLiveData<T> {
    return liveData {
        "liveDataScope onThread=${Thread.currentThread().name}".logD(TAG)
        try {
            emit(RequestState.Loading)
            val response = request()
            "liveDataScope response=${Gson().toJson(response)}".logD(TAG)
            if (response.isSuccess()) {
                emit(RequestState.Success(response.getResponseData()))
            } else {
                RequestState.Error(
                    ApiException(
                        response.getResponseMsg(),
                        response.getResponseCode()
                    )
                )
            }
        } catch (e: Exception) {
            "liveDataScope exception==$e".logE(TAG)
            emit(
                RequestState.Error(getApiException(e))
            )
        }
    }
}

fun getApiException(throwable: Throwable): ApiException {
    "getApiException==${throwable}".logE(TAG)
    return when (throwable) {
        is UnknownHostException, is SocketTimeoutException, is ConnectException -> {
            ApiException("网络异常", BaseConstant.NET_ERROR)
        }
        is JSONException -> {
            ApiException("Json解析异常", BaseConstant.JSON_ERROR)
        }
        is HttpException -> {
            ApiException("Http请求异常", throwable.code())
        }
        /**
         * 如果协程还在运行，个别机型退出当前界面时，viewModel会通过抛出CancellationException，
         * 强行结束协程，与java中InterruptException类似，所以不必理会,只需将toast隐藏即可
         */
        is CancellationException -> {
            ApiException("协程异常", BaseConstant.COROUTINE_ERROR)
        }
        is ApiException -> {
            throwable
        }
        else -> {
            ApiException("未知错误", BaseConstant.UNKNOWN_ERROR)
        }
    }
}

fun <T> Flow<T>.commonCatch(action: suspend FlowCollector<T>.(cause: ApiException) -> Unit): Flow<T> {
    return this.catch {
        val apiException = getApiException(it)
        "commonCatch exception==$apiException".logE()
        action(apiException)
    }
}



