package com.baiyu.androidx.basicmodule.util

import com.baiyu.androidx.basicmodule.ext.logD
import com.baiyu.androidx.basicmodule.network.ApiException
import kotlinx.coroutines.CancellationException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author Baiyu
 * @date :2020/9/13 1:15 PM September
 * @version: 1.0
 */
object ExceptionUtil {
    fun getApiException(e: Throwable): ApiException {
        "getApiException==${e}".logD()
        return when (e) {
            is UnknownHostException -> {
                ApiException("网络异常", -100)
            }
            is JSONException -> {//|| e is JsonParseException
                ApiException("数据异常", -100)
            }
            is SocketTimeoutException -> {
                ApiException("连接超时", -100)
            }
            is ConnectException -> {
                ApiException("连接错误", -100)
            }
            is HttpException -> {
                ApiException("Http code ${e.code()}", -100)
            }
            is ApiException -> {
                e
            }
            /**
             * 如果协程还在运行，个别机型退出当前界面时，viewModel会通过抛出CancellationException，
             * 强行结束协程，与java中InterruptException类似，所以不必理会,只需将toast隐藏即可
             */
            is CancellationException -> {
                ApiException("CancellationException", -10)
            }
            else -> {
                ApiException("未知错误", -100)
            }
        }
    }
}